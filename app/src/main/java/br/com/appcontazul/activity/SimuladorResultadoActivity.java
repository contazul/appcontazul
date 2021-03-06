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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import br.com.appcontazul.R;
import br.com.appcontazul.contentstatic.Simulador;
import br.com.appcontazul.rest.Requisicao;
import br.com.appcontazul.rest.model.InclusaoDivida;
import br.com.appcontazul.rest.model.SimuladorSaida;
import br.com.appcontazul.util.Formatacao;
import br.com.appcontazul.util.model.ListaSimuladorNovoBeneficio;

public class SimuladorResultadoActivity extends AppCompatActivity {

    // COMPONENTES
    private TextView textView_statusSimulado;
    private TextView textView_statusAtual;
    private TextView textView_percentualEconomizadoSimulado;
    private TextView textView_percentualEconomizadoAtual;
    private TextView textView_valorEconomizadoSimulado;
    private TextView textView_valorEconomizadoAtual;
    private TextView textView_valorAfetado;
    private ProgressBar load;
    private LinearLayout layout_resultadoSimulacao;
    private TextView textView_statusMeta;
    private TextView textView_valorRestante;
    private LinearLayout layout_resultadoSimulacaoMeta;
    private Button button_aplicar;
    private Button button_finalizarSimulacao;
    // FIM COMPONENTES

