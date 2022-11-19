package BankProject.Contador.Controller;

import BankProject.Contador.Model.DTO.RelatorioDiarioDTO;
import BankProject.Contador.Model.Enums.MesesDoAno;
import BankProject.Contador.Model.RelatorioDiario;
import BankProject.Contador.Model.User;
import BankProject.Contador.Service.RelatorioService;
import BankProject.Contador.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class HomeController {

    @Autowired
    private RelatorioService relatorioService;
    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String confirmLogin(Model model) {
        User usuarioLogado = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("usuarioLogado", usuarioLogado);
        model.addAttribute("todosAnos", relatorioService.buscaAnos());
        model.addAttribute("valorAnos", relatorioService.buscaRelatorioTodosAnos());
        model.addAttribute("valorMeses", relatorioService.buscaTodosMeses());
        model.addAttribute("valorDia", relatorioService.buscaTodosDias());
        return "Home";
    }

    @GetMapping("/NovoRelatorioDiario")
    public String novoRelatorio(Model model){
        User usuarioLogado = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("usuarioLogado", usuarioLogado);
        model.addAttribute("newRelatorio", new RelatorioDiarioDTO());
        return "NovoRelatorio";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute RelatorioDiarioDTO novoRelatorio, BindingResult result, Model model){
        User usuarioLogado = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("usuarioLogado", usuarioLogado);
        if(relatorioService.novoRelatorio(novoRelatorio)){
            RelatorioDiario relatorioCriado = relatorioService.relatorioQueFoiCriado(MesesDoAno.getNomeMes(novoRelatorio.getMes()), novoRelatorio.getDia(), novoRelatorio.getAno(), usuarioLogado);
            model.addAttribute("relatorioCriado", relatorioCriado);
            return "RelatorioCriado";
        }else {
            return "ErroCriarRelatorio";
        }
    }

    @GetMapping("/EditarRelatorio/{idRelatorio}")
    public String editaRelatorio(Model model, @PathVariable("idRelatorio") long idRelatorio){
        User usuarioLogado = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        Optional<RelatorioDiario> relatorio = relatorioService.buscaRelatorioPorId(idRelatorio);
        if (relatorio.isPresent()){

            model.addAttribute("usuarioLogado", usuarioLogado);
            model.addAttribute("newRelatorio", relatorio.get());
            return "EditarRelatorio";
        }
        return null;
    }

    @PostMapping("/atualiza/{idRelatorio}")
    public String atualizaRelatorio(@ModelAttribute RelatorioDiario relatorio, BindingResult result, Model model, @PathVariable("idRelatorio") long idRelatorio) {
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("usuarioLogado", user);
        relatorioService.atualizaRelatorio(relatorio, idRelatorio);
        Optional<RelatorioDiario> relatorioAtualizado = relatorioService.buscaRelatorioPorId(idRelatorio);
        relatorioAtualizado.ifPresent(relatorioDiario -> model.addAttribute("relatorioCriado", relatorioDiario));
        return "RelatorioAtualizado";
    }

    @GetMapping("ExcluirRelatorio/{idRelatorio}")
    public String excluiRelatorio(@PathVariable("idRelatorio") long idRelatorio){
        relatorioService.excluiRelatorio(idRelatorio);
        return "redirect:/home";
    }

    @GetMapping("/ErroDatabase")
    public String erroCriarRelatorio(Model model){
        User usuarioLogado = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("usuarioLogado", usuarioLogado);
        return "ErroCriarRelatorio";
    }
}
