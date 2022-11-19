package BankProject.Contador.Service;
import BankProject.Contador.Model.DTO.RelatorioDiarioDTO;
import BankProject.Contador.Model.Enums.MesesDoAno;
import BankProject.Contador.Model.RelatorioDiario;
import BankProject.Contador.Model.TotalAno;
import BankProject.Contador.Model.TotalMes;
import BankProject.Contador.Model.User;
import BankProject.Contador.Repository.RelatorioDiarioRepository;
import BankProject.Contador.Repository.TotalAnoRepository;
import BankProject.Contador.Repository.TotalMesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class RelatorioService {

    @Autowired
    private RelatorioDiarioRepository relatorioDiarioRepository;
    @Autowired
    private TotalAnoRepository totalAnoRepository;
    @Autowired
    private TotalMesRepository totalMesRepository;
    @Autowired
    private UserService userService;

    public boolean novoRelatorio(RelatorioDiarioDTO relatorioDTO) {

        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());

        if (relatorioDTO.getMes() > 13 || relatorioDTO.getAno() > 2023 || relatorioDTO.getDia() > 31 || relatorioDTO.getDia() == 0 || relatorioDTO.getAno() == 0 || relatorioDTO.getAno() < 2000) {
            return false;
        }else {
            RelatorioDiario novoRelatorio = converteRelatorio(relatorioDTO);
            RelatorioDiario existe = relatorioDiarioRepository.findByDiaMesEAno(novoRelatorio.getMes(), novoRelatorio.getDia(), novoRelatorio.getAno(), user);
            if (existe == null){
                novoRelatorio.setUser(user);
                relatorioDiarioRepository.save(novoRelatorio);
                somaMes(novoRelatorio);
                somaAno(novoRelatorio);
            }else{
                editaRelatorioExistente(existe, novoRelatorio);
            }
            return true;
        }
    }

    private void editaRelatorioExistente(RelatorioDiario antigoRelatorio, RelatorioDiario novoRelatorio) {
        editaMes(antigoRelatorio, novoRelatorio);
        editaAno(antigoRelatorio, novoRelatorio);
        antigoRelatorio.setTotal_de_vendas(novoRelatorio.getTotal_de_vendas());
        antigoRelatorio.setTotal_de_vendas_debito(novoRelatorio.getTotal_de_vendas_debito());
        antigoRelatorio.setTotal_de_vendas_dinheiro(novoRelatorio.getTotal_de_vendas_dinheiro());
        antigoRelatorio.setTotal_de_vendas_credito(novoRelatorio.getTotal_de_vendas_credito());

        relatorioDiarioRepository.save(antigoRelatorio);
    }

    private void editaAno(RelatorioDiario antigoRelatorio, RelatorioDiario novoRelatorio) {
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        TotalAno ano = totalAnoRepository.findByAno(antigoRelatorio.getAno(), user);
        ano.setTotalAno(ano.getTotalAno().subtract(antigoRelatorio.getTotal_de_vendas()).add(novoRelatorio.getTotal_de_vendas()));
        ano.setVendasDinheiro(ano.getVendasDinheiro().subtract(antigoRelatorio.getTotal_de_vendas_dinheiro()).add(novoRelatorio.getTotal_de_vendas_dinheiro()));
        ano.setVendasCartaoCredito(ano.getVendasCartaoCredito().subtract(antigoRelatorio.getTotal_de_vendas_credito()).add(novoRelatorio.getTotal_de_vendas_credito()));
        ano.setVendasCartaoDebito(ano.getVendasCartaoDebito().subtract(antigoRelatorio.getTotal_de_vendas_debito()).add(novoRelatorio.getTotal_de_vendas_debito()));
        totalAnoRepository.save(ano);
    }

    private void editaMes(RelatorioDiario antigoRelatorio, RelatorioDiario novoRelatorio) {
        TotalMes mes = totalMesRepository.findByMesEAno(antigoRelatorio.getMes(), antigoRelatorio.getAno(), antigoRelatorio.getUser());
        mes.setTotalMes(mes.getTotalMes().subtract(antigoRelatorio.getTotal_de_vendas()).add(novoRelatorio.getTotal_de_vendas()));
        mes.setVendasDinheiro(mes.getVendasDinheiro().subtract(antigoRelatorio.getTotal_de_vendas_dinheiro()).add(novoRelatorio.getTotal_de_vendas_dinheiro()));
        mes.setVendasCartaoCredito(mes.getVendasCartaoCredito().subtract(antigoRelatorio.getTotal_de_vendas_credito()).add(novoRelatorio.getTotal_de_vendas_credito()));
        mes.setVendasCartaoDebito(mes.getVendasCartaoDebito().subtract(antigoRelatorio.getTotal_de_vendas_debito()).add(novoRelatorio.getTotal_de_vendas_debito()));
        totalMesRepository.save(mes);
    }

    private void somaMes(RelatorioDiario relatorio) {
        TotalMes atualizaMes= totalMesRepository.findByMesEAno(relatorio.getMes(), relatorio.getAno(), relatorio.getUser());
        User user =  userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());

        if (atualizaMes == null){
            TotalMes novoMes = new TotalMes();
            novoMes.setMes(relatorio.getMes());
            novoMes.setAno(relatorio.getAno());
            novoMes.setTotalMes(relatorio.getTotal_de_vendas());
            novoMes.setVendasDinheiro(relatorio.getTotal_de_vendas_dinheiro());
            novoMes.setVendasCartaoDebito(relatorio.getTotal_de_vendas_debito());
            novoMes.setVendasCartaoCredito(relatorio.getTotal_de_vendas_credito());
            novoMes.setUser(user);
            totalMesRepository.save(novoMes);
            return;
        }

        BigDecimal total = atualizaMes.getTotalMes().add(relatorio.getTotal_de_vendas());
        BigDecimal totalDinheiro = atualizaMes.getVendasDinheiro().add(relatorio.getTotal_de_vendas_dinheiro());
        BigDecimal totalDebito = atualizaMes.getVendasCartaoDebito().add(relatorio.getTotal_de_vendas_debito());
        BigDecimal totalCredito = atualizaMes.getVendasCartaoCredito().add(relatorio.getTotal_de_vendas_credito());
        atualizaMes.setTotalMes(total);
        atualizaMes.setVendasDinheiro(totalDinheiro);
        atualizaMes.setVendasCartaoDebito(totalDebito);
        atualizaMes.setVendasCartaoCredito(totalCredito);
        totalMesRepository.save(atualizaMes);
    }

    private void somaAno(RelatorioDiario relatorio) {
        User user =  userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        TotalAno atualizaAno = totalAnoRepository.findByAno(relatorio.getAno(), user);


        if (atualizaAno == null){
            TotalAno novoAno = new TotalAno();
            novoAno.setAno(relatorio.getAno());
            novoAno.setTotalAno(relatorio.getTotal_de_vendas());
            novoAno.setVendasDinheiro(relatorio.getTotal_de_vendas_dinheiro());
            novoAno.setVendasCartaoCredito(relatorio.getTotal_de_vendas_credito());
            novoAno.setVendasCartaoDebito(relatorio.getTotal_de_vendas_debito());
            novoAno.setUser(user);
            totalAnoRepository.save(novoAno);
            return;
        }

        BigDecimal total = atualizaAno.getTotalAno().add(relatorio.getTotal_de_vendas());
        BigDecimal totalDinheiro = atualizaAno.getVendasDinheiro().add(relatorio.getTotal_de_vendas_dinheiro());
        BigDecimal totalCredito = atualizaAno.getVendasCartaoCredito().add(relatorio.getTotal_de_vendas_credito());
        BigDecimal totalDebito = atualizaAno.getVendasCartaoDebito().add(relatorio.getTotal_de_vendas_debito());
        atualizaAno.setTotalAno(total);
        atualizaAno.setVendasCartaoDebito(totalDebito);
        atualizaAno.setVendasDinheiro(totalDinheiro);
        atualizaAno.setVendasCartaoCredito(totalCredito);
        totalAnoRepository.save(atualizaAno);
    }

    private RelatorioDiario converteRelatorio(RelatorioDiarioDTO relatorioDTO) {
        RelatorioDiario novoRelatorio = new RelatorioDiario();
        novoRelatorio.setAno(relatorioDTO.getAno());
        novoRelatorio.setMes(MesesDoAno.getNomeMes(relatorioDTO.getMes()));
        novoRelatorio.setDia(relatorioDTO.getDia());
        novoRelatorio.setTotal_de_vendas_dinheiro(relatorioDTO.getTotalDeVendasDinheiro());
        novoRelatorio.setTotal_de_vendas_credito(relatorioDTO.getTotalDeVendasCredito());
        novoRelatorio.setTotal_de_vendas_debito(relatorioDTO.getTotalDeVendasDebito());
        novoRelatorio.setTotal_de_vendas(somaTotal(relatorioDTO));

        return novoRelatorio;
    }

    private BigDecimal somaTotal(RelatorioDiarioDTO relatorioDTO) {
        BigDecimal total = relatorioDTO.getTotalDeVendasDinheiro().add(relatorioDTO.getTotalDeVendasCredito());
        return total.add(relatorioDTO.getTotalDeVendasDebito());
    }

    public RelatorioDiario relatorioQueFoiCriado(String mes, int dia, long ano, User usuarioLogado) {
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        return relatorioDiarioRepository.findByDiaMesEAno(mes, dia, ano, user);
    }

    public Optional<RelatorioDiario> buscaRelatorioPorId(long idRelatorio) {
        return relatorioDiarioRepository.findById(idRelatorio);
    }

    public List<Long> buscaAnos() {
        List<Long> anos = new ArrayList<>();
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        for (TotalAno ano : totalAnoRepository.findAllByUser(user.getId_usuario())){
            anos.add(ano.getAno());
        }
        anos.sort(Collections.reverseOrder());
        return anos;
    }

    public List<TotalAno> buscaRelatorioTodosAnos() {
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        return totalAnoRepository.findAllByUser(user.getId_usuario());
    }

    public List<TotalMes> buscaTodosMeses() {
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        return totalMesRepository.findAllByUser(user.getId_usuario());
    }

    public List<RelatorioDiario> buscaTodosDias() {
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        return relatorioDiarioRepository.findAllByUser(user.getId_usuario());
    }

    public void excluiRelatorio(long idRelatorio) {
        RelatorioDiario relatorioDiario = relatorioDiarioRepository.getReferenceById(idRelatorio);
        editaAnoPosExclusao(relatorioDiario);
        editaMesPosExclusao(relatorioDiario);
        relatorioDiarioRepository.deleteById(idRelatorio);
    }

    private void editaMesPosExclusao(RelatorioDiario relatorioDiario) {
        TotalMes mes = totalMesRepository.findByMesEAno(relatorioDiario.getMes(), relatorioDiario.getAno(), relatorioDiario.getUser());
        mes.setVendasCartaoDebito(mes.getVendasCartaoDebito().subtract(relatorioDiario.getTotal_de_vendas_debito()));
        mes.setTotalMes(mes.getTotalMes().subtract(relatorioDiario.getTotal_de_vendas()));
        mes.setVendasCartaoCredito(mes.getVendasCartaoCredito().subtract(relatorioDiario.getTotal_de_vendas_credito()));
        mes.setVendasDinheiro(mes.getVendasDinheiro().subtract(relatorioDiario.getTotal_de_vendas_dinheiro()));
        totalMesRepository.save(mes);
    }

    private void editaAnoPosExclusao(RelatorioDiario relatorioDiario) {
        TotalAno ano = totalAnoRepository.findByAno(relatorioDiario.getAno(), relatorioDiario.getUser());
        ano.setTotalAno(ano.getTotalAno().subtract(relatorioDiario.getTotal_de_vendas()));
        ano.setVendasDinheiro(ano.getVendasDinheiro().subtract(relatorioDiario.getTotal_de_vendas_dinheiro()));
        ano.setVendasCartaoDebito(ano.getVendasCartaoDebito().subtract(relatorioDiario.getTotal_de_vendas_debito()));
        ano.setVendasCartaoCredito(ano.getVendasCartaoCredito().subtract(relatorioDiario.getTotal_de_vendas_credito()));
        totalAnoRepository.save(ano);
    }

    public void atualizaRelatorio(RelatorioDiario relatorio, long idRelatorio) {
        RelatorioDiario relatorioBanco = relatorioDiarioRepository.getReferenceById(idRelatorio);
        relatorio.setTotal_de_vendas(relatorio.getTotal_de_vendas_credito().add(relatorio.getTotal_de_vendas_debito().add(relatorio.getTotal_de_vendas_dinheiro())));
        relatorio.setId_relatorio(idRelatorio);
        relatorio.setUser(relatorioBanco.getUser());

        if (!relatorio.getMes().equals(relatorioBanco.getMes()) || relatorio.getAno() != (relatorioBanco.getAno()) || relatorio.getDia() != relatorioBanco.getDia()) {
            relatorioBanco.setDia(relatorio.getDia());
            relatorioBanco.setMes(relatorio.getMes());
            relatorioBanco.setAno(relatorio.getAno());
        }
        editaMes(relatorioBanco, relatorio);
        editaAno(relatorioBanco, relatorio);
        relatorioDiarioRepository.save(relatorio);
    }
}


