package br.com.appcontazul.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import br.com.appcontazul.R;
import br.com.appcontazul.rest.Requisicao;
import br.com.appcontazul.rest.model.ListaSomaSaldo;
import br.com.appcontazul.util.Adaptador03;
import br.com.appcontazul.util.Formatacao;

public class SomaDeSaldoActivity extends AppCompatActivity {

    private double saldoAtual;
    private ListView listaSomaSaldo;
    private Adaptador03 adaptador03;
    private TextView textViewValorFormatado;
    private EditText editTextValorDaMovimentacao;
    private TextView textViewSaldoConta;
    private EditText editTextDescricaoDaMovimentacao;
    private TextView textViewRE28;
    private TextView textViewRE29;
    private TextView textViewTituloSomaSaldo;
    private Button buttonInserirMovimentacao;
    ProgressBar pbHeaderProgress;
    boolean campoDescricaoObrigatorio;
    boolean campoValorObrigatorio;
    boolean campoValorZerado;
    boolean layoutListaSomaSaldoMostrando;
    private LinearLayout layoutListaSomaDeSaldo;
    private Button buttonSomadesaldo;
    private LinearLayout layoutIncluirSomaDeSaldo;
    private boolean listaVazia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_soma_de_saldo);

        this.criarElementos();
        this.formatarSaldo();
        this.atualizarListaSomaSaldo();
        this.carregarListaSomaSaldo();
        this.inicializarComportamentoEditTextDescricaoDaMovimentacao();
        this.inicializarComportamentoEditTextValorDaMovimentacao();
        this.setMensagem();
    }

    public void criarElementos() {

        this.listaSomaSaldo = (ListView) findViewById(R.id.listaSaldoConta);
        this.editTextValorDaMovimentacao = (EditText) findViewById(R.id.editText_ValorDaMovimentacao);
        this.textViewSaldoConta = (TextView) findViewById(R.id.textView_SaldodaConta);
        this.editTextDescricaoDaMovimentacao = (EditText) findViewById(R.id.editText_DescricaoMovimentacao);
        this.textViewValorFormatado = (TextView) findViewById(R.id.activityPerfilConta_textViewValorFormatado);
        this.textViewRE28 = (TextView) findViewById(R.id.textView_RE28);
        this.textViewRE29 = (TextView) findViewById(R.id.textView_RE29);
        this.pbHeaderProgress = (ProgressBar) findViewById(R.id.pbHeaderProgress);
        this.campoDescricaoObrigatorio = false;
        this.campoValorObrigatorio = false;
        this.campoValorZerado = false;
        this.layoutListaSomaSaldoMostrando = true;
        this.layoutListaSomaDeSaldo = (LinearLayout) findViewById(R.id.layout_lista_soma_de_saldo);
        this.buttonSomadesaldo = (Button) findViewById(R.id.button_somadesaldo);
        this.layoutIncluirSomaDeSaldo = (LinearLayout) findViewById(R.id.layout_incluir_soma_de_saldo);
        this.textViewTituloSomaSaldo = (TextView) findViewById(R.id.textView_tituloSomaSaldo);
        this.buttonInserirMovimentacao = (Button) findViewById(R.id.button_InserirMovimentacao);
        this.listaVazia = false;
    }

    public void carregarListaSomaSaldo() {

        this.listaSomaSaldo.setAdapter(this.adaptador03);
    }

    public void atualizarListaSomaSaldo() {

        Requisicao requisicao = new Requisicao();
        List<ListaSomaSaldo> somaSaldo = requisicao.requestListaSomaSaldo();
        this.listaVazia = somaSaldo.size() == 0;
        this.adaptador03 = new Adaptador03(somaSaldo,this);
    }

    public void setMensagem() {

        if(!listaVazia)
            this.textViewTituloSomaSaldo.setText("Soma de saldo do mês atual:");
    }

    public void formatarSaldo() {

        Requisicao requisicao = new Requisicao();
        this.saldoAtual = requisicao.requestSaldo().getSaldo();
        Formatacao formatacao = new Formatacao();
        this.textViewSaldoConta.setText(getResources().getString(
                R.string.activitySomadeSaldo_saldoAtual) + " " + formatacao.formatarValorMonetario("" + saldoAtual));
    }

    public void inicializarComportamentoEditTextDescricaoDaMovimentacao() {

        this.editTextDescricaoDaMovimentacao.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {


            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

                textViewRE28.setText("");
            }
        });
    }

    public void inicializarComportamentoEditTextValorDaMovimentacao() {

        editTextValorDaMovimentacao.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here

                // yourEditText...
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                textViewRE29.setText("");
                String sequencia = s.toString();
                Formatacao formatacao = new Formatacao();
                if(!sequencia.isEmpty()) {

                    String formatado = formatacao.formatarValorMonetario(s.toString());
                    double saldoSomado = saldoAtual + Double.parseDouble(s.toString());
                    textViewSaldoConta.setText(getResources().getString(
                            R.string.activitySomadeSaldo_saldoAtual) + " " + formatacao.formatarValorMonetario("" + saldoSomado));
                    textViewValorFormatado.setText(formatado);
                } else {

                    textViewSaldoConta.setText(getResources().getString(
                            R.string.activitySomadeSaldo_saldoAtual) + " " + formatacao.formatarValorMonetario("" + saldoAtual));
                    textViewValorFormatado.setText("R$0,00");
                }
            }
        });
    }

    public void trocarTela(View v) {

        if(layoutListaSomaSaldoMostrando) {

            layoutListaSomaSaldoMostrando = false;
            this.buttonSomadesaldo.setText(getResources().getString(R.string.activitySomaSaldoOpcaoListarSomaDeSaldo));
            this.layoutListaSomaDeSaldo.setVisibility(View.GONE);
            this.layoutIncluirSomaDeSaldo.setVisibility(View.VISIBLE);
            this.textViewTituloSomaSaldo.setVisibility(View.GONE);
        } else {

            layoutListaSomaSaldoMostrando = true;
            this.buttonSomadesaldo.setText(getResources().getString(R.string.activitySomaSaldoOpcaoIncluirSomaDeSaldo));
            this.textViewRE28.setText("");
            this.textViewRE29.setText("");
            this.layoutIncluirSomaDeSaldo.setVisibility(View.GONE);
            this.layoutListaSomaDeSaldo.setVisibility(View.VISIBLE);
            this.textViewTituloSomaSaldo.setVisibility(View.VISIBLE);
        }
    }

    public void buttonInserirMovimentacao(View v){

        LongOperation longOperation = new LongOperation();
        longOperation.execute();
    }

    public void mostrarPopupSucesso() {

        AlertDialog.Builder popup = new AlertDialog.Builder(SomaDeSaldoActivity.this);
        popup.setTitle(R.string.activitySelacaoConta_tituloSucesso);
        popup.setMessage(R.string.activitySomadeSaldo_RE27);
        popup.setPositiveButton(R.string.activitySomadeSaldobutton_ok, null);
        popup.create();
        popup.show();
        editTextDescricaoDaMovimentacao.setText("");
        editTextValorDaMovimentacao.setText("");
    }

    public void incluirSomaSaldo() {

        Requisicao requisicao = new Requisicao();
        requisicao.requestInserirSomaSaldo(editTextDescricaoDaMovimentacao.getText().toString(),
                editTextValorDaMovimentacao.getText().toString());
        this.saldoAtual = requisicao.requestSaldo().getSaldo();
        this.atualizarListaSomaSaldo();
    }

    public boolean validarCampos(){

        boolean validar = true;

        if(editTextDescricaoDaMovimentacao.getText().toString().equals("")){

            this.campoDescricaoObrigatorio = true;
            validar = false;

        }

        if (editTextValorDaMovimentacao.getText().toString().equals("")) {

            this.campoValorObrigatorio = true;
            validar = false;
        } else {

            double valor = Double.parseDouble(editTextValorDaMovimentacao.getText().toString());

            if (valor == 0) {

                this.campoValorZerado = true;
                validar = false;
            }

        }

        return validar;

    }

    public void checarValidacoes() {

        if(this.campoDescricaoObrigatorio) {

            textViewRE28.setText(R.string.activitySomadeSaldo_textViewRE28);
        }

        if(this.campoValorObrigatorio) {

            textViewRE29.setText(R.string.activitySomadeSaldo_textViewRE29);
        }

        if(this.campoValorZerado) {

            textViewRE29.setText(R.string.activitySomaSaldoMensagem);
        }

        this.campoDescricaoObrigatorio = false;
        this.campoValorObrigatorio = false;
        this.campoValorZerado = false;
    }

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

                Intent activityCentral = new Intent(SomaDeSaldoActivity.this, CentralActivity.class);
                startActivity(activityCentral);
                return true;

            case R.id.action_perfilDaConta:

                Intent activityPerfilDaConta = new Intent(SomaDeSaldoActivity.this, PerfilContaActivity.class);
                startActivity(activityPerfilDaConta);
                return true;

            case R.id.action_somaSaldo:

                Intent activitySomaDeSaldo = new Intent(SomaDeSaldoActivity.this, SomaDeSaldoActivity.class);
                startActivity(activitySomaDeSaldo);
                return true;

            case R.id.action_subtracaoSaldo:

                Intent activitySubtracaoDeSaldo = new Intent(SomaDeSaldoActivity.this, SubtracaoDeSaldoActivity.class);
                startActivity(activitySubtracaoDeSaldo);
                return true;

            case R.id.action_lucroMensal:

                Intent activityLucroMensal = new Intent(SomaDeSaldoActivity.this, LucroMensalActivity.class);
                startActivity(activityLucroMensal);
                return true;

            case R.id.action_selecaoConta:

                Intent activitySelecaoConta = new Intent(SomaDeSaldoActivity.this, SelecaoContaActivity.class);
                startActivity(activitySelecaoConta);
                return true;

            case R.id.action_contasPagar:

                Intent activityContasAPagar = new Intent(SomaDeSaldoActivity.this, ContasAPagarActivity.class);
                startActivity(activityContasAPagar);
                return true;

            case R.id.action_simulador:

                Intent activitySimulador = new Intent(SomaDeSaldoActivity.this, SimuladorActivity.class);
                startActivity(activitySimulador);
                return true;

            case R.id.action_meta:

                Intent activityMeta = new Intent(SomaDeSaldoActivity.this, MetaActivity.class);
                startActivity(activityMeta);
                return true;

            case R.id.action_sair:

                Intent activityLogin = new Intent(SomaDeSaldoActivity.this, LoginActivity.class);
                startActivity(activityLogin);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void desabilitarItens() {

        this.editTextDescricaoDaMovimentacao.setEnabled(false);
        this.editTextValorDaMovimentacao.setEnabled(false);
        this.buttonInserirMovimentacao.setEnabled(false);
    }

    public void habilitarItens() {

        this.editTextDescricaoDaMovimentacao.setEnabled(true);
        this.editTextValorDaMovimentacao.setEnabled(true);
        this.buttonInserirMovimentacao.setEnabled(true);
    }

    private class LongOperation extends AsyncTask<Void, Void, Boolean> {


        @Override
        protected Boolean doInBackground(Void... voids) {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!validarCampos()) {

                return false;
            }

            incluirSomaSaldo();
            return true;
        }

        @Override
        protected void onPreExecute() {

            desabilitarItens();
            pbHeaderProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

            if(aBoolean) {

                pbHeaderProgress.setVisibility(View.GONE);
                carregarListaSomaSaldo();
                setMensagem();
                habilitarItens();
                mostrarPopupSucesso();
            }

            pbHeaderProgress.setVisibility(View.GONE);
            habilitarItens();
            checarValidacoes();
        }
    }
}
















