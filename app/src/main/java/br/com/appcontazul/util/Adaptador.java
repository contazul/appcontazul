package br.com.appcontazul.util;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.appcontazul.R;
import br.com.appcontazul.rest.model.ListaContazul;

public class Adaptador extends BaseAdapter {

    private final List<ListaContazul> contas;
    private final Activity act;

    public Adaptador(List<ListaContazul> contas, Activity act) {

        this.contas = contas;
        this.act = act;
    }

    @Override
    public int getCount() {

        return this.contas.size();
    }

    @Override
    public ListaContazul getItem(int posicao) {

        return this.contas.get(posicao);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater()
                .inflate(R.layout.lista_contazul, parent, false);
        ListaContazul listaContazul = this.contas.get(position);

        TextView textViewNumeroContazul = (TextView) view.findViewById(R.id.textView_numeroContazul);
        TextView textViewDescricaoConta = (TextView) view.findViewById(R.id.textView_descricaoConta);
        TextView textViewPerfilComum = (TextView) view.findViewById(R.id.textView_perfilComum);

        if(listaContazul.getNumeroContazul() == 0L) {

            textViewNumeroContazul.setVisibility(View.GONE);
            textViewDescricaoConta.setVisibility(View.GONE);
            textViewPerfilComum.setVisibility(View.GONE);
        } else {

            textViewNumeroContazul.setText("" + listaContazul.getNumeroContazul());
            textViewDescricaoConta.setText(view.getResources().getString(R.string.activitySelacaoConta_listaItemDescricao) + " " + listaContazul.getDescricao());
            textViewPerfilComum.setText(view.getResources().getString(R.string.activitySelacaoConta_listaItemPerfil) + " " + listaContazul.getPerfil());
        }

        return view;
    }
}
