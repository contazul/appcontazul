package br.com.appcontazul.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import br.com.appcontazul.R;
import br.com.appcontazul.contentstatic.ReferenciaUsuario;
import br.com.appcontazul.rest.Requisicao;
import br.com.appcontazul.rest.model.ListaContazul;
import br.com.appcontazul.util.Adaptador;

public class SelecaoContaActivity extends AppCompatActivity {

    private ListView listaContas;
    private TextView textViewRE20;
    private Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_selecao_conta);
        this.listaContas = (ListView) findViewById(R.id.listaContas);
        this.textViewRE20 = (TextView) findViewById(R.id.textView_RE20);

        Requisicao requisicao = new Requisicao();

        List<ListaContazul> listaContazul = requisicao.requestListaContazul();

        if(listaContazul != null && listaContazul.size() != 0) {

            this.textViewRE20.setVisibility(View.GONE);
            adaptador = new Adaptador(listaContazul, this);
            listaContas.setAdapter(adaptador);
        }

        listaContas.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapter, View view,
                                    int posicao, long id) {

                ListaContazul listaContazul = adaptador.getItem(posicao);
                ReferenciaUsuario.numeroContazul = listaContazul.getNumeroContazul();
                Intent centralActivity = new Intent(SelecaoContaActivity.this, CentralActivity.class);
                startActivity(centralActivity);
            }
        });
    }

    public void criarContazul (View v) {

        Requisicao requisicao = new Requisicao();
        requisicao.requestInserirContazul();
        AlertDialog.Builder popup = new AlertDialog.Builder(SelecaoContaActivity.this);
        popup.setTitle(R.string.activitySelacaoConta_tituloSucesso);
        popup.setMessage(R.string.activitySelacaoConta_RE_10);
        popup.setPositiveButton(R.string.activitySelacaoConta_popupBotaoProsseguir,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                textViewRE20.setVisibility(View.GONE);
                Requisicao requisicao = new Requisicao();
                adaptador = new Adaptador(requisicao.requestListaContazul(), SelecaoContaActivity.this);
                listaContas.setAdapter(adaptador);
            }
        });
        popup.create();
        popup.show();
    }
}
