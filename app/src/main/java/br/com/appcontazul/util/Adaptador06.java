package br.com.appcontazul.util;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.appcontazul.R;
import br.com.appcontazul.rest.model.ListaContasAPagar;

public class Adaptador06 extends BaseAdapter {

    private final List<ListaContasAPagar> listaContasAPagar;
    private final Activity act01;

    public Adaptador06(List<ListaContasAPagar> listaContasAPagar, Activity act01) {
        this.listaContasAPagar = listaContasAPagar;
        this.act01 = act01;
    }

    @Override
    public int getCount (){
        return this.listaContasAPagar.size();
    }

    @Override
    public ListaContasAPagar getItem(int posicao){
        return this.listaContasAPagar.get(posicao);
    }

    @Override
    public long getItemId (int posicao) {return 0;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = act01.getLayoutInflater().inflate(R.layout.lista_contas_apagar, parent, false);
        ListaContasAPagar listaContasAPagar = this.listaContasAPagar.get(position);

        TextView textView_descricaoDaConta = (TextView) view.findViewById(R.id.textView_descricaoDaConta);
        TextView textView_valorDaConta = (TextView) view.findViewById(R.id.textView_valorDaConta);
        TextView textView_data = (TextView) view.findViewById(R.id.textView_Data);
        TextView textView_prioridade = (TextView) view.findViewById(R.id.textView_Prioridade);
        TextView textView_parcela = (TextView) view.findViewById(R.id.textView_Parcela);
        Button buttonPagar = (Button) view.findViewById(R.id.button_Pagar);
        buttonPagar.setId(position);
        Button buttonExcluir = (Button) view.findViewById(R.id.button_Excluir);
        buttonExcluir.setId(position);
        Button buttonQuitar = (Button) view.findViewById(R.id.button_Quitar);
        buttonQuitar.setId(position);

        textView_descricaoDaConta.setText("Descrição:" + " " + listaContasAPagar.getDescricao());
        Formatacao formatacao = new Formatacao();
        textView_valorDaConta.setText("Valor:" + " " + formatacao.formatarValorMonetario("" + listaContasAPagar.getValor()));
        // 1/12
        textView_prioridade.setText("Prioridade:" + " " + listaContasAPagar.getPrioridade());

        if(listaContasAPagar.getStrUltimaDataPagamento() == null || listaContasAPagar.getStrUltimaDataPagamento().isEmpty())
            textView_data.setText("Não há data de pagamento registrada");
        else
            textView_data.setText("Ultimo pagamento realizado:" + listaContasAPagar.getStrUltimaDataPagamento());

        if (listaContasAPagar.getQuantidadeParcela() !=0){
            textView_parcela.setText("Número de parcelas pagas:" + listaContasAPagar.getQuantidadePaga()+"/"+listaContasAPagar.getQuantidadeParcela());
            textView_parcela.setVisibility(View.VISIBLE);
        }

        if (listaContasAPagar.getQuantidadeParcela() != 0){
            buttonQuitar.setVisibility(View.VISIBLE);

        }

        return view;
    }
}
