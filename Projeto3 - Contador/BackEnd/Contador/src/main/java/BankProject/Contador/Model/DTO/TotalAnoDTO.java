package BankProject.Contador.Model.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TotalAnoDTO {
    private long ano;
    private BigDecimal vendasDinheiro;
    private BigDecimal vendasCartaoDebito;
    private BigDecimal vendasCartaoCredito;
    private BigDecimal totalAno;

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
