package BankProject.Contador.Model.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RelatorioDiarioDTO {
    private int dia;
    private int mes;
    private long ano;
    private BigDecimal totalDeVendasDinheiro;
    private BigDecimal totalDeVendasDebito;
    private BigDecimal totalDeVendasCredito;
    private BigDecimal totalDeVendas;

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }
    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public long getAno() {
        return ano;
    }

    public void setAno(long ano) {
        this.ano = ano;
    }

    public BigDecimal getTotalDeVendasDinheiro() {
        return totalDeVendasDinheiro;
    }

    public void setTotalDeVendasDinheiro(BigDecimal totalDeVendasDinheiro) {
        this.totalDeVendasDinheiro = totalDeVendasDinheiro;
    }

    public BigDecimal getTotalDeVendasDebito() {
        return totalDeVendasDebito;
    }

    public void setTotalDeVendasDebito(BigDecimal totalDeVendasDebito) {
        this.totalDeVendasDebito = totalDeVendasDebito;
    }

    public BigDecimal getTotalDeVendasCredito() {
        return totalDeVendasCredito;
    }

    public void setTotalDeVendasCredito(BigDecimal totalDeVendasCredito) {
        this.totalDeVendasCredito = totalDeVendasCredito;
    }

    public BigDecimal getTotalDeVendas() {
        return totalDeVendas;
    }

    public void setTotalDeVendas(BigDecimal totalDeVendas) {
        this.totalDeVendas = totalDeVendas;
    }
}
