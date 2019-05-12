package br.com.appcontazul.util;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.appcontazul.R;
import br.com.appcontazul.util.model.ListaSimuladorRemoverBeneficio;

public class Adaptador11 extends BaseAdapter {

    private List<ListaSimuladorRemoverBeneficio> lista;
    private final Activity act01;

    public Adaptador11(List<ListaSimuladorRemoverBeneficio> lista, Activity act01) {

        this.lista = lista;
        this.act01 = act01;
    }

    @Override
    public int getCount (){
        return this.lista.size();
    }

    @Override
    public ListaSimuladorRemoverBeneficio getItem(int posicao){
        return this.lista.get(posicao);
    }

    @Override
    public long getItemId (int posicao) {return 0;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View view = act01.getLayoutInflater().inflate(R.layout.lista_simulador_novo_beneficio,
                parent, false);
        ListaSimuladorRemoverBeneficio item = this.lista.get(position);
        TextView descricao = view.findViewById(R.id.textView_descricao);
        descricao.setText(view.getResources().getString(R.string.simulador_lista_textviewdescricao,
                item.getDescricao()));
        Formatacao fmt = new Formatacao();
        TextView valor = view.findViewById(R.id.textView_valor);
        valor.setText(view.getResources().getString(R.string.simulador_lista_textviewvalor,
                fmt.formatarValorMonetario("" + item.getValor())));
        return view;
    }
}
