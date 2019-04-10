package br.com.appcontazul.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListaContasAPagar {

    private long id;
    private String descricao;
    private double valor;
    private String strUltimaDataPagamento;
    private String prioridade;
    private int quantidadeParcela;
    private int quantidadePaga;

    public ListaContasAPagar(){

    }


    public ListaContasAPagar(long id, String descricao, double valor, String strUltimaDataPagamento, String prioridade, int quantidadeParcela, int quantidadePaga) {
        this.descricao = descricao;
        this.valor = valor;
        this.strUltimaDataPagamento = strUltimaDataPagamento;
        this.prioridade = prioridade;
        this.quantidadeParcela = quantidadeParcela;
        this.quantidadePaga = quantidadePaga;
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

    public String getStrUltimaDataPagamento() {
        return strUltimaDataPagamento;
    }

    public void setStrUltimaDataPagamento(String strUltimaDataPagamento) {
        this.strUltimaDataPagamento = strUltimaDataPagamento;
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

    public int getQuantidadePaga() {
        return quantidadePaga;
    }

    public void setQuantidadePaga(int quantidadePaga) {
        this.quantidadePaga = quantidadePaga;
    }
}
