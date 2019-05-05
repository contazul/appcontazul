package br.com.appcontazul.rest.model;

public class InclusaoDivida {

    private String descricao;
    private double valor;
    private String prioridade;
    private int quantidadeParcela;

    public InclusaoDivida() {

        this.descricao = "";
        this.valor = 0.0;
        this.prioridade = "";
        this.quantidadeParcela = 0;
    }

    public InclusaoDivida(String descricao, double valor, String prioridade, int quantidadeParcela) {
        this.descricao = descricao;
        this.valor = valor;
        this.prioridade = prioridade;
        this.quantidadeParcela = quantidadeParcela;
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

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public int getQuantidadeParcela() {
        return quantidadeParcela;
    }

    public void setQuantidadeParcela(int quantidadeParcela) {
        this.quantidadeParcela = quantidadeParcela;
    }
}
