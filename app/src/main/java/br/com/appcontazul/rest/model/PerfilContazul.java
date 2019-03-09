package br.com.appcontazul.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PerfilContazul {

    private String perfil;
    private long numeroContazul;
    private String status;

    public PerfilContazul() {

    }

    public PerfilContazul(String perfil, long numeroContazul, String status) {

        this.perfil = perfil;
        this.numeroContazul = numeroContazul;
        this.status = status;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public long getNumeroContazul() {
        return numeroContazul;
    }

    public void setNumeroContazul(long numeroContazul) {
        this.numeroContazul = numeroContazul;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
