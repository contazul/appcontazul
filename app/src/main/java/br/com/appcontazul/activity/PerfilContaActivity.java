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
import android.widget.ProgressBar;
import android.widget.TextView;

import br.com.appcontazul.R;
import br.com.appcontazul.rest.Requisicao;
import br.com.appcontazul.rest.model.PerfilContazul;
import br.com.appcontazul.util.Formatacao;

public class PerfilContaActivity extends AppCompatActivity {

    private TextView textViewPerfilConta;
    private TextView textViewNumeroContazul;
    private TextView textViewStatus;
    private EditText editTextDescricaoConta;
    private EditText editTextValorIdeal;
    private TextView textViewValidacaoValor0;
    private TextView activityPerfilContaTextViewValorFormatado;
    ProgressBar pbHeaderProgress;
    private Button buttonDefinir;
    boolean camposVazios;
    boolean campoValorZerado;
    PerfilContazul perfilContazul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_perfil_conta);

        this.criarElementos();
        this.carregarElementos();
        this.popularElementos();
        this.inicializarComportamentoEditTextValorIdeal();
    }

    public void criarElementos() {

        this.textViewPerfilConta = (TextView) findViewById(R.id.textView_perfilConta);
        this.textViewNumeroContazul = (TextView) findViewById(R.id.textView_numeroContazul);
        this.textViewStatus = (TextView) findViewById(R.id.textView_status);
        this.editTextDescricaoConta = (EditText) findViewById(R.id.editText_DescricaoConta);
        this.editTextValorIdeal = (EditText) findViewById(R.id.editText_ValorIdeal);
        this.textViewValidacaoValor0 = (TextView) findViewById(R.id.textView_validacaoValor0);
        this.activityPerfilContaTextViewValorFormatado = (TextView) findViewById(R.id.activityPerfilConta_textViewValorFormatado);
        this.buttonDefinir = (Button) findViewById(R.id.button_definir);
        this.pbHeaderProgress = (ProgressBar) findViewById(R.id.pbHeaderProgress);
        this.camposVazios = false;
        this.campoValorZerado = false;
    }

    public void carregarElementos() {

        Requisicao requisicao = new Requisicao();
        this.perfilContazul = requisicao.requestPerfilConta();
    }

    public void popularElementos() {

        this.textViewPerfilConta.setText(getResources().getString(R.string.activityPerfilConta_perfilDaConta)
                + " " + perfilContazul.getPerfil());

        this.textViewNumeroContazul.setText(getResources().getString(R.string.activityPerfilConta_numeroContazul)
                + " " + perfilContazul.getNumeroContazul());

        this.textViewStatus.setText(getResources().getString(R.string.activityPerfilConta_status)
                + " " + perfilContazul.getStatus());
    }

    public void inicializarComportamentoEditTextValorIdeal() {

        editTextValorIdeal.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here

                // yourEditText...
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                textViewValidacaoValor0.setVisibility(View.INVISIBLE);
                String sequencia = s.toString();
                Formatacao formatacao = new Formatacao();
                if(!sequencia.isEmpty()) {

                    String formatado = formatacao.formatarValorMonetario(sequencia);
                    activityPerfilContaTextViewValorFormatado.setText(formatado);
                } else {

                    activityPerfilContaTextViewValorFormatado.setText(getResources().getString(
                            R.string.activityPerfilConta_textViewValorValorFormatado));
                }
            }
        });
    }

    public boolean validar() {

        boolean validado = true;

        if(editTextDescricaoConta.getText().toString().isEmpty() &&
                editTextValorIdeal.getText().toString().isEmpty()) {

            this.camposVazios = true;
            validado = false;
        } else {

            if(!editTextValorIdeal.getText().toString().isEmpty()) {

                double valor = Double.parseDouble(editTextValorIdeal.getText().toString());
                if (valor == 0) {

                    this.campoValorZerado = true;
                    validado = false;
                }
            }
        }

        return validado;
    }

    public void checarValidacoes() {

        if(camposVazios){

            AlertDialog.Builder popup = new AlertDialog.Builder(PerfilContaActivity.this);
            popup.setTitle(R.string.activityPerfilConta_atencao);
            popup.setMessage(R.string.activityPerfilConta_mensagemPopup);
            popup.setPositiveButton(R.string.activityPerfilConta_OK, null);
            popup.create();
            popup.show();
        }

        if(campoValorZerado) {

            textViewValidacaoValor0.setVisibility(View.VISIBLE);
        }
        this.camposVazios = false;
        this.campoValorZerado = false;
    }

    public void definir(View v) {

        LongOperation longOperation = new LongOperation();
        longOperation.execute();
    }

    public void apresentarPopupSucesso() {

        this.editTextDescricaoConta.setText("");
        this.editTextValorIdeal.setText("");
        AlertDialog.Builder popup = new AlertDialog.Builder(PerfilContaActivity.this);
        popup.setTitle(R.string.activityPerfilConta_sucesso);
        popup.setMessage(R.string.activityPerfilConta_RE25);
        popup.setPositiveButton(R.string.activityPerfilConta_buttonPopupOk, null);
        popup.create();
        popup.show();
    }

    public void atualizarDadosPerfilConta() {

        String valorIdeal = this.editTextValorIdeal.getText().toString();
        if (valorIdeal.equals("")) {

            valorIdeal = "0.0";
        }
        Requisicao requisicao = new Requisicao();
        requisicao.requestAtualizarPerfilContazul(this.editTextDescricaoConta.getText().toString(),
                valorIdeal);
    }

    public void desabilitarItens() {

        editTextDescricaoConta.setEnabled(false);
        editTextValorIdeal.setEnabled(false);
        buttonDefinir.setEnabled(false);
    }

    public void habilitarItens() {

        editTextDescricaoConta.setEnabled(true);
        editTextValorIdeal.setEnabled(true);
        buttonDefinir.setEnabled(true);
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

                Intent activityCentral = new Intent(PerfilContaActivity.this, CentralActivity.class);
                startActivity(activityCentral);
                return true;

            case R.id.action_perfilDaConta:

                Intent activityPerfilDaConta = new Intent(PerfilContaActivity.this, PerfilContaActivity.class);
                startActivity(activityPerfilDaConta);
                return true;

            case R.id.action_somaSaldo:

                Intent activitySomaDeSaldo = new Intent(PerfilContaActivity.this, SomaDeSaldoActivity.class);
                startActivity(activitySomaDeSaldo);
                return true;

            case R.id.action_subtracaoSaldo:

                Intent activitySubtracaoDeSaldo = new Intent(PerfilContaActivity.this, SubtracaoDeSaldoActivity.class);
                startActivity(activitySubtracaoDeSaldo);
                return true;

            case R.id.action_lucroMensal:

                Intent activityLucroMensal = new Intent(PerfilContaActivity.this, LucroMensalActivity.class);
                startActivity(activityLucroMensal);
                return true;

            case R.id.action_selecaoConta:

                Intent activitySelecaoConta = new Intent(PerfilContaActivity.this, SelecaoContaActivity.class);
                startActivity(activitySelecaoConta);
                return true;

            case R.id.action_sair:

                Intent activityLogin = new Intent(PerfilContaActivity.this, LoginActivity.class);
                startActivity(activityLogin);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private class LongOperation extends AsyncTask<Void, Void, Boolean> {


        @Override
        protected Boolean doInBackground(Void... voids) {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(!validar()) {

                return false;
            }

            atualizarDadosPerfilConta();
            carregarElementos();
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
                popularElementos();
                habilitarItens();
                apresentarPopupSucesso();
            }

            pbHeaderProgress.setVisibility(View.GONE);
            habilitarItens();
            checarValidacoes();
        }
    }
}












