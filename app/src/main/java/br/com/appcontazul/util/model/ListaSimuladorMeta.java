package br.com.appcontazul.util.model;

public class ListaSimuladorMeta {

    private long id;
    private String descricao;

    public ListaSimuladorMeta() {

    }

    public ListaSimuladorMeta(long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
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
}
