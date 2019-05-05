package br.com.appcontazul.util.model;

public class ListaSimuladorAdesaoDivida {

    private String descricao;
    private double valor;
    private String prioridade;
    private boolean aPrazo;
    private int quantidadeParcela;

    public ListaSimuladorAdesaoDivida() {

        this.descricao = "";
        this.valor = 0.0;
        this.prioridade = "";
        this.aPrazo = false;
        this.quantidadeParcela = 0;
    }

    public ListaSimuladorAdesaoDivida(String descricao, double valor, String prioridade, boolean aPrazo, int quantidadeParcela) {
        this.descricao = descricao;
        this.valor = valor;
        this.prioridade = prioridade;
        this.aPrazo = aPrazo;
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

    public boolean isaPrazo() {
        return aPrazo;
    }

    public void setaPrazo(boolean aPrazo) {
        this.aPrazo = aPrazo;
    }

    public int getQuantidadeParcela() {
        return quantidadeParcela;
    }

    public void setQuantidadeParcela(int quantidadeParcela) {
        this.quantidadeParcela = quantidadeParcela;
    }

    public String getStrQuantidadeParcela() {

        return this.quantidadeParcela + " Vezes";
    }
}
