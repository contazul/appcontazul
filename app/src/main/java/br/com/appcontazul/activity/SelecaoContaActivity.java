package br.com.appcontazul.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
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
    ProgressBar pbHeaderProgress;
    private Button criarContazul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_selecao_conta);
        this.listaContas = (ListView) findViewById(R.id.listaContas);
        this.textViewRE20 = (TextView) findViewById(R.id.textView_RE20);
        this.pbHeaderProgress = (ProgressBar) findViewById(R.id.pbHeaderProgress);
        this.criarContazul = (Button) findViewById(R.id.button_criarconta);

        Requisicao requisicao = new Requisicao();

        List<ListaContazul> listaContazul = requisicao.requestListaContazul();

        if(listaContazul != null && listaContazul.size() != 0) {

            this.textViewRE20.setText(getResources().getString(R.string.activitySelacaoConta_selecionarConta));
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

        LongOperation longOperation = new LongOperation();
        longOperation.execute();
    }

    public void criarContazul() {

        Requisicao requisicao = new Requisicao();
        requisicao.requestInserirContazul();
    }

    public void mostrarPopup() {

        AlertDialog.Builder popup = new AlertDialog.Builder(SelecaoContaActivity.this);
        popup.setTitle(R.string.activitySelacaoConta_tituloSucesso);
        popup.setMessage(R.string.activitySelacaoConta_RE_10);
        popup.setPositiveButton(R.string.activitySelacaoConta_popupBotaoProsseguir,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                
                Requisicao requisicao = new Requisicao();
                adaptador = new Adaptador(requisicao.requestListaContazul(), SelecaoContaActivity.this);
                listaContas.setAdapter(adaptador);
                textViewRE20.setText(getResources().getString(R.string.activitySelacaoConta_selecionarConta));
            }
        });
        popup.create();
        popup.show();
    }

    public void desabilitarItens() {

        this.listaContas.setEnabled(false);
        this.criarContazul.setEnabled(false);
    }

    public void habilitarItens() {

        this.listaContas.setEnabled(true);
        this.criarContazul.setEnabled(true);
    }

    private class LongOperation extends AsyncTask<Void, Void, Boolean> {


        @Override
        protected Boolean doInBackground(Void... voids) {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            criarContazul();
            return true;
        }

        @Override
        protected void onPreExecute() {

            desabilitarItens();
            pbHeaderProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

            pbHeaderProgress.setVisibility(View.GONE);
            mostrarPopup();
            habilitarItens();
        }
    }
}
