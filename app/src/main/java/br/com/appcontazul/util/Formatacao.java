package br.com.appcontazul.util;

import java.text.DecimalFormat;
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

    public String formatarValorPercentual(double valor) {

        DecimalFormat fmt = new DecimalFormat("0.00");
        String strValor = fmt.format(valor);
        strValor = strValor.replace(".",",");
        return strValor + "%";
    }
}
