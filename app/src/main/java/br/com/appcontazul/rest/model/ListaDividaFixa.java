package br.com.appcontazul.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListaDividaFixa {

    private long id;
    private String descricao;
    private double valor;
    private String prioridade;
    private int quantidadeParcela;

    public ListaDividaFixa() {

        this.id = 0;
        this.descricao = "";
        this.valor = 0.0;
        this.prioridade = "";
        this.quantidadeParcela = 0;
    }

    public ListaDividaFixa(long id, String descricao, double valor, String prioridade, int quantidadeParcela) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.prioridade = prioridade;
        this.quantidadeParcela = quantidadeParcela;
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

    public String getStrQuantidadeParcela() {

        return this.quantidadeParcela + " vezes";
    }
}
