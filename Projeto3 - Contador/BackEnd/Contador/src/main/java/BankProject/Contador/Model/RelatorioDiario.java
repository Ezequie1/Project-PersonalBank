package BankProject.Contador.Model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class RelatorioDiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_relatorio;
    private int dia;
    private String mes;
    private long ano;
    private BigDecimal total_de_vendas_dinheiro;
    private BigDecimal total_de_vendas_debito;
    private BigDecimal total_de_vendas_credito;
    private BigDecimal total_de_vendas;

    @ManyToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getTotal_de_vendas() {
        return total_de_vendas;
    }

    public void setTotal_de_vendas(BigDecimal total_de_vendas) {
        this.total_de_vendas = total_de_vendas;
    }

    public long getId_relatorio() {
        return id_relatorio;
    }

    public void setId_relatorio(long id_relatorio) {
        this.id_relatorio = id_relatorio;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public long getAno() {
        return ano;
    }

    public void setAno(long ano) {
        this.ano = ano;
    }

    public BigDecimal getTotal_de_vendas_dinheiro() {
        return total_de_vendas_dinheiro;
    }

    public void setTotal_de_vendas_dinheiro(BigDecimal total_de_vendas_dinheiro) {
        this.total_de_vendas_dinheiro = total_de_vendas_dinheiro;
    }

    public BigDecimal getTotal_de_vendas_debito() {
        return total_de_vendas_debito;
    }

    public void setTotal_de_vendas_debito(BigDecimal total_de_vendas_debito) {
        this.total_de_vendas_debito = total_de_vendas_debito;
    }

    public BigDecimal getTotal_de_vendas_credito() {
        return total_de_vendas_credito;
    }

    public void setTotal_de_vendas_credito(BigDecimal total_de_vendas_credito) {
        this.total_de_vendas_credito = total_de_vendas_credito;
    }
}
