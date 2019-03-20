package br.com.appcontazul.activity;

import android.content.DialogInterface;
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
import br.com.appcontazul.util.Adaptador05;
import br.com.appcontazul.util.Formatacao;

public class LucroMensalActivity extends AppCompatActivity {


    private ListView listaLucroMensal;
    private Adaptador05 adaptador05;
    private EditText editTextDescricaoDoBeneficio;
    private EditText editTextValorDoLucro;
    private TextView textViewValorFormatado;
    private TextView textViewRE30;
    private TextView textViewRE31;

    public View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucro_mensal);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        this.criarElementos();
        this.carregarLista();
        this.inicializarComportamentoEditTextValorDoLucro();
        this.inicializarComportamentoEditTextDescricaoDoBeneficio();
    }

    public void criarElementos() {

        this.editTextDescricaoDoBeneficio = (EditText) findViewById(R.id.editText_DescricaoDoBeneficio);
        this.editTextValorDoLucro = (EditText) findViewById(R.id.editText_ValorDoLucro);
        this.textViewValorFormatado = (TextView) findViewById(R.id.textView_ValorFormatado);
        this.textViewRE30 = (TextView) findViewById(R.id.textView_RE30);
        this.textViewRE31 = (TextView) findViewById(R.id.textView_RE31);
    }

    public void carregarLista() {

        this.listaLucroMensal = (ListView) findViewById(R.id.listaLucroMensal);
        Requisicao requisicao = new Requisicao();
        this.adaptador05 = new Adaptador05(requisicao.requestListaLucroMensal(), this);
        this.listaLucroMensal.setAdapter(adaptador05);
    }

    public void inicializarComportamentoEditTextValorDoLucro() {

        this.editTextValorDoLucro.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here

                // yourEditText...
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                textViewRE31.setText("");
                String sequencia = s.toString();
                Formatacao formatacao = new Formatacao();
                if(!sequencia.isEmpty()) {

                    textViewValorFormatado.setText(formatacao.formatarValorMonetario(s.toString()));
                } else {

                    textViewValorFormatado.setText(getResources().getString(R.string.activityLucroMensal_textViewValorFormatado));
                }
            }
        });
    }

    public void inicializarComportamentoEditTextDescricaoDoBeneficio() {

        this.editTextDescricaoDoBeneficio.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here

                // yourEditText...
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                textViewRE30.setText("");
            }
        });
    }

    public void contralMostrarMenu(View v) {

        Intent menuActivity = new Intent(LucroMensalActivity.this, MenuActivity.class);
        startActivity(menuActivity);
    }

    public void buttonIncluirLucro(View v) {

        if(validarCampos()) {

            Requisicao requisicao = new Requisicao();
            requisicao.requestInserirLucroMensal(this.editTextDescricaoDoBeneficio.getText().toString(),
                    this.editTextValorDoLucro.getText().toString());
            this.carregarLista();
            AlertDialog.Builder popup = new AlertDialog.Builder(LucroMensalActivity.this);
            popup.setTitle(R.string.activityLucroMensal_Sucesso);
            popup.setMessage(R.string.activityLucroMensal_REXX);
            popup.setPositiveButton(R.string.activityLucroMensal_OK, null);
            popup.create();
            popup.show();
            this.editTextDescricaoDoBeneficio.setText("");
            this.editTextValorDoLucro.setText("");
        }
    }

    public boolean validarCampos() {

        boolean validar = true;

        if(this.editTextDescricaoDoBeneficio.getText().toString().equals("")) {

            this.textViewRE30.setText(getResources().getString(R.string.activityLucroMensal_textViewRE30));
            validar = false;
        }

        if(this.editTextValorDoLucro.getText().toString().equals("")) {

            this.textViewRE31.setText(getResources().getString(R.string.activityLucroMensal_textViewRE31));
            validar = false;
        } else {

            double valor = Double.parseDouble(this.editTextValorDoLucro.getText().toString());
            if(valor <= 0) {

                this.textViewRE31.setText(getResources().getString(R.string.activitySomaSaldoMensagem));
                validar = false;
            }
        }

        return validar;
    }

    public void buttonReceber(View v2) {

        this.v = v2;
        AlertDialog.Builder popup = new AlertDialog.Builder(LucroMensalActivity.this);
        popup.setTitle(R.string.activityLucroMensal_Atencao);
        popup.setMessage(R.string.activityLucroMensal_RE35);
        popup.setPositiveButton(R.string.activityLucroMensal_ButtonSim, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            Requisicao requisicao = new Requisicao();
            requisicao.requestReceber("" + adaptador05.getItem(v.getId()).getId());
            carregarLista();
            AlertDialog.Builder popup = new AlertDialog.Builder(LucroMensalActivity.this);
            popup.setTitle(R.string.activityLucroMensal_Sucesso);
            popup.setMessage(R.string.activityLucroMensal_RE36);
            popup.setPositiveButton(R.string.activityLucroMensal_OK, null);
            popup.create();
            popup.show();

            }
        });
        popup.setNegativeButton(R.string.activityLucroMensal_ButtonNao, null);
        popup.create();
        popup.show();
    }

    public void buttonExcluir(View v2) {

        this.v = v2;
        AlertDialog.Builder popup = new AlertDialog.Builder(LucroMensalActivity.this);
        popup.setTitle(R.string.activityLucroMensal_Atencao);
        popup.setMessage(R.string.activityLucroMensal_RE33);
        popup.setPositiveButton(R.string.activityLucroMensal_ButtonSim, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                Requisicao requisicao = new Requisicao();
                requisicao.requestExcluirLucroMensal("" + adaptador05.getItem(v.getId()).getId());
                carregarLista();
                AlertDialog.Builder popup = new AlertDialog.Builder(LucroMensalActivity.this);
                popup.setTitle(R.string.activityLucroMensal_Sucesso);
                popup.setMessage(R.string.activityLucroMensal_RE34);
                popup.setPositiveButton(R.string.activityLucroMensal_OK, null);
                popup.create();
                popup.show();

            }
        });
        popup.setNegativeButton(R.string.activityLucroMensal_ButtonNao, null);
        popup.create();
        popup.show();
    }
}
