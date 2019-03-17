package br.com.appcontazul.rest.teste;

import java.util.ArrayList;
import java.util.List;


import br.com.appcontazul.rest.model.ListaSubtracaoSaldo;

public class ListaSubtracaoSaldoRepository {

    List<ListaSubtracaoSaldo> listaSubtracaoSaldo;


    public ListaSubtracaoSaldoRepository() {

        this.listaSubtracaoSaldo = new ArrayList<>();
    }


    public List<ListaSubtracaoSaldo> getListaSubtracaoSaldo(){


        this.listaSubtracaoSaldo.add(new ListaSubtracaoSaldo("000000000000", 1.550,"Alta"));
        this.listaSubtracaoSaldo.add(new ListaSubtracaoSaldo("000000000000", 1.550,"Baixa"));
        this.listaSubtracaoSaldo.add(new ListaSubtracaoSaldo("000000000000", 1.550,"Alta"));
        this.listaSubtracaoSaldo.add(new ListaSubtracaoSaldo("000000000000", 1.550,"Baixa"));
        this.listaSubtracaoSaldo.add(new ListaSubtracaoSaldo("000000000000", 1.550,"Alta"));
        this.listaSubtracaoSaldo.add(new ListaSubtracaoSaldo("000000000000", 1.550,"Baixa"));
        this.listaSubtracaoSaldo.add(new ListaSubtracaoSaldo("000000000000", 1.550,"Alta"));
        this.listaSubtracaoSaldo.add(new ListaSubtracaoSaldo("000000000000", 1.550,"Baixa"));
        this.listaSubtracaoSaldo.add(new ListaSubtracaoSaldo("000000000000", 1.550,"Alta"));
        this.listaSubtracaoSaldo.add(new ListaSubtracaoSaldo("000000000000", 1.550,"Baixa"));
        this.listaSubtracaoSaldo.add(new ListaSubtracaoSaldo("000000000000", 1.550,"Alta"));
        this.listaSubtracaoSaldo.add(new ListaSubtracaoSaldo("000000000000", 1.550,"Baixa"));
        this.listaSubtracaoSaldo.add(new ListaSubtracaoSaldo("000000000000", 1.550,"Alta"));
        this.listaSubtracaoSaldo.add(new ListaSubtracaoSaldo("000000000000", 1.550,"Baixa"));
        this.listaSubtracaoSaldo.add(new ListaSubtracaoSaldo("000000000000", 1.550,"Alta"));
        this.listaSubtracaoSaldo.add(new ListaSubtracaoSaldo("000000000000", 1.550,"Baixa"));
        this.listaSubtracaoSaldo.add(new ListaSubtracaoSaldo("000000000000", 1.550,"Alta"));
        this.listaSubtracaoSaldo.add(new ListaSubtracaoSaldo("000000000000", 1.550,"Baixa"));
        this.listaSubtracaoSaldo.add(new ListaSubtracaoSaldo("000000000000", 1.550,"Alta"));
        this.listaSubtracaoSaldo.add(new ListaSubtracaoSaldo("000000000000", 1.550,"Baixa"));
        this.listaSubtracaoSaldo.add(new ListaSubtracaoSaldo("000000000000", 1.550,"Alta"));
        this.listaSubtracaoSaldo.add(new ListaSubtracaoSaldo("000000000000", 1.550,"Baixa"));
        this.listaSubtracaoSaldo.add(new ListaSubtracaoSaldo("000000000000", 1.550,"Alta"));
        this.listaSubtracaoSaldo.add(new ListaSubtracaoSaldo("000000000000", 1.550,"Baixa"));

        return listaSubtracaoSaldo;
    }
}
