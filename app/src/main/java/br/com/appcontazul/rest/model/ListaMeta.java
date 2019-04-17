package br.com.appcontazul.rest.model;

public class ListaMeta {


    private String descricao;
    private double valor;
    private double valorMinimoEco;
    private double valorParaAtingir;
    private String strUltimaDataPagamento;
    private String status;
    private int quantidadeParcela;
    private int quantidadePaga;
    private boolean pago;

    public ListaMeta (){

    }

    public ListaMeta(String descricao, double valor, double valorMinimoEco, double valorParaAtingir, String strUltimaDataPagamento, String status, int quantidadeParcela, int quantidadePaga) {
        this.descricao = descricao;
        this.valor = valor;
        this.valorMinimoEco = valorMinimoEco;
        this.valorParaAtingir = valorParaAtingir;
        this.strUltimaDataPagamento = strUltimaDataPagamento;
        this.status = status;
        this.quantidadeParcela = quantidadeParcela;
        this.quantidadePaga = quantidadePaga;

    }




    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValorMinimoEco() {
        return valorMinimoEco;
    }

    public void setValorMinimoEco(double valorMinimoEco) {
        this.valorMinimoEco = valorMinimoEco;
    }

    public double getValorParaAtingir() {
        return valorParaAtingir;
    }

    public void setValorParaAtingir(double valorParaAtingir) {
        this.valorParaAtingir = valorParaAtingir;
    }

    public String getStrUltimaDataPagamento() {
        return strUltimaDataPagamento;
    }

    public void setStrUltimaDataPagamento(String strUltimaDataPagamento) {
        this.strUltimaDataPagamento = strUltimaDataPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantidadeParcela() {
        return quantidadeParcela;
    }

    public void setQuantidadeParcela(int quantidadeParcela) {
        this.quantidadeParcela = quantidadeParcela;
    }

    public int getQuantidadePaga() {
        return quantidadePaga;
    }

    public void setQuantidadePaga(int quantidadePaga) {
        this.quantidadePaga = quantidadePaga;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }
}
