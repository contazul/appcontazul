package br.com.appcontazul.rest.teste;

import java.util.ArrayList;
import java.util.List;

import br.com.appcontazul.rest.model.ListaMeta;

public class ListaMetaRepository {

    List<ListaMeta> listaMeta;


    public List<ListaMeta> getListaMeta (){

        this.listaMeta = new ArrayList<>();
        this.listaMeta.add(new ListaMeta("Bruno",1.0, 2.0,3.0,"02/04","Alcançada",12,5));
        this.listaMeta.add(new ListaMeta("Bruno",1.0, 2.0,3.0,"09/04","Não Alcançada",0,1));
        this.listaMeta.add(new ListaMeta("Bruno",1.0, 2.0,3.0,"01/03","Alcançada",12,2));
        this.listaMeta.add(new ListaMeta("Bruno",1.0, 2.0,3.0,"02/04","Não Alcançada",0,5));




        return listaMeta;
    }

}
