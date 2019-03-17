package br.com.appcontazul.rest.model;

import android.widget.Button;

public class ListaLucroMensal {

    private String descricaoBeneficio;
    private double valorBeneficio;
    private String data;
    private Button buttonReceber;
    private Button buttonExcluir;

    public ListaLucroMensal (String descricaoBeneficio, double valorBeneficio, String data, Button buttonReceber, Button buttonExcluir ){

        this.descricaoBeneficio = descricaoBeneficio;
        this.valorBeneficio = valorBeneficio;
        this.data = data;
        this.buttonReceber = buttonReceber;
        this.buttonExcluir = buttonExcluir;

    }

    public ListaLucroMensal(String s, double v, String s1){

    }

    public String getDescricaoBeneficio() {
        return descricaoBeneficio;
    }

    public void setDescricaoBeneficio(String descricaoBeneficio) {
        this.descricaoBeneficio = descricaoBeneficio;
    }

    public double getValorBeneficio() {
        return valorBeneficio;
    }

    public void setValorBeneficio(double valorBeneficio) {
        this.valorBeneficio = valorBeneficio;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Button getButtonReceber() {
        return buttonReceber;
    }

    public void setButtonReceber(Button buttonReceber) {
        this.buttonReceber = buttonReceber;
    }

    public Button getButtonExcluir() {
        return buttonExcluir;
    }

    public void setButtonExcluir(Button buttonExcluir) {
        this.buttonExcluir = buttonExcluir;
    }
}
