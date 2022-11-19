package BankProject.Contador.Model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class TotalMes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_do_mes;
    private String mes;
    private long ano;
    private BigDecimal vendasDinheiro;
    private BigDecimal vendasCartaoDebito;
    private BigDecimal vendasCartaoCredito;
    private BigDecimal totalMes;
    @ManyToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMes() {
        return mes;
    }

    public long getId_do_mes() {
        return id_do_mes;
    }

    public void setId_do_mes(long id_do_mes) {
        this.id_do_mes = id_do_mes;
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

    public BigDecimal getVendasDinheiro() {
        return vendasDinheiro;
    }

    public void setVendasDinheiro(BigDecimal vendasDinheiro) {
        this.vendasDinheiro = vendasDinheiro;
    }

    public BigDecimal getVendasCartaoDebito() {
        return vendasCartaoDebito;
    }

    public void setVendasCartaoDebito(BigDecimal vendasCartaoDebito) {
        this.vendasCartaoDebito = vendasCartaoDebito;
    }

    public BigDecimal getVendasCartaoCredito() {
        return vendasCartaoCredito;
    }

    public void setVendasCartaoCredito(BigDecimal vendasCartaoCredito) {
        this.vendasCartaoCredito = vendasCartaoCredito;
    }

    public BigDecimal getTotalMes() {
        return totalMes;
    }

    public void setTotalMes(BigDecimal totalMes) {
        this.totalMes = totalMes;
    }
}
