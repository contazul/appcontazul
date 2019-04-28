package br.com.appcontazul.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.appcontazul.R;
import br.com.appcontazul.contentstatic.Simulador;
import br.com.appcontazul.rest.Requisicao;
import br.com.appcontazul.rest.model.ListaSubtracaoSaldo;

public class SimuladorGastoAVistaResultadoActivity extends AppCompatActivity {

    // COMPONENTES
    private TextView saldoSimulado;
    private TextView totalBaixaPrioridade;
    private TextView totalGeral;
    private ProgressBar carregando;
    private Button aplicar;
    private Button finalizarSimulacao;
    // FIM COMPONENENTES

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulador_gasto_avista_resultado);
        this.criarElementos();
        this.carregarElementos();
    }

    public void criarElementos() {

        // INICIALIZANDO COMPONENENTES
        this.saldoSimulado = (TextView) findViewById(R.id.textView_valorResultadoSaldoSimualdo);
        this.totalBaixaPrioridade = (TextView) findViewById(R.id.textView_valorResultadoTotalBaixa);
        this.totalGeral = (TextView) findViewById(R.id.textView_valorResultadoTotalGeral);
        this.carregando = (ProgressBar) findViewById(R.id.pbHeaderProgress);
        this.aplicar = (Button) findViewById(R.id.button_aplicar);
        this.finalizarSimulacao = (Button) findViewById(R.id.button_finalizarSimulacao);
        // FIM INICIALIZANDO COMPONENENTES
    }

    public void carregarElementos() {

        this.saldoSimulado.setText(getIntent().getStringExtra("saldoSimulado"));
        this.totalBaixaPrioridade.setText(getIntent().getStringExtra("totalBaixa"));
        this.totalGeral.setText(getIntent().getStringExtra("totalGeral"));
    }

    // ONCLICK
    public void aplicar(View v) {

        exibirConfirmacaoAplicacao();
    }

    public void finalizarSimulacao(View v) {

        redirecionarSimulador();
    }
    // FIM ONCLICK

    // MÉTODOS DE REQUISIÇÃO
    public void incluirSubtracaoDeSaldo() {

        Requisicao requisicao = new Requisicao();
        for(ListaSubtracaoSaldo subtracaoSaldo : Simulador.listaSimulacao) {

            requisicao.requestInserirSubtracaoSaldo("" + subtracaoSaldo.getValor(),
                    subtracaoSaldo.getDescricao(), subtracaoSaldo.getPrioridade());
        }
    }
    // FIM MÉTODOS DE REQUISIÇÃO

    // MÉTODOS DE CONTROLE
    public void desabilitarItens() {

        this.aplicar.setEnabled(false);
        this.finalizarSimulacao.setEnabled(false);
    }

    public void redirecionarSimulador() {

        Simulador.listaSimulacao = new ArrayList<>();
        Intent activitySimulador = new Intent(SimuladorGastoAVistaResultadoActivity.this,
                SimuladorActivity.class);
        startActivity(activitySimulador);
    }
    // FIM MÉTODOS DE CONTROLE

    // EXIBIR MENSAGEM
    public void exibirConfirmacaoAplicacao() {

        AlertDialog.Builder popup = new AlertDialog.Builder(SimuladorGastoAVistaResultadoActivity.this);
        popup.setTitle(R.string.simulador_atencao);
        popup.setMessage(R.string.simulador_confirmarAplicacao);
        popup.setPositiveButton(R.string.simulador_sim, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                LongOperation longOperation = new LongOperation();
                longOperation.execute();
            }
        });
        popup.setNegativeButton(R.string.simulador_nao, null);
        popup.create();
        popup.show();
    }

    public void exibirSucessoAplicacao() {

        AlertDialog.Builder popup = new AlertDialog.Builder(SimuladorGastoAVistaResultadoActivity.this);
        popup.setTitle(R.string.simulador_sucesso);
        popup.setMessage(R.string.simulador_sucessoAplicacao);
        popup.setPositiveButton(R.string.simulador_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                redirecionarSimulador();
            }
        });
        popup.create();
        popup.show();
    }
    // FIM EXIBIR MENSAGEM

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

                Intent activityCentral = new Intent(SimuladorGastoAVistaResultadoActivity.this, CentralActivity.class);
                startActivity(activityCentral);
                return true;

            case R.id.action_perfilDaConta:

                Intent activityPerfilDaConta = new Intent(SimuladorGastoAVistaResultadoActivity.this, PerfilContaActivity.class);
                startActivity(activityPerfilDaConta);
                return true;

            case R.id.action_somaSaldo:

                Intent activitySomaDeSaldo = new Intent(SimuladorGastoAVistaResultadoActivity.this, SomaDeSaldoActivity.class);
                startActivity(activitySomaDeSaldo);
                return true;

            case R.id.action_subtracaoSaldo:

                Intent activitySubtracaoDeSaldo = new Intent(SimuladorGastoAVistaResultadoActivity.this, SubtracaoDeSaldoActivity.class);
                startActivity(activitySubtracaoDeSaldo);
                return true;

            case R.id.action_lucroMensal:

                Intent activityLucroMensal = new Intent(SimuladorGastoAVistaResultadoActivity.this, LucroMensalActivity.class);
                startActivity(activityLucroMensal);
                return true;

            case R.id.action_contasPagar:

                Intent activityContasAPagar = new Intent(SimuladorGastoAVistaResultadoActivity.this, ContasAPagarActivity.class);
                startActivity(activityContasAPagar);
                return true;

            case R.id.action_simulador:

                Intent activitySimulador = new Intent(SimuladorGastoAVistaResultadoActivity.this, SimuladorActivity.class);
                startActivity(activitySimulador);
                return true;

            case R.id.action_meta:

                Intent activityMeta = new Intent(SimuladorGastoAVistaResultadoActivity.this, MetaActivity.class);
                startActivity(activityMeta);
                return true;


            case R.id.action_selecaoConta:

                Intent activitySelecaoConta = new Intent(SimuladorGastoAVistaResultadoActivity.this, SelecaoContaActivity.class);
                startActivity(activitySelecaoConta);
                return true;

            case R.id.action_sair:

                Intent activityLogin = new Intent(SimuladorGastoAVistaResultadoActivity.this, LoginActivity.class);
                startActivity(activityLogin);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
    // FIM MENU

    // CHAMADAS ASSINCRONAS
    private class LongOperation extends AsyncTask<Void, Void, Boolean> {



        @Override
        protected Boolean doInBackground(Void... voids) {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            incluirSubtracaoDeSaldo();
            return true;
        }

        @Override
        protected void onPreExecute() {

            desabilitarItens();
            carregando.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

            carregando.setVisibility(View.GONE);
            exibirSucessoAplicacao();
        }
    }
    // FIM CHAMADAS ASSINCRONAS
}












