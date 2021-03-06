package br.com.appcontazul.activity;

import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

import br.com.appcontazul.R;
import br.com.appcontazul.rest.Requisicao;
import br.com.appcontazul.rest.model.ListaSubtracaoSaldo;
import br.com.appcontazul.util.Adaptador04;
import br.com.appcontazul.util.Formatacao;

public class SubtracaoDeSaldoActivity extends AppCompatActivity {

    private String prioridadeSelecionada;
    private double saldoAtual;
    private ListView listaSubtracaoSaldo;
    private Adaptador04 adaptador04;
    private TextView textViewValorFormatado;
    private EditText editTextValorDaMovimentacao;
    private TextView textViewSaldoConta;
    private EditText editTextDescricaoDaMovimentacao;
    private TextView textViewRE28;
    private TextView textViewRE29;
    private RadioButton buttonAlta;
    private ProgressBar pbHeaderProgress;
    private boolean campoDescricaoVazio;
    private boolean campoValorVazio;
    private boolean valorZero;
    private Button buttonSubtracaoSaldo;
    private LinearLayout layoutListaSubtracaoSaldo;
    private LinearLayout layoutIncluirSubtracaoDeSaldo;
    private boolean mostrandoLista;
    private TextView textViewTituloSubtracaoSaldo;
    private Button buttonInserirMovimentacao;
    private boolean listaVazia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtracao_de_saldo);

        this.criarElementos();
        this.formatarSaldo();
        this.carregarListaSubtracaoSaldo();
        carregarLista();
        this.inicializarComportamentoEditTextDescricaoDaMovimentacao();
        this.inicializarComportamentoEditTextValorDaMovimentacao();
        this.setMensagem();
    }

    public void criarElementos() {

        this.editTextValorDaMovimentacao = (EditText) findViewById(R.id.editText_ValorDaMovimentacao);
        this.textViewSaldoConta = (TextView) findViewById(R.id.textView_SaldodaConta);
        this.editTextDescricaoDaMovimentacao = (EditText) findViewById(R.id.editText_DescricaoMovimentacao);
        this.textViewValorFormatado = (TextView) findViewById(R.id.activityPerfilConta_textViewValorFormatado);
        this.textViewRE28 = (TextView) findViewById(R.id.textView_RE28);
        this.textViewRE29 = (TextView) findViewById(R.id.textView_RE29);
        this.buttonAlta = (RadioButton) findViewById(R.id.button_Alta);
        this.prioridadeSelecionada = "Alta";
        buttonAlta.setChecked(true);
        buttonAlta.setSelected(true);
        this.pbHeaderProgress = (ProgressBar) findViewById(R.id.pbHeaderProgress);
        this.campoDescricaoVazio = false;
        this.campoValorVazio = false;
        this.valorZero = false;
        this.buttonSubtracaoSaldo = (Button) findViewById(R.id.button_subtracaoSaldo);
        this.layoutListaSubtracaoSaldo = (LinearLayout) findViewById(R.id.layout_lista_subtracao_saldo);
        this.layoutIncluirSubtracaoDeSaldo = (LinearLayout) findViewById(R.id.layout_incluir_subtracao_de_saldo);
        this.mostrandoLista = true;
        this.textViewTituloSubtracaoSaldo = (TextView) findViewById(R.id.textView_tituloSubtracaoSaldo);
        this.buttonInserirMovimentacao = (Button) findViewById(R.id.button_InserirMovimentacao);
        this.listaVazia = false;
        this.listaSubtracaoSaldo = (ListView) findViewById(R.id.listaSaldoConta);
    }

    public void formatarSaldo() {

        Requisicao requisicao = new Requisicao();
        Formatacao formatacao = new Formatacao();
        this.saldoAtual = requisicao.requestSaldo().getSaldo();
        this.textViewSaldoConta.setText(getResources().getString(R.string.activitySubtracaodeSaldoSaldoAtual) + " " + formatacao.formatarValorMonetario("" + saldoAtual));
    }

    public void carregarLista() {

        this.listaSubtracaoSaldo.setAdapter(this.adaptador04);
    }

    public void carregarListaSubtracaoSaldo() {

        Requisicao requisicao = new Requisicao();
        List<ListaSubtracaoSaldo> subtracaoSaldo = requisicao.requestListaSubtracaoSaldo();
        this.adaptador04 = new Adaptador04(subtracaoSaldo,this);
        this.listaVazia = subtracaoSaldo.size() == 0;
    }

    public void setMensagem() {

        if(!listaVazia)
            this.textViewTituloSubtracaoSaldo.setText("Subtração de saldo do mês atual");
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

                textViewRE28.setVisibility(View.GONE);
            }
        });

    }

    public void inicializarComportamentoEditTextValorDaMovimentacao() {

        this.editTextValorDaMovimentacao.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here

                // yourEditText...
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                textViewRE29.setVisibility(View.GONE);
                String sequencia = s.toString();
                Formatacao formatacao = new Formatacao();
                if(!sequencia.isEmpty()) {

                    String formatado = formatacao.formatarValorMonetario(s.toString());
                    double saldoSubtracao = saldoAtual - Double.parseDouble(s.toString());
                    textViewSaldoConta.setText(getResources().getString(R.string.activitySubtracaodeSaldoSaldoAtual) + " " +
                            formatacao.formatarValorMonetario("" + saldoSubtracao));
                    textViewValorFormatado.setText(formatado);
                } else {

                    textViewSaldoConta.setText(getResources().getString(R.string.activitySubtracaodeSaldoSaldoAtual) + " " +
                            formatacao.formatarValorMonetario("" + saldoAtual));
                    textViewValorFormatado.setText("R$0,00");
                }
            }
        });
    }

    public void onRadioButtonClicked (View v) {

        boolean checked = ((RadioButton) v).isChecked();

        switch(((RadioButton) v).getId()) {
            case R.id.button_Alta:
                if (checked)

                    this.prioridadeSelecionada = "Alta";

                    break;
            case R.id.button_Baixa:
                if (checked)

                    this.prioridadeSelecionada = "Baixa";

                    break;
        }
    }

    public void subtracaoSaldoTrocarTela(View v) {

        if(mostrandoLista) {

            this.mostrandoLista = false;
            this.buttonSubtracaoSaldo.setText(getResources().getString(R.string.activitySubtracaodeSaldoListarSubtracaoSaldo));
            this.layoutListaSubtracaoSaldo.setVisibility(View.GONE);
            this.layoutIncluirSubtracaoDeSaldo.setVisibility(View.VISIBLE);
            this.textViewTituloSubtracaoSaldo.setVisibility(View.GONE);
        } else {

            this.mostrandoLista = true;
            textViewRE28.setVisibility(View.GONE);
            textViewRE29.setVisibility(View.GONE);
            this.buttonSubtracaoSaldo.setText(getResources().getString(R.string.activitySubtracaodeSaldoIncluirSubtracaoSaldo));
            this.layoutIncluirSubtracaoDeSaldo.setVisibility(View.GONE);
            this.layoutListaSubtracaoSaldo.setVisibility(View.VISIBLE);
            this.textViewTituloSubtracaoSaldo.setVisibility(View.VISIBLE);
        }
    }

    public void buttonInserirMovimentacao(View v){

        LongOperation longOperation = new LongOperation();
        longOperation.execute();
    }

    public void inserirSubtracaoSaldo() {

        Requisicao requisicao = new Requisicao();
        requisicao.requestInserirSubtracaoSaldo(editTextValorDaMovimentacao.getText().toString(),
                editTextDescricaoDaMovimentacao.getText().toString(), prioridadeSelecionada);
        carregarListaSubtracaoSaldo();
    }

    public void mostrarSucesso() {

        this.formatarSaldo();
        this.carregarListaSubtracaoSaldo();
        AlertDialog.Builder popup = new AlertDialog.Builder(SubtracaoDeSaldoActivity.this);
        popup.setTitle(R.string.activitySelacaoConta_tituloSucesso);
        popup.setMessage(R.string.activitySomadeSaldo_RE27);
        popup.setPositiveButton(R.string.activitySomadeSaldobutton_ok, null);
        popup.create();
        popup.show();
        editTextDescricaoDaMovimentacao.setText("");
        editTextValorDaMovimentacao.setText("");
    }

    public boolean validarCampo(){

        boolean validar = true;

        if(editTextDescricaoDaMovimentacao.getText().toString().equals("")){

            this.campoDescricaoVazio = true;
            validar = false;

        }

        if (editTextValorDaMovimentacao.getText().toString().equals("")) {

            this.campoValorVazio = true;
            validar = false;
        } else {

            double valor = Double.parseDouble(editTextValorDaMovimentacao.getText().toString());

            if (valor == 0) {

                this.valorZero = true;
                validar = false;
            }
        }

        return validar;
    }

    public void checarValidacoes() {

        if(this.campoDescricaoVazio) {

            textViewRE28.setVisibility(View.VISIBLE);
            textViewRE28.setText(R.string.activitySomadeSaldo_textViewRE28);
        }

        if(this.campoValorVazio) {

            textViewRE29.setVisibility(View.VISIBLE);
            textViewRE29.setText(R.string.activitySomadeSaldo_textViewRE29);
        } else if(this.valorZero) {

            textViewRE29.setVisibility(View.VISIBLE);
            textViewRE29.setText(R.string.activitySomaSaldoMensagem);
        }

        this.campoDescricaoVazio = false;
        this.campoValorVazio = false;
        this.valorZero = false;
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

                Intent activityCentral = new Intent(SubtracaoDeSaldoActivity.this, CentralActivity.class);
                startActivity(activityCentral);
                return true;

            case R.id.action_perfilDaConta:

                Intent activityPerfilDaConta = new Intent(SubtracaoDeSaldoActivity.this, PerfilContaActivity.class);
                startActivity(activityPerfilDaConta);
                return true;

            case R.id.action_somaSaldo:

                Intent activitySomaDeSaldo = new Intent(SubtracaoDeSaldoActivity.this, SomaDeSaldoActivity.class);
                startActivity(activitySomaDeSaldo);
                return true;

            case R.id.action_subtracaoSaldo:

                Intent activitySubtracaoDeSaldo = new Intent(SubtracaoDeSaldoActivity.this, SubtracaoDeSaldoActivity.class);
                startActivity(activitySubtracaoDeSaldo);
                return true;

            case R.id.action_lucroMensal:

                Intent activityLucroMensal = new Intent(SubtracaoDeSaldoActivity.this, LucroMensalActivity.class);
                startActivity(activityLucroMensal);
                return true;

            case R.id.action_selecaoConta:

                Intent activitySelecaoConta = new Intent(SubtracaoDeSaldoActivity.this, SelecaoContaActivity.class);
                startActivity(activitySelecaoConta);
                return true;

            case R.id.action_contasPagar:

                Intent activityContasAPagar = new Intent(SubtracaoDeSaldoActivity.this, ContasAPagarActivity.class);
                startActivity(activityContasAPagar);
                return true;

            case R.id.action_simulador:

                Intent activitySimulador = new Intent(SubtracaoDeSaldoActivity.this, SimuladorActivity.class);
                startActivity(activitySimulador);
                return true;

            case R.id.action_meta:

                Intent activityMeta = new Intent(SubtracaoDeSaldoActivity.this, MetaActivity.class);
                startActivity(activityMeta);
                return true;

            case R.id.action_sair:

                Intent activityLogin = new Intent(SubtracaoDeSaldoActivity.this, LoginActivity.class);
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
            if(!validarCampo()) {

                return false;
            }

            inserirSubtracaoSaldo();
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
                carregarLista();
                setMensagem();
                habilitarItens();
                mostrarSucesso();
            }

            pbHeaderProgress.setVisibility(View.GONE);
            habilitarItens();
            checarValidacoes();
        }
    }
}
