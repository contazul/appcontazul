package br.com.appcontazul.util;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.appcontazul.R;
import br.com.appcontazul.rest.model.ListaContazul;
import br.com.appcontazul.rest.model.ListaSomaSaldo;

public class Adaptador03 extends BaseAdapter {

    private final List<ListaSomaSaldo> listaSomaSaldo;
    private final Activity act01;

    public Adaptador03(List<ListaSomaSaldo> listaSomaSaldo, Activity act01) {
        this.listaSomaSaldo = listaSomaSaldo;
        this.act01 = act01;
    }

    @Override
    public int getCount() {

        return this.listaSomaSaldo.size();
    }

    @Override
    public ListaSomaSaldo getItem(int posicao) {

        return this.listaSomaSaldo.get(posicao);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act01.getLayoutInflater()
                .inflate(R.layout.lista_soma_saldo, parent, false);
        ListaSomaSaldo listaSomaSaldo = this.listaSomaSaldo.get(position);


        TextView textView_descricaoMovimentacao = (TextView) view.findViewById(R.id.textView_descricaoMovimentacao);
        TextView textView_valorMovimentacao = (TextView) view.findViewById(R.id.textView_valorMovimentacao);

        textView_descricaoMovimentacao.setText(view.getResources().getString(R.string.activitySelacaoConta_listaItemDescricao) + " " + listaSomaSaldo.getDescricao());
        textView_valorMovimentacao.setText(view.getResources().getString(R.string.activitySomaSaldolistaItemValorMovimentacao) + " " + listaSomaSaldo.getValorMovimentacao());
        return view;
    }
}
