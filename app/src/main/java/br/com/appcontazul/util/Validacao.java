package br.com.appcontazul.util;

public class Validacao {

    public boolean contemCaracterEspecial(String str) {

        for (int i = 0; i < str.length();i++ ) {
            if (!Character.isAlphabetic((str.charAt(i)))) {

                boolean isNumero = false;
                try {

                    Integer.parseInt("" + str.charAt(i));
                    isNumero = true;
                } catch (Exception e) {

                    return true;
                }

                if (!isNumero) {

                    return true;
                }
            }
        }

        return false;
    }
}
