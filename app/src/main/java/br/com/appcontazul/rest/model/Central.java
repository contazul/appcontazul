package br.com.appcontazul.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Central {

    private String status;
    private double saldo;
    private double totalSubtracaoSaldoBaixaPrioridade;
    private double totalSubtracaoSaldoGeral;
    private double totalBeneficioMensal;
    private double totalSomaSaldo;
    private double percentualEconomizado;
    private double totalDividaMensal;
    private double totalDividaMensalBaixaPrioridade;


    public Central() {

    }

    public Central(String status, double saldo, double totalSubtracaoSaldoBaixaPrioridade,
                   double totalSubtracaoSaldoGeral, double totalBeneficioMensal,
                   double totalSomaSaldo, double percentualEconomizado, double totalDividaMensal,
                   double totalDividaMensalBaixaPrioridade) {

        this.status = status;
        this.saldo = saldo;
        this.totalSubtracaoSaldoBaixaPrioridade = totalSubtracaoSaldoBaixaPrioridade;
        this.totalSubtracaoSaldoGeral = totalSubtracaoSaldoGeral;
        this.totalBeneficioMensal = totalBeneficioMensal;
        this.totalSomaSaldo = totalSomaSaldo;
        this.percentualEconomizado = percentualEconomizado;
        this.totalDividaMensal = totalDividaMensal;
        this.totalDividaMensalBaixaPrioridade = totalDividaMensalBaixaPrioridade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getTotalSubtracaoSaldoBaixaPrioridade() {
        return totalSubtracaoSaldoBaixaPrioridade;
    }

    public void setTotalSubtracaoSaldoBaixaPrioridade(double totalSubtracaoSaldoBaixaPrioridade) {
        this.totalSubtracaoSaldoBaixaPrioridade = totalSubtracaoSaldoBaixaPrioridade;
    }

    public double getTotalSubtracaoSaldoGeral() {
        return totalSubtracaoSaldoGeral;
    }

    public void setTotalSubtracaoSaldoGeral(double totalSubtracaoSaldoGeral) {
        this.totalSubtracaoSaldoGeral = totalSubtracaoSaldoGeral;
    }

    public double getTotalBeneficioMensal() {
        return totalBeneficioMensal;
    }

    public void setTotalBeneficioMensal(double totalBeneficioMensal) {
        this.totalBeneficioMensal = totalBeneficioMensal;
    }

    public double getTotalSomaSaldo() {
        return totalSomaSaldo;
    }

    public void setTotalSomaSaldo(double totalSomaSaldo) {
        this.totalSomaSaldo = totalSomaSaldo;
    }

    public double getPercentualEconomizado() {
        return percentualEconomizado;
    }

    public void setPercentualEconomizado(double percentualEconomizado) {
        this.percentualEconomizado = percentualEconomizado;
    }

    public double getTotalDividaMensal() {
        return totalDividaMensal;
    }

    public void setTotalDividaMensal(double totalDividaMensal) {
        this.totalDividaMensal = totalDividaMensal;
    }

    public double getTotalDividaMensalBaixaPrioridade() {
        return totalDividaMensalBaixaPrioridade;
    }

    public void setTotalDividaMensalBaixaPrioridade(double totalDividaMensalBaixaPrioridade) {
        this.totalDividaMensalBaixaPrioridade = totalDividaMensalBaixaPrioridade;
    }
}
