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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import br.com.appcontazul.R;
import br.com.appcontazul.contentstatic.ReferenciaUsuario;
import br.com.appcontazul.rest.model.ListaSomaSaldo;
import br.com.appcontazul.rest.teste.ListaSubtracaoSaldoRepository;
import br.com.appcontazul.util.Adaptador03;
import br.com.appcontazul.util.Adaptador04;
import br.com.appcontazul.util.Formatacao;

import static br.com.appcontazul.R.id.button_Alta;

public class SubtracaoDeSaldoActivity extends AppCompatActivity {

    String prioridadeSelecionada;
    private static final double saldoAtual = 500;
    private ListView listaSubtracaoSaldo;
    private Adaptador04 adaptador04;
    private TextView textViewValorFormatado;
    EditText editTextValorDaMovimentacao;
    TextView textViewSaldoConta;
    EditText editTextDescricaoDaMovimentacao;
    TextView textViewRE28;
    TextView textViewRE29;
    RadioButton buttonAlta;
    RadioButton buttonBaixa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtracao_de_saldo);

        this.listaSubtracaoSaldo = (ListView) findViewById(R.id.listaSaldoConta);
        ListaSubtracaoSaldoRepository listaSubtracao = new ListaSubtracaoSaldoRepository();
        this.adaptador04 = new Adaptador04(listaSubtracao.getListaSubtracaoSaldo(),this);
        this.listaSubtracaoSaldo.setAdapter(this.adaptador04);
        this.editTextValorDaMovimentacao = (EditText) findViewById(R.id.editText_ValorDaMovimentacao);
        this.textViewSaldoConta = (TextView) findViewById(R.id.textView_SaldodaConta);
        this.editTextDescricaoDaMovimentacao = (EditText) findViewById(R.id.editText_DescricaoMovimentacao);
        this.textViewValorFormatado = (TextView) findViewById(R.id.textView_valorFormatado);
        this.textViewRE28 = (TextView) findViewById(R.id.textView_RE28);
        this.textViewRE29 = (TextView) findViewById(R.id.textView_RE29);
        this.buttonAlta = (RadioButton) findViewById(R.id.button_Alta);
        this.buttonBaixa = (RadioButton) findViewById(R.id.button_Baixa);
        this.prioridadeSelecionada = "alta";
        buttonAlta.setChecked(true);
        buttonAlta.setSelected(true);

        Formatacao formatacao = new Formatacao();

        this.textViewSaldoConta.setText(getResources().getString(
                R.string.activitySomadeSaldo_saldoAtual) + " " + formatacao.formatarValorMonetario("" + saldoAtual));

        editTextDescricaoDaMovimentacao.addTextChangedListener(new TextWatcher() {

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

        editTextValorDaMovimentacao.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {


            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

                textViewRE29.setText("");
            }
        });

       editTextValorDaMovimentacao.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here

                // yourEditText...
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String sequencia = s.toString();
                Formatacao formatacao = new Formatacao();
                if(!sequencia.isEmpty()) {

                    String formatado = formatacao.formatarValorMonetario(s.toString());
                    double saldoSubtracao = saldoAtual - Double.parseDouble(s.toString());
                    textViewSaldoConta.setText(getResources().getString(
                            R.string.activitySomadeSaldo_saldoAtual) + " " + formatacao.formatarValorMonetario("" + saldoSubtracao));
                    textViewValorFormatado.setText(formatado);
                } else {

                    textViewSaldoConta.setText(getResources().getString(
                            R.string.activitySomadeSaldo_saldoAtual) + " " + formatacao.formatarValorMonetario("" + saldoAtual));
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

                    this.prioridadeSelecionada = "alta";

                    break;
            case R.id.button_Baixa:
                if (checked)

                    this.prioridadeSelecionada = "baixa";

                    break;
        }
    }


    public void contralMostrarMenu(View v) {

        Intent menuActivity = new Intent(SubtracaoDeSaldoActivity.this, MenuActivity.class);
        startActivity(menuActivity);
    }

    public void buttonInserirMovimentacao(View v){

        if (validarCampo()){
            AlertDialog.Builder popup = new AlertDialog.Builder(SubtracaoDeSaldoActivity.this);
            popup.setTitle(R.string.activitySelacaoConta_tituloSucesso);
            popup.setMessage(R.string.activitySomadeSaldo_RE27);
            popup.setPositiveButton(R.string.activitySomadeSaldobutton_ok, null);
            popup.create();
            popup.show();
            editTextDescricaoDaMovimentacao.setText("");
            editTextValorDaMovimentacao.setText("");
        }



    }

    public boolean validarCampo(){

        boolean validar = true;

        if(editTextDescricaoDaMovimentacao.getText().toString().equals("")){

            textViewRE28.setText(R.string.activitySomadeSaldo_textViewRE28);
            validar = false;

        }

        if (editTextValorDaMovimentacao.getText().toString().equals("")) {

            textViewRE29.setText(R.string.activitySomadeSaldo_textViewRE29);
            validar = false;
        }

        return validar;


    }


}
