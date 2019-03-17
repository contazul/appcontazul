package br.com.appcontazul.rest.model;

public class ListaSubtracaoSaldo {

    private String descricao;
    private double valorMovimentacao;
    private String prioridade;


    public ListaSubtracaoSaldo(String descricao, double valorMovimentacao,String prioridade){
        this.descricao = descricao;
        this.valorMovimentacao = valorMovimentacao;
        this.prioridade = prioridade;

    }

    public ListaSubtracaoSaldo(){

    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {this.descricao = descricao;    }

    public double getValorMovimentacao() {
        return valorMovimentacao;
    }

    public void setValorMovimentacao(double valorMovimentacao) {
        this.valorMovimentacao = valorMovimentacao;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }
}
