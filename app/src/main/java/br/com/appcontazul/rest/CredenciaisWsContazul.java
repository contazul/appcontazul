package br.com.appcontazul.rest;

public class CredenciaisWsContazul {

    private final String URL = "https://wscontazul.herokuapp.com/wscontazul";
    private final String URL2 = "https://wscontazul.herokuapp.com/wscontazul/subtracaoDeSaldo";
    private final String URL3 = "https://wscontazul.herokuapp.com/wscontazul/lucroMensal";
    private final String URL4 = "https://wscontazul.herokuapp.com/wscontazul/central";

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

    // ENDPOINT /saldo
    private final String EP_SALDO = "/saldo";
    private final String PARAMETRO01_SALDO = "numeroContazul";

    // ENDPOINT /inserirSomaSaldo
    private final String EP_INSERIRSOMASALDO = "/inserirSomaSaldo";
    private final String PARAMETRO01_INSERIRSOMASALDO = "numeroContazul";
    private final String PARAMETRO02_INSERIRSOMASALDO = "valor";
    private final String PARAMETRO03_INSERIRSOMASALDO = "descricao";

    // ENDPOINT /listaSomaSaldo
    private final String EP_LISTASOMASALDO = "/listaSomaSaldo";
    private final String PARAMETRO01_LISTASOMASALDO = "numeroContazul";

    // ENDPOINT /inserirSubtracaoSaldo
    private final String EP_INSERIRSUBTRACAOSALDO = "/inserirSubtracaoSaldo";
    private final String PARAMETRO01_INSERIRSUBTRACAOSALDO = "numeroContazul";
    private final String PARAMETRO02_INSERIRSUBTRACAOSALDO = "valor";
    private final String PARAMETRO03_INSERIRSUBTRACAOSALDO = "descricao";
    private final String PARAMETRO04_INSERIRSUBTRACAOSALDO = "prioridade";

    // ENDPOINT /listaDeSubtracaoSaldo
    private final String EP_LISTADESUBTRACAOSALDO = "/listaDeSubtracaoSaldo";
    private final String PARAMETRO01_LISTADESUBTRACAOSALDO = "numeroContazul";

    // ENDPOINT /inserirLucroMensal
    private final String EP_INSERIRLUCROMENSAL = "/inserirLucroMensal";
    private final String PARAMETRO01_INSERIRLUCROMENSAL = "numeroContazul";
    private final String PARAMETRO02_INSERIRLUCROMENSAL = "descricao";
    private final String PARAMETRO03_INSERIRLUCROMENSAL = "valor";

    // ENDPOINT /listaDeLucroMensal
    private final String EP_LISTADELUCROMENSAL = "/listaDeLucroMensal";
    private final String PARAMETRO01_LISTADELUCROMENSAL = "numeroContazul";

    // ENDPOINT /receber
    private final String EP_RECEBER = "/receber";
    private final String PARAMETRO01_RECEBER = "id";
    private final String PARAMETRO02_RECEBER = "numeroContazul";

    // ENDPOINT /excluirLucroMensal
    private final String EP_EXCLUIRLUCROMENSAL = "/excluirLucroMensal";
    private final String PARAMETRO01_EXCLUIRLUCROMENSAL = "id";

    // ENDPOINT /informacoesCentral
    private final String EP_INFORMACOESCENTRAL = "/informacoesCentral";
    private final String PARAMETRO01_INFORMACOESCENTRAL = "numeroContazul";

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

    public String getPathEpSaldo() {

        String url = this.URL + this.EP_SALDO + "?" + this.PARAMETRO01_SALDO + "={numeroContazul}";
        return url;
    }

    public String getPathEpInserirSomaSaldo() {

        String url = this.URL + this.EP_INSERIRSOMASALDO + "?"
                + this.PARAMETRO01_INSERIRSOMASALDO + "={numeroContazul}"
                + "&" + this.PARAMETRO02_INSERIRSOMASALDO + "={valor}"
                + "&" + this.PARAMETRO03_INSERIRSOMASALDO + "={descricao}"
                ;
        return url;
    }

    public String getPathEpListaSomaSaldo() {

        String url = this.URL + this.EP_LISTASOMASALDO + "?" + this.PARAMETRO01_LISTASOMASALDO + "={numeroContazul}";
        return url;
    }

    public String getPathEpInserirSubtracaoSaldo() {

        String url = this.URL2 + this.EP_INSERIRSUBTRACAOSALDO + "?"
                + this.PARAMETRO01_INSERIRSUBTRACAOSALDO + "={numeroContazul}"
                + "&" + this.PARAMETRO02_INSERIRSUBTRACAOSALDO + "={valor}"
                + "&" + this.PARAMETRO03_INSERIRSUBTRACAOSALDO + "={descricao}"
                + "&" + this.PARAMETRO04_INSERIRSUBTRACAOSALDO + "={prioridade}";
        return url;
    }

    public String getPathEpListaDeSubtracaoSaldo() {

        String url = this.URL2 + this.EP_LISTADESUBTRACAOSALDO + "?" + this.PARAMETRO01_LISTADESUBTRACAOSALDO + "={numeroContazul}";
        return url;
    }

    public String getPathEpInserirLucroMensal() {

        String url = this.URL3 + this.EP_INSERIRLUCROMENSAL + "?"
                + this.PARAMETRO01_INSERIRLUCROMENSAL + "={numeroContazul}"
                + "&" + this.PARAMETRO02_INSERIRLUCROMENSAL + "={descricao}"
                + "&" + this.PARAMETRO03_INSERIRLUCROMENSAL + "={valor}"
                ;
        return url;
    }

    public String getPathEpListaDeLucroMensal() {

        String url = this.URL3 + this.EP_LISTADELUCROMENSAL + "?"
                + this.PARAMETRO01_LISTADELUCROMENSAL + "={numeroContazul}";
        return url;
    }

    public String getPathEpReceber() {

        String url = this.URL3 + this.EP_RECEBER + "?"
                + this.PARAMETRO01_RECEBER + "={id}"
                + "&" + this.PARAMETRO02_RECEBER + "={numeroContazul}"
                ;
        return url;
    }

    public String getPathEpExcluirLucroMensal() {

        String url = this.URL3 + this.EP_EXCLUIRLUCROMENSAL + "?"
                + this.PARAMETRO01_EXCLUIRLUCROMENSAL + "={id}";
        return url;
    }

    public String getPathEpInformacoesCentral() {

        String url = this.URL4 + this.EP_INFORMACOESCENTRAL + "?"
                + this.PARAMETRO01_INFORMACOESCENTRAL + "={numeroContazul}";
        return url;
    }
}

















