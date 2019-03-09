package br.com.appcontazul.rest;

public class CredenciaisWsContazul {

    private final String URL = "https://wscontazul.herokuapp.com/wscontazul";

    // ENDPOINT /ex01
    private final String EP_EX01 = "/ex01";
    private final String PARAMETRO01_EX01 = "nomeUsuario";

    // ENDPOINT /inserirUsuario
    private final String EP_INSERIRUSUARIO = "/inserirUsuario";
    private final String PARAMETRO01_INSERIRUSUARIO = "nomeUsuario";
    private final String PARAMETRO02_INSERIRUSUARIO = "senha";

    // ENPOINT /ex08
    private final String EP_EX08 = "/ex08";
    private final String PARAMETRO01_EX08 = "nomeUsuario";
    private final String PARAMETRO02_EX08 = "senha";

    public CredenciaisWsContazul() {

    }

    public String getPathEpEx01() {

        String url = this.URL + this.EP_EX01 + "?" + this.PARAMETRO01_EX01 + "={nomeUsuario}";
        return url;
    }

    public String getPathEpInserirUsuario() {

        String url = this.URL + this.EP_INSERIRUSUARIO + "?" + this.PARAMETRO01_INSERIRUSUARIO + "={nomeUsuario}"
                + "&" + this.PARAMETRO02_INSERIRUSUARIO + "={senha}"
                ;
        return url;
    }

    public String getPathEpEx08() {

        String url = this.URL + this.EP_EX08 + "?" + this.PARAMETRO01_EX08 + "={nomeUsuario}"
                + "&" + this.PARAMETRO02_EX08 + "={senha}";
        return url;
    }
}
