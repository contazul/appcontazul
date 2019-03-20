package br.com.appcontazul.util;


import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.appcontazul.R;
import br.com.appcontazul.rest.model.ListaSubtracaoSaldo;


public class Adaptador04 extends BaseAdapter{

    private final List<ListaSubtracaoSaldo> listaSubtracaoSaldo;
    private final Activity act01;

    public Adaptador04(List<ListaSubtracaoSaldo> listaSubtracaoSaldo, Activity act01) {
        this.listaSubtracaoSaldo = listaSubtracaoSaldo;
        this.act01 = act01;
    }

    @Override
    public int getCount() {

        return this.listaSubtracaoSaldo.size();
    }

    @Override
    public ListaSubtracaoSaldo getItem(int posicao) {

        return this.listaSubtracaoSaldo.get(posicao);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act01.getLayoutInflater()
                .inflate(R.layout.lista_subtracao_de_saldo, parent, false);
        ListaSubtracaoSaldo listaSubtracaoSaldo = this.listaSubtracaoSaldo.get(position);


        TextView textView_descricaoMovimentacao = (TextView) view.findViewById(R.id.textView_descricaoMovimentacao);
        TextView textView_valorMovimentacao = (TextView) view.findViewById(R.id.textView_valorMovimentacao);
        TextView textView_prioridade = (TextView) view.findViewById(R.id.textView_Prioridade);

        textView_descricaoMovimentacao.setText(view.getResources().getString(R.string.listaSubtracaoSaldo_descricao)
                + " " + listaSubtracaoSaldo.getDescricao());

        Formatacao formatacao = new Formatacao();

        textView_valorMovimentacao.setText(view.getResources().getString(R.string.listaSubtracaoSaldo_valor)
                + " " + formatacao.formatarValorMonetario("" + listaSubtracaoSaldo.getValor()));
        textView_prioridade.setText(view.getResources().getString(R.string.listaSubtracaoSaldo_prioridade)
                + " " + listaSubtracaoSaldo.getPrioridade());
        return view;
    }


}
