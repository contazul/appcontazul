package br.com.appcontazul.util;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.appcontazul.R;
import br.com.appcontazul.rest.model.ListaDividaFixa;

public class Adaptador09 extends BaseAdapter {

    private final List<ListaDividaFixa> lista;

    private final Activity act01;

    public Adaptador09(List<ListaDividaFixa> lista, Activity act01) {

        this.lista = lista;
        this.act01 = act01;
    }

    @Override
    public int getCount (){
        return this.lista.size();
    }

    @Override
    public ListaDividaFixa getItem(int posicao){
        return this.lista.get(posicao);
    }

    @Override
    public long getItemId (int posicao) {return 0;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View view = act01.getLayoutInflater().inflate(R.layout.lista_simulador_adesao_divida,
                parent, false);
        ListaDividaFixa item = this.lista.get(position);
        TextView descricao = view.findViewById(R.id.textView_descricao);
        descricao.setText(view.getResources().getString(R.string.simulador_lista_textviewdescricao,
                item.getDescricao()));
        Formatacao fmt = new Formatacao();
        TextView valor = view.findViewById(R.id.textView_valor);
        valor.setText(view.getResources().getString(R.string.simulador_lista_textviewvalor,
                fmt.formatarValorMonetario("" + item.getValor())));
        TextView prioridade = view.findViewById(R.id.textView_prioridade);
        prioridade.setText(view.getResources().getString(R.string.simulador_lista_textviewprioridade,
                item.getPrioridade()));
        TextView tipo = view.findViewById(R.id.textView_tipo);
        tipo.setVisibility(View.GONE);
        return view;
    }
}













