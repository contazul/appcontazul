package br.com.appcontazul.activity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.appcontazul.R;
import br.com.appcontazul.contentstatic.Simulador;
import br.com.appcontazul.rest.Requisicao;
import br.com.appcontazul.rest.model.ListaLucroMensal;
import br.com.appcontazul.rest.model.SimuladorEntrada;
import br.com.appcontazul.util.Adaptador11;
import br.com.appcontazul.util.model.ListaSimuladorRemoverBeneficio;

public class SimuladorRemoverBeneficioActivity extends AppCompatActivity {

    // COMPONENTES
    private ProgressBar load;
    private LinearLayout layout_simulacao;
    private Button button_acao;
    private ListView listaSimulacao;
    // FIM COMPONENTES

    // ATRIBUTOS
    private List<ListaSimuladorRemoverBeneficio> lista;
    private String beneficios;
    private int contadorBeneficios;
    // FIM ATRIBUTOS

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulador_remover_beneficio);

        this.criarElementos();
    }

    private void criarElementos() {

        this.load = (ProgressBar) findViewById(R.id.pbHeaderProgress);
        this.layout_simulacao = (LinearLayout) findViewById(R.id.layout_simulacao);
        this.button_acao = (Button) findViewById(R.id.button_acao);
        this.listaSimulacao = (ListView) findViewById(R.id.listaSimulacao);
        this.inicilizarCliqueLista();

        this.lista = new ArrayList<>();
        this.beneficios = "";
        this.contadorBeneficios = 0;

        this.verificarOpcao();
    }

    public void carregarLista() {

        Adaptador11 adaptador = new Adaptador11(lista, this);
        this.listaSimulacao.setAdapter(adaptador);
    }

    public void inicilizarCliqueLista() {

        this.listaSimulacao.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View v, int pos, long id) {

                Simulador.simuladorEntrada.setTotalBeneficioRemovido(Simulador.simuladorEntrada.getTotalBeneficioRemovido() + lista.get(pos).getValor());
                beneficios += lista.get(pos).getId() + ";";
                contadorBeneficios++;
                lista.remove(pos);
                carregarLista();
                mostrarToastItemRemovido();
                button_acao.setEnabled(true);
                button_acao.setAlpha(1);
                if(lista.size() == 0)
                    finalizar(null);
                return true;
            }
        });
    }

    // CONTROLE
    public void verificarOpcao() {

        boolean[] opcoes = getIntent().getBooleanArrayExtra("opcoes");
        if(opcoes[3]) {

            if(opcoes[4])
                this.button_acao.setText(getResources().getString(R.string.simulador_proxima));

            LongOperation operation = new LongOperation();
            operation.execute();
        } else {

            Intent activity = new Intent(SimuladorRemoverBeneficioActivity.this, SimuladorMetaActivity.class);
            activity.putExtra("opcoes", opcoes);
            startActivity(activity);
        }
    }
    // FIM CONTROLE

    // REQUISIÇÃO
    public void listar() {

        Requisicao r = new Requisicao();
        List<ListaLucroMensal> beneficios = r.requestListaLucroMensal();
        for(ListaLucroMensal item : beneficios) {

            lista.add(new ListaSimuladorRemoverBeneficio(item.getId(),item.getDescricao(), item.getValor()));
        }
    }
    // FIM REQUISIÇÃO

    // MENSAGENS
    public void mostrarToastItemRemovido() {

        Toast.makeText(this, getResources().getString(R.string.simulador_excluidosucesso), Toast.LENGTH_SHORT).show();
    }
    // FIM MENSAGENS

    // ONCLICK
    public void finalizar(View v) {

        Simulador.beneficios = new long[this.contadorBeneficios];
        String[] strDividas = this.beneficios.split(";");
        for(int i = 0; i < contadorBeneficios; i++) {

            Simulador.beneficios[i] = Long.parseLong(strDividas[i]);
        }

        if(!this.button_acao.getText().toString().equals(getResources().getString(R.string.simulador_proxima))) {

            Intent activity = new Intent(SimuladorRemoverBeneficioActivity.this, SimuladorResultadoActivity.class);
            activity.putExtra("opcoes", getIntent().getBooleanArrayExtra("opcoes"));
            startActivity(activity);
        } else {

            Intent activity = new Intent(SimuladorRemoverBeneficioActivity.this, SimuladorMetaActivity.class);
            activity.putExtra("opcoes", getIntent().getBooleanArrayExtra("opcoes"));
            startActivity(activity);
        }
    }
    // FIM ONCLICK

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

                Intent activityCentral = new Intent(SimuladorRemoverBeneficioActivity.this, CentralActivity.class);
                startActivity(activityCentral);
                return true;

            case R.id.action_perfilDaConta:

                Intent activityPerfilDaConta = new Intent(SimuladorRemoverBeneficioActivity.this, PerfilContaActivity.class);
                startActivity(activityPerfilDaConta);
                return true;

            case R.id.action_somaSaldo:

                Intent activitySomaDeSaldo = new Intent(SimuladorRemoverBeneficioActivity.this, SomaDeSaldoActivity.class);
                startActivity(activitySomaDeSaldo);
                return true;

            case R.id.action_subtracaoSaldo:

                Intent activitySubtracaoDeSaldo = new Intent(SimuladorRemoverBeneficioActivity.this, SubtracaoDeSaldoActivity.class);
                startActivity(activitySubtracaoDeSaldo);
                return true;

            case R.id.action_lucroMensal:

                Intent activityLucroMensal = new Intent(SimuladorRemoverBeneficioActivity.this, LucroMensalActivity.class);
                startActivity(activityLucroMensal);
                return true;

            case R.id.action_contasPagar:

                Intent activityContasAPagar = new Intent(SimuladorRemoverBeneficioActivity.this, ContasAPagarActivity.class);
                startActivity(activityContasAPagar);
                return true;

            case R.id.action_simulador:

                Intent activitySimulador = new Intent(SimuladorRemoverBeneficioActivity.this, SimuladorActivity.class);
                startActivity(activitySimulador);
                return true;

            case R.id.action_meta:

                Intent activityMeta = new Intent(SimuladorRemoverBeneficioActivity.this, MetaActivity.class);
                startActivity(activityMeta);
                return true;


            case R.id.action_selecaoConta:

                Intent activitySelecaoConta = new Intent(SimuladorRemoverBeneficioActivity.this, SelecaoContaActivity.class);
                startActivity(activitySelecaoConta);
                return true;

            case R.id.action_sair:

                Intent activityLogin = new Intent(SimuladorRemoverBeneficioActivity.this, LoginActivity.class);
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


