    // ATRIBUTOS DE CONTROLE
    private SimuladorSaida saida;
    // FIM ATRIBUTOS DE CONTROLE

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulador_resultado);

        this.criarElementos();
    }

    private void criarElementos() {

        this.textView_statusSimulado = (TextView) findViewById(R.id.textView_statusSimulado);
        this.textView_statusAtual = (TextView) findViewById(R.id.textView_statusAtual);
        this.textView_percentualEconomizadoSimulado = (TextView) findViewById(R.id.textView_percentualEconomizadoSimulado);
        this.textView_percentualEconomizadoAtual = (TextView) findViewById(R.id.textView_percentualEconomizadoAtual);
        this.textView_valorEconomizadoSimulado = (TextView) findViewById(R.id.textView_valorEconomizadoSimulado);
        this.textView_valorEconomizadoAtual = (TextView) findViewById(R.id.textView_valorEconomizadoAtual);
        this.textView_valorAfetado = (TextView) findViewById(R.id.textView_valorAfetado);
        this.load = (ProgressBar) findViewById(R.id.pbHeaderProgress);
        this.layout_resultadoSimulacao = (LinearLayout) findViewById(R.id.layout_resultadoSimulacao);
        this.textView_statusMeta = (TextView) findViewById(R.id.textView_statusMeta);
        this.textView_valorRestante = (TextView) findViewById(R.id.textView_valorRestante);
        this.layout_resultadoSimulacaoMeta = (LinearLayout) findViewById(R.id.layout_resultadoSimulacaoMeta);
        this.button_aplicar = (Button) findViewById(R.id.button_finalizarSimulacao);
        this.button_finalizarSimulacao = (Button) findViewById(R.id.button_finalizarSimulacao);
        this.chamadaInicial();
    }

    public void carregarElementos() {

        this.textView_statusSimulado.setText(getResources().getString(R.string.simulador_statussimulado, this.saida.getStatusSimulado()));
        this.textView_statusAtual.setText(getResources().getString(R.string.simulador_statusatual, this.saida.getStatusAtual()));
        Formatacao fmt = new Formatacao();
        this.textView_percentualEconomizadoSimulado.setText(getResources().getString(R.string.simulador_percentualeconomizadosimulado,
                fmt.formatarValorPercentual(this.saida.getPercentualEconomizadoSimulado())));
        this.textView_percentualEconomizadoAtual.setText(getResources().getString(R.string.simulador_percentualeconomizadoatual,
                fmt.formatarValorPercentual(this.saida.getPercentualEconomizadoAtual())));
        this.textView_valorEconomizadoSimulado.setText(getResources().getString(R.string.simulador_valoreconomizadosimulado,
                fmt.formatarValorMonetario("" + this.saida.getValorEconomizadoSimulado())));
        this.textView_valorEconomizadoAtual.setText(getResources().getString(R.string.simulador_valoreconomizadoatual,
                fmt.formatarValorMonetario("" + this.saida.getValorEconomizadoAtual())));
        String msg = "";
        if(this.saida.isLucrou()) {

            msg = getResources().getString(R.string.simulador_aumentou);
        } else {

            msg = getResources().getString(R.string.simulador_caiu);
        }
        this.textView_valorAfetado.setText(getResources().getString(R.string.simulador_valorafetado,
                msg, fmt.formatarValorMonetario("" + this.saida.getValorAfetado())));
        if(Simulador.simuladorEntrada.isSimulandoMeta()) {

            this.textView_statusMeta.setText(getResources().getString(R.string.simulador_statusmeta, this.saida.getStatusMeta()));
            this.textView_valorRestante.setText(getResources().getString(R.string.simulador_valorrestantemeta,
                    fmt.formatarValorMonetario("" + this.saida.getValorRestanteMeta())));
        }
    }

    public void chamadaInicial() {

        LongOperation operation = new LongOperation();
        operation.execute();
    }

    // MÉTODOS DE REQUISIÇÃO
    public void carregarInformacoes() {

        Requisicao requisicao = new Requisicao();
        this.saida = requisicao.requestSimular();
    }

    public void aplicarAdesaoDivida() {

        boolean[] opcoes = getIntent().getBooleanArrayExtra("opcoes");
        if(opcoes[0]) {

            for (InclusaoDivida item : Simulador.dividasSimuladas) {

                Requisicao requisicao = new Requisicao();
                requisicao.requestIncluirConta(item.getDescricao(), item.getValor(),
                        item.getPrioridade(), item.getQuantidadeParcela());
            }
        }
    }

    public void aplicarExclusaoDivida() {

        boolean[] opcoes = getIntent().getBooleanArrayExtra("opcoes");
        if(opcoes[1]) {

            for(int i = 0; i < Simulador.dividas.length; i++) {

                Requisicao requisicao = new Requisicao();
                requisicao.requestExcluirDivida(Simulador.dividas[i]);
            }
        }
    }

    public void aplicarNovoBeneficio() {

        boolean[] opcoes = getIntent().getBooleanArrayExtra("opcoes");
        if(opcoes[2]) {

            for(ListaSimuladorNovoBeneficio item : Simulador.beneficiosNovos) {

                Requisicao requisicao = new Requisicao();
                requisicao.requestInserirLucroMensal(item.getDescricao(), "" + item.getValor());
            }
        }
    }

    public void aplicarExclusaoBenefico() {

        boolean[] opcoes = getIntent().getBooleanArrayExtra("opcoes");
        if(opcoes[3]) {

            for(int i = 0; i < Simulador.beneficios.length; i++) {

                Requisicao requisicao = new Requisicao();
                requisicao.requestExcluirLucroMensal("" + Simulador.beneficios[i]);
            }
        }
    }
    // FIM MÉTODOS DE REQUISIÇÃO

    // MENSAGENS
    public void exibirConfirmacaoAplicacao() {

        AlertDialog.Builder popup = new AlertDialog.Builder(SimuladorResultadoActivity.this);
        popup.setTitle(R.string.simulador_atencao);
        popup.setMessage(R.string.simulador_confirmarAplicacao);
        popup.setPositiveButton(R.string.simulador_sim, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                LongOperation2 longOperation = new LongOperation2();
                longOperation.execute();
            }
        });
        popup.setNegativeButton(R.string.simulador_nao, null);
        popup.create();
        popup.show();
    }

    public void exibirSucessoAplicacao() {

        AlertDialog.Builder popup = new AlertDialog.Builder(SimuladorResultadoActivity.this);
        popup.setTitle(R.string.simulador_sucesso);
        popup.setMessage(R.string.simulador_resultadoaplicacao);
        popup.setPositiveButton(R.string.simulador_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                redirecionarSimulador();
            }
        });
        popup.create();
        popup.show();
    }
    // FIM MENSAGENS

    // ONCLICK
    public void aplicar(View v) {

        this.exibirConfirmacaoAplicacao();
    }

    public void finalizarSimulacao(View v) {

        this.redirecionarSimulador();
    }
    // FIM ONCLICK

    // MÉTODOS DE CONTROLE
    public void desabilitarItens() {

        this.button_aplicar.setEnabled(false);
        this.button_finalizarSimulacao.setEnabled(false);
    }

    public void redirecionarSimulador() {

        Intent activity = new Intent(SimuladorResultadoActivity.this, SimuladorActivity.class);
        startActivity(activity);
    }
    // DESABILITAR ITENS

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

                Intent activityCentral = new Intent(SimuladorResultadoActivity.this, CentralActivity.class);
                startActivity(activityCentral);
                return true;

            case R.id.action_perfilDaConta:

                Intent activityPerfilDaConta = new Intent(SimuladorResultadoActivity.this, PerfilContaActivity.class);
                startActivity(activityPerfilDaConta);
                return true;

            case R.id.action_somaSaldo:

                Intent activitySomaDeSaldo = new Intent(SimuladorResultadoActivity.this, SomaDeSaldoActivity.class);
                startActivity(activitySomaDeSaldo);
                return true;

            case R.id.action_subtracaoSaldo:

                Intent activitySubtracaoDeSaldo = new Intent(SimuladorResultadoActivity.this, SubtracaoDeSaldoActivity.class);
                startActivity(activitySubtracaoDeSaldo);
                return true;

            case R.id.action_lucroMensal:

                Intent activityLucroMensal = new Intent(SimuladorResultadoActivity.this, LucroMensalActivity.class);
                startActivity(activityLucroMensal);
                return true;

            case R.id.action_contasPagar:

                Intent activityContasAPagar = new Intent(SimuladorResultadoActivity.this, ContasAPagarActivity.class);
                startActivity(activityContasAPagar);
                return true;

            case R.id.action_simulador:

                Intent activitySimulador = new Intent(SimuladorResultadoActivity.this, SimuladorActivity.class);
                startActivity(activitySimulador);
                return true;

            case R.id.action_meta:

                Intent activityMeta = new Intent(SimuladorResultadoActivity.this, MetaActivity.class);
                startActivity(activityMeta);
                return true;


            case R.id.action_selecaoConta:

                Intent activitySelecaoConta = new Intent(SimuladorResultadoActivity.this, SelecaoContaActivity.class);
                startActivity(activitySelecaoConta);
                return true;

            case R.id.action_sair:

                Intent activityLogin = new Intent(SimuladorResultadoActivity.this, LoginActivity.class);
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

            carregarInformacoes();
            return true;
        }

        @Override
        protected void onPreExecute() {

            layout_resultadoSimulacao.setVisibility(View.GONE);
            load.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

            carregarElementos();
            layout_resultadoSimulacao.setVisibility(View.VISIBLE);
            if(Simulador.simuladorEntrada.isSimulandoMeta()) {

                layout_resultadoSimulacaoMeta.setVisibility(View.VISIBLE);
            }
            load.setVisibility(View.GONE);
        }
    }

    private class LongOperation2 extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            aplicarAdesaoDivida();
            aplicarExclusaoDivida();
            aplicarNovoBeneficio();
            aplicarExclusaoBenefico();
            return true;
        }

        @Override
        protected void onPreExecute() {

            desabilitarItens();
            load.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

            load.setVisibility(View.GONE);
            exibirSucessoAplicacao();
        }
    }
    // FIM CHAMADAS ASSINCRONAS
}









