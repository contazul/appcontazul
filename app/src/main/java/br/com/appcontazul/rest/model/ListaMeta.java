package br.com.appcontazul.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListaMeta {

    private Long id;
    private String descricao;
    private Double valor;
    private double valorRestante;
    private String status;
    private boolean isPodeAplicar;
    private Double valorEconomizar;
    private Integer isAvista;

    public ListaMeta() {

    }

    public ListaMeta(Long id, String descricao, Double valor, double valorRestante, String status,
                     boolean isPodeAplicar, Double valorEconomizar, Integer isAvista) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.valorRestante = valorRestante;
        this.status = status;
        this.isPodeAplicar = isPodeAplicar;
        this.valorEconomizar = valorEconomizar;
        this.isAvista = isAvista;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public double getValorRestante() {
        return valorRestante;
    }

    public void setValorRestante(double valorRestante) {
        this.valorRestante = valorRestante;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isPodeAplicar() {
        return isPodeAplicar;
    }

    public void setPodeAplicar(boolean podeAplicar) {
        isPodeAplicar = podeAplicar;
    }

    public Double getValorEconomizar() {
        return valorEconomizar;
    }

    public void setValorEconomizar(Double valorEconomizar) {
        this.valorEconomizar = valorEconomizar;
    }

    public Integer getIsAvista() {
        return isAvista;
    }

    public void setIsAvista(Integer isAvista) {
        this.isAvista = isAvista;
    }
}
