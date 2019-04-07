package br.com.appcontazul.util;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import br.com.appcontazul.R;
import java.util.List;
import br.com.appcontazul.rest.model.ListaLucroMensal;
import br.com.appcontazul.rest.model.ListaSubtracaoSaldo;


public class    Adaptador05 extends BaseAdapter {

    private final List<ListaLucroMensal> listaLucroMensal;
    private final Activity act01;

    public Adaptador05(List<ListaLucroMensal> listaLucroMensal, Activity act01) {
        this.listaLucroMensal = listaLucroMensal;
        this.act01 = act01;
    }

    @Override
    public int getCount() {

        return this.listaLucroMensal.size();
    }

    @Override
    public ListaLucroMensal getItem(int posicao) {

        return this.listaLucroMensal.get(posicao);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act01.getLayoutInflater()
                .inflate(R.layout.lista_lucro_mensal, parent, false);
        ListaLucroMensal listaLucroMensal = this.listaLucroMensal.get(position);


        TextView textView_descricaoBeneficio = (TextView) view.findViewById(R.id.textView_descricaoBeneficio);
        TextView textView_valorBeneficio = (TextView) view.findViewById(R.id.textView_valorBeneficio);
        TextView textView_data = (TextView) view.findViewById(R.id.textView_Data);
        Button buttonReceber = (Button) view.findViewById(R.id.button_receber);
        buttonReceber.setId(position);
        Button buttonExcluir = (Button) view.findViewById(R.id.button_Excluir);
        buttonExcluir.setId(position);

        textView_descricaoBeneficio.setText(view.getResources().getString(R.string.activitySelacaoConta_listaItemDescricao) + " " + listaLucroMensal.getDescricao());
        Formatacao formatacao = new Formatacao();
        textView_valorBeneficio.setText(view.getResources().getString(R.string.activityLucroMensalListaItemValorBeneficio) + " " + formatacao.formatarValorMonetario("" + listaLucroMensal.getValor()));
        if(listaLucroMensal.getUltimaDataRecebimento() != null) {
            textView_data.setText(view.getResources().getString(R.string.activityLucroMensalListaItemDataBeneficio) + " " + listaLucroMensal.getUltimaDataRecebimento());
        } else {

            textView_data.setText(view.getResources().getString(R.string.activityLucroMensalListaItemSemData));
        }
        //button_receber.setText(view.getResources().getString(R.string.activityLucroMensalItemButtonReceber) +" " + listaLucroMensal.getButtonReceber());
        //button_excluir.setText(view.getResources().getString(R.string.activityLucroMensalItemButtonExcluir) +" " + listaLucroMensal.getButtonExcluir());
        return view;
    }

}
