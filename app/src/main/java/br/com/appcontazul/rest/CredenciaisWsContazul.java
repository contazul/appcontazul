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

    // ENDPOINT /inserirContazul
    private final String EP_INSERIRCONTAZUL = "/inserirContazul";
    private final String PARAMETRO01_INSERIRCONTAZUL = "nomeUsuario";

    // ENDPOINT /listaDeContazul
    private final String EP_LISTADECONTAZUL = "/listaDeContazul";
    private final String PARAMETRO01_LISTADECONTAZUL = "nomeUsuario";

    // ENDPOINT /perfilContazul
    private final String EP_PERFILCONTAZUL = "/perfilContazul";
    private final String PARAMETRO01_PERFILCONTAZUL = "numeroContazul";

    // ENDPOINT /atualizarPerfilContazul
    private final String EP_ATUALIZARPERFILCONTAZUL = "/atualizarPerfilContazul";
    private final String PARAMETRO01_ATUALIZARPERFILCONTAZUL = "numeroContazul";
    private final String PARAMETRO02_ATUALIZARPERFILCONTAZUL = "descricao";
    private final String PARAMETRO03_ATUALIZARPERFILCONTAZUL = "valorIdeal";

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

    public String getPathEpInserirContazul() {

        String url = this.URL + this.EP_INSERIRCONTAZUL + "?" + this.PARAMETRO01_INSERIRCONTAZUL + "={nomeUsuario}";
        return url;
    }

    public String getPathEpListaDeContazul() {

        String url = this.URL + this.EP_LISTADECONTAZUL + "?" + this.PARAMETRO01_LISTADECONTAZUL + "={nomeUsuario}";
        return url;
    }

    public String getPathPerfilContazul() {

        String url = this.URL + this.EP_PERFILCONTAZUL + "?" + this.PARAMETRO01_PERFILCONTAZUL + "={numeroContazul}";
        return url;
    }

    public String getPathEpAtualizarPerfilContazul() {

        String url = this.URL + this.EP_ATUALIZARPERFILCONTAZUL + "?"
                + this.PARAMETRO01_ATUALIZARPERFILCONTAZUL + "={numeroContazul}"
                + "&" + this.PARAMETRO02_ATUALIZARPERFILCONTAZUL + "={descricao}"
                + "&" + this.PARAMETRO03_ATUALIZARPERFILCONTAZUL + "={valorIdeal}"
                ;
        return url;
    }
}
