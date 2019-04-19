package br.com.appcontazul.util;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.com.appcontazul.R;
import br.com.appcontazul.rest.model.ListaMeta;


public class Adaptador07  extends BaseAdapter {

    private final List<ListaMeta> listaMeta;
    private final Activity act01;

    public Adaptador07(List<ListaMeta> listaMeta, Activity act01) {
        this.listaMeta = listaMeta;
        this.act01 = act01;
    }

    @Override
    public int getCount (){
        return this.listaMeta.size();
    }

    @Override
    public ListaMeta getItem(int posicao){
        return this.listaMeta.get(posicao);
    }

    @Override
    public long getItemId (int posicao) {return 0;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View view = act01.getLayoutInflater().inflate(R.layout.lista_meta, parent, false);
        ListaMeta listaMeta = this.listaMeta.get(position);

        TextView textView_descricaoDaMeta = (TextView) view.findViewById(R.id.textView_descricaoDaMeta);
        TextView textView_valorDaMeta = (TextView) view.findViewById(R.id.textView_valorDaMeta);
        TextView textView_valorMinimoEco = (TextView) view.findViewById(R.id.textView_valorMinimoEco);
        TextView textView_valorParaAtingir = (TextView) view.findViewById(R.id.textView_valorParaAtingir);
        TextView textView_status = (TextView) view.findViewById(R.id.textView_status);
        Button buttonAplicar = (Button) view.findViewById(R.id.button_Aplicar);
        buttonAplicar.setId(position);
        Button buttonExcluir = (Button) view.findViewById(R.id.button_Excluir);
        buttonExcluir.setId(position);


        textView_descricaoDaMeta.setText("Descrição:" + " " + listaMeta.getDescricao());

        Formatacao formatacao = new Formatacao();
        textView_valorDaMeta.setText("Valor:" + " " + formatacao.formatarValorMonetario("" + listaMeta.getValor()));
        textView_valorMinimoEco.setText("Valor mínimo a ser economizado:" + " " + formatacao.formatarValorMonetario("" + listaMeta.getValorEconomizar()));
        textView_valorParaAtingir.setText("Valor restante para atingir a meta:" + " " + formatacao.formatarValorMonetario("" + listaMeta.getValorRestante()));
        textView_status.setText("Status:" + " " + listaMeta.getStatus());

        if(!listaMeta.isPodeAplicar()){

            buttonExcluir.setLayoutParams(buttonAplicar.getLayoutParams());
            buttonAplicar.setVisibility(View.GONE);
        }

        return view;
    }
}
