package br.com.appcontazul.util.model;

public class ListaSimuladorNovoBeneficio {

    private String descricao;
    private double valor;

    public ListaSimuladorNovoBeneficio() {

    }

    public ListaSimuladorNovoBeneficio(String descricao, double valor) {

        this.descricao = descricao;
        this.valor = valor;
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
}
