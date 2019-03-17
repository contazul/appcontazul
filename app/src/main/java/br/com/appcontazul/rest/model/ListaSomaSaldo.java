package br.com.appcontazul.rest.model;

public class ListaSomaSaldo {
    private String descricao;
    private double valorMovimentacao;


    public ListaSomaSaldo(String descricao, double valorMovimentacao){
        this.descricao = descricao;
        this.valorMovimentacao = valorMovimentacao;

    }

    public ListaSomaSaldo(){

    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorMovimentacao() {
        return valorMovimentacao;
    }

    public void setValorMovimentacao(double valorMovimentacao) {
        this.valorMovimentacao = valorMovimentacao;
    }
}
