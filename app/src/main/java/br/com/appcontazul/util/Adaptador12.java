package br.com.appcontazul.util;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.appcontazul.R;
import br.com.appcontazul.util.model.ListaSimuladorMeta;

public class Adaptador12 extends BaseAdapter {

    private final List<ListaSimuladorMeta> lista;
    private final Activity act01;

    public Adaptador12(List<ListaSimuladorMeta> lista, Activity act01) {

        this.lista = lista;
        this.act01 = act01;
    }

    @Override
    public int getCount() {
        return this.lista.size();
    }

    @Override
    public ListaSimuladorMeta getItem(int posicao) {
        return this.lista.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = act01.getLayoutInflater().inflate(R.layout.lista_simulador_meta,
                parent, false);
        ListaSimuladorMeta item = lista.get(position);
        TextView textViewDescricao = view.findViewById(R.id.textView_descricao);
        textViewDescricao.setText(item.getDescricao());
        return view;
    }
}