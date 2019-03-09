package br.com.appcontazul.rest.model;

public class ListaContazul {

    private long numeroContazul;
    private String descricao;
    private String perfilConta;

    public ListaContazul(long numeroContazul, String descricao, String perfilConta) {
        this.numeroContazul = numeroContazul;
        this.descricao = descricao;
        this.perfilConta = perfilConta;
    }

    public long getNumeroContazul() {
        return numeroContazul;
    }

    public void setNumeroContazul(long numeroContazul) {
        this.numeroContazul = numeroContazul;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPerfilConta() {
        return perfilConta;
    }

    public void setPerfilConta(String perfilConta) {
        this.perfilConta = perfilConta;
    }
}
