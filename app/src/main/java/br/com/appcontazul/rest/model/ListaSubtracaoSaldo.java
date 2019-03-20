package br.com.appcontazul.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListaSubtracaoSaldo {

    private String descricao;
    private double valor;
    private String prioridade;

    public ListaSubtracaoSaldo(){

    }

    public ListaSubtracaoSaldo(String descricao, double valor, String prioridade){

        this.descricao = descricao;
        this.valor = valor;
        this.prioridade = prioridade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {this.descricao = descricao;    }

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
}
