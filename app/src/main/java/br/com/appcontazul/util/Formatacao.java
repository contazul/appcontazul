package br.com.appcontazul.util;

import java.text.NumberFormat;

public class Formatacao {

    public String formatarValorMonetario(String valor) {

        try {
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            return nf.format(Double.parseDouble(valor) / 100);
        } catch (Exception e) {

            return "R$0,00";
        }
    }
}
