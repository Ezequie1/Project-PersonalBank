package BankProject.Contador.Model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class TotalAno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_do_ano;
    private long ano;
    private BigDecimal vendasDinheiro;
    private BigDecimal vendasCartaoDebito;
    private BigDecimal vendasCartaoCredito;
    private BigDecimal totalAno;
    @ManyToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId_do_ano() {
        return id_do_ano;
    }

    public void setId_do_ano(long id_do_ano) {
        this.id_do_ano = id_do_ano;
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

    public BigDecimal getTotalAno() {
        return totalAno;
    }

    public void setTotalAno(BigDecimal totalAno) {
        this.totalAno = totalAno;
    }
}
