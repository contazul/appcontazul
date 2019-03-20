package br.com.appcontazul.activity;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import br.com.appcontazul.R;
import br.com.appcontazul.rest.Requisicao;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_soma_de_saldo);

        this.criarElementos();
        this.formatarSaldo();
        this.carregarListaSomaSaldo();
        this.inicializarComportamentoEditTextDescricaoDaMovimentacao();
        this.inicializarComportamentoEditTextValorDaMovimentacao();
    }

    public void criarElementos() {

        this.listaSomaSaldo = (ListView) findViewById(R.id.listaSaldoConta);
        this.editTextValorDaMovimentacao = (EditText) findViewById(R.id.editText_ValorDaMovimentacao);
        this.textViewSaldoConta = (TextView) findViewById(R.id.textView_SaldodaConta);
        this.editTextDescricaoDaMovimentacao = (EditText) findViewById(R.id.editText_DescricaoMovimentacao);
        this.textViewValorFormatado = (TextView) findViewById(R.id.activityPerfilConta_textViewValorFormatado);
        this.textViewRE28 = (TextView) findViewById(R.id.textView_RE28);
        this.textViewRE29 = (TextView) findViewById(R.id.textView_RE29);
    }

    public void carregarListaSomaSaldo() {

        Requisicao requisicao = new Requisicao();
        this.adaptador03 = new Adaptador03(requisicao.requestListaSomaSaldo(),this);
        this.listaSomaSaldo.setAdapter(this.adaptador03);
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

    public void buttonInserirMovimentacao(View v){

        if (validarCampos()){

            Requisicao requisicao = new Requisicao();
            requisicao.requestInserirSomaSaldo(editTextDescricaoDaMovimentacao.getText().toString(),
                    editTextValorDaMovimentacao.getText().toString());
            this.saldoAtual = requisicao.requestSaldo().getSaldo();
            this.carregarListaSomaSaldo();
            AlertDialog.Builder popup = new AlertDialog.Builder(SomaDeSaldoActivity.this);
            popup.setTitle(R.string.activitySelacaoConta_tituloSucesso);
            popup.setMessage(R.string.activitySomadeSaldo_RE27);
            popup.setPositiveButton(R.string.activitySomadeSaldobutton_ok, null);
            popup.create();
            popup.show();
            editTextDescricaoDaMovimentacao.setText("");
            editTextValorDaMovimentacao.setText("");
        }
    }

    public boolean validarCampos(){

        boolean validar = true;

        if(editTextDescricaoDaMovimentacao.getText().toString().equals("")){

            textViewRE28.setText(R.string.activitySomadeSaldo_textViewRE28);
            validar = false;

        }

        if (editTextValorDaMovimentacao.getText().toString().equals("")) {

            textViewRE29.setText(R.string.activitySomadeSaldo_textViewRE29);
            validar = false;
        } else {

            double valor = Double.parseDouble(editTextValorDaMovimentacao.getText().toString());

            if (valor == 0) {

                textViewRE29.setText(R.string.activitySomaSaldoMensagem);
                validar = false;
            }

        }

        return validar;

    }

    public void contralMostrarMenu(View v) {

        Intent menuActivity = new Intent(SomaDeSaldoActivity.this, MenuActivity.class);
        startActivity(menuActivity);
    }
}
