package br.com.appcontazul.util.model;

public class ListaSimuladorRemoverBeneficio {

    private long id;
    private String descricao;
    private double valor;

    public ListaSimuladorRemoverBeneficio() {

    }

    public ListaSimuladorRemoverBeneficio(long id, String descricao, double valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
