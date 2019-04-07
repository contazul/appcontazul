package br.com.appcontazul.rest.model;

public class ListaContasAPagar {

    private String descricao;
    private double valor;
    private String ultimaDataPagamento;
    private String prioridade;
    private int quantidadeParcelas;
    private int quantidadePagas;

    public ListaContasAPagar(){

    }


    public ListaContasAPagar(String descricao, double valor, String ultimaDataPagamento, String prioridade, int quantidadeParcelas, int quantidadePagas) {
        this.descricao = descricao;
        this.valor = valor;
        this.ultimaDataPagamento = ultimaDataPagamento;
        this.prioridade = prioridade;
        this.quantidadeParcelas = quantidadeParcelas;
        this.quantidadePagas = quantidadePagas;
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

    public String getUltimaDataPagamento() {
        return ultimaDataPagamento;
    }

    public void setUltimaDataPagamento(String ultimaDataPagamento) {
        this.ultimaDataPagamento = ultimaDataPagamento;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public int getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(int quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public int getQuantidadePagas() {
        return quantidadePagas;
    }

    public void setQuantidadePagas(int quantidadePagas) {
        this.quantidadePagas = quantidadePagas;
    }


}
