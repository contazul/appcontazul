package br.com.appcontazul.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListaContazul {

    private long numeroContazul;
    private String descricao;
    private String perfil;

    public ListaContazul(long numeroContazul, String descricao, String perfil) {
        this.numeroContazul = numeroContazul;
        this.descricao = descricao;
        this.perfil = perfil;
    }

    public ListaContazul() {

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

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}
