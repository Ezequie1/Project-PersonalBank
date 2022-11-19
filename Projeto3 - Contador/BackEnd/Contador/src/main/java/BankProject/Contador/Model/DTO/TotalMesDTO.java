package BankProject.Contador.Model.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TotalMesDTO {
    private String mes;
    private long ano;
    private BigDecimal vendasDinheiro;
    private BigDecimal vendasCartaoDebito;
    private BigDecimal vendasCartaoCredito;
    private BigDecimal totalMes;

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
