package br.com.appcontazul.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.appcontazul.R;
import br.com.appcontazul.contentstatic.Simulador;
import br.com.appcontazul.rest.Requisicao;
import br.com.appcontazul.rest.model.ListaMeta;
import br.com.appcontazul.util.Adaptador12;
import br.com.appcontazul.util.model.ListaSimuladorMeta;

public class SimuladorMetaActivity extends AppCompatActivity {

    // COMPONENTES
    private ProgressBar load;
    private LinearLayout layout_simulacao;
    private ListView listaSimulacao;
    // FIM COMPONENTES

    // ATRIBUTOS
    private List<ListaSimuladorMeta> lista;
    // FIM ATRIBUTOS

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulador_meta);

        this.criarElementos();
    }

    private void criarElementos() {

        this.load = (ProgressBar) findViewById(R.id.pbHeaderProgress);
        this.layout_simulacao = (LinearLayout) findViewById(R.id.layout_simulacao);
        this.listaSimulacao = (ListView) findViewById(R.id.listaSimulacao);
        this.inicilizarCliqueLista();

        this.lista = new ArrayList<>();

        this.verificarOpcao();
    }

    public void carregarLista() {

        Adaptador12 adaptador = new Adaptador12(lista, this);
        this.listaSimulacao.setAdapter(adaptador);
    }

    public void inicilizarCliqueLista() {

        this.listaSimulacao.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View v, int pos, long id) {

                Simulador.simuladorEntrada.setIdMeta(lista.get(pos).getId());
                Intent intent = new Intent(SimuladorMetaActivity.this, SimuladorResultadoActivity.class);
                intent.putExtra("opcoes", getIntent().getBooleanArrayExtra("opcoes"));
                startActivity(intent);
                return true;
            }
        });
    }

    public void verificarOpcao() {

        boolean[] opcoes = getIntent().getBooleanArrayExtra("opcoes");
        if(opcoes[4]) {

            Simulador.simuladorEntrada.setSimulandoMeta(true);
            LongOperation operation = new LongOperation();
            operation.execute();
        } else {

            Intent activity = new Intent(SimuladorMetaActivity.this, SimuladorResultadoActivity.class);
            activity.putExtra("opcoes", opcoes);
            startActivity(activity);
        }
    }
    // FIM CONTROLE

    // REQUISIÇÃO
    public void listar() {

        Requisicao r = new Requisicao();
        List<ListaMeta> metas = r.requestListaMetaStatus01();
        for(ListaMeta item : metas) {

            this.lista.add(new ListaSimuladorMeta(item.getId(), item.getDescricao()));
        }
    }
    // FIM REQUISIÇÃO

    // MENSAGENS
    public void mostrarToastItemRemovido() {

        Toast.makeText(this, getResources().getString(R.string.simulador_excluidosucesso), Toast.LENGTH_SHORT).show();
    }
    // FIM MENSAGENS

    // MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_central:

                Intent activityCentral = new Intent(SimuladorMetaActivity.this, CentralActivity.class);
                startActivity(activityCentral);
                return true;

            case R.id.action_perfilDaConta:

                Intent activityPerfilDaConta = new Intent(SimuladorMetaActivity.this, PerfilContaActivity.class);
                startActivity(activityPerfilDaConta);
                return true;

            case R.id.action_somaSaldo:

                Intent activitySomaDeSaldo = new Intent(SimuladorMetaActivity.this, SomaDeSaldoActivity.class);
                startActivity(activitySomaDeSaldo);
                return true;

            case R.id.action_subtracaoSaldo:

                Intent activitySubtracaoDeSaldo = new Intent(SimuladorMetaActivity.this, SubtracaoDeSaldoActivity.class);
                startActivity(activitySubtracaoDeSaldo);
                return true;

            case R.id.action_lucroMensal:

                Intent activityLucroMensal = new Intent(SimuladorMetaActivity.this, LucroMensalActivity.class);
                startActivity(activityLucroMensal);
                return true;

            case R.id.action_contasPagar:

                Intent activityContasAPagar = new Intent(SimuladorMetaActivity.this, ContasAPagarActivity.class);
                startActivity(activityContasAPagar);
                return true;

            case R.id.action_simulador:

                Intent activitySimulador = new Intent(SimuladorMetaActivity.this, SimuladorActivity.class);
                startActivity(activitySimulador);
                return true;

            case R.id.action_meta:

                Intent activityMeta = new Intent(SimuladorMetaActivity.this, MetaActivity.class);
                startActivity(activityMeta);
                return true;


            case R.id.action_selecaoConta:

                Intent activitySelecaoConta = new Intent(SimuladorMetaActivity.this, SelecaoContaActivity.class);
                startActivity(activitySelecaoConta);
                return true;

            case R.id.action_sair:

                Intent activityLogin = new Intent(SimuladorMetaActivity.this, LoginActivity.class);
                startActivity(activityLogin);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
    // FIM MENU

    // MÉTODOS ASCINCRONOS
    private class LongOperation extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            listar();
            return true;
        }

        @Override
        protected void onPreExecute() {

            layout_simulacao.setVisibility(View.GONE);
            load.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

            layout_simulacao.setVisibility(View.VISIBLE);
            load.setVisibility(View.GONE);
            carregarLista();
        }
    }
    // FIM MÉTODOS ASCINCRONOS
}
