package br.com.appcontazul.activity;

import br.com.appcontazul.R;
import br.com.appcontazul.rest.Requisicao;
import br.com.appcontazul.rest.model.ListaContasAPagar;
import br.com.appcontazul.rest.teste.ListaContasAPagarRepository;
import br.com.appcontazul.util.Adaptador;
import br.com.appcontazul.util.Adaptador06;
import br.com.appcontazul.util.Formatacao;

import android.content.DialogInterface;
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
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

public class ContasAPagarActivity extends AppCompatActivity {

    private TextView textViewValorFormatado;
    private EditText editTextDescricaoDaConta;
    private EditText editTextValorDaconta;
    private EditText editTextParcelas;
    private RadioButton buttonAlta;
    private RadioButton buttonFixa;
    private String prioridadeSelecionada;
    private String tipoSelecionado;
    private ListView listaContaAPagar;
    private TextView textViewRE38;
    private TextView textViewRE39RE41;
    private ProgressBar pbHeaderProgress;
    private TextView textViewRE40;
    private TextView textViewRE42;
    private Adaptador06 adaptador06;
    private boolean mostrandoLista;
    private boolean campoDescricaoVazio;
    private boolean campoValorVazio;
    private boolean valorZero;
    private boolean valorZeroP;
    private boolean campoQuantidadeVazio;
    private LinearLayout layoutListaContasAPagar;
    private LinearLayout layoutInserirContasAPagar;
    private Button buttonContasAPagar;
    private String prestacaoSelecionada;
    private boolean aPrazo;
    private TextView textViewTituloContasAPagar;
    private boolean listaVazia;

    public View v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contas_apagar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        this.criarElementos();
        this.carregarLista();
        this.setLista();
        this.inicializarComportamentoEditTextDescricaoDaConta();
        this.inicializarComportamentoEditTextValorDaConta();
        this.inicializarComportamentoEditTextQuantidadeDeParcelas();
    }

    public void criarElementos() {

        this.editTextDescricaoDaConta = (EditText) findViewById(R.id.editText_DescricaoDaConta);
        this.editTextValorDaconta = (EditText) findViewById(R.id.editText_ValorDaConta);
        this.editTextParcelas = (EditText) findViewById(R.id.editText_Parcelas);

        this.textViewValorFormatado = (TextView) findViewById(R.id.textView_ValorFormatado);
        this.textViewRE38 = (TextView) findViewById(R.id.textView_RE38);
        this.textViewRE39RE41 = (TextView) findViewById(R.id.textView_RE39_RE41);
        this.textViewRE40 = (TextView) findViewById(R.id.textView_RE40);
        this.campoDescricaoVazio = false;
        this.campoValorVazio = false;
        this.valorZero = false;
        this.valorZeroP = false;
        this.campoQuantidadeVazio = false;
        this.pbHeaderProgress = (ProgressBar) findViewById(R.id.pbHeaderProgress);
        this.mostrandoLista = true;
        this.layoutInserirContasAPagar = (LinearLayout) findViewById(R.id.layout_InserirContasAPagar);
        this.layoutListaContasAPagar = (LinearLayout) findViewById(R.id.layout_listaContasAPagar);
        this.buttonContasAPagar = (Button) findViewById(R.id.button_contasapagar);
        this.buttonAlta = (RadioButton) findViewById(R.id.button_Alta);
        this.buttonFixa = (RadioButton) findViewById(R.id.button_Fixa);
        buttonAlta.setChecked(true);
        buttonAlta.setSelected(true);
        buttonFixa.setChecked(true);
        buttonFixa.setSelected(true);
        this.prioridadeSelecionada = "Alta";
        this.prestacaoSelecionada = "Fixa";
        this.aPrazo = false;
        this.textViewTituloContasAPagar = (TextView) findViewById(R.id.textView_tituloContasAPagar);
        this.listaVazia = false;
        this.listaContaAPagar = (ListView) findViewById(R.id.listaContasAPagar);
    }


    public void carregarLista() {

        Requisicao requisicao = new Requisicao();
        List<ListaContasAPagar> listaContasAPagar = requisicao.requestListaDeDividaMensal();
        this.listaVazia = listaContasAPagar.size() == 0;
        this.adaptador06 = new Adaptador06(listaContasAPagar, this);

    }

    public void setLista() {

        if(listaVazia)
            this.textViewTituloContasAPagar.setText("Não há contas á pagar cadastrada");
        else
            this.textViewTituloContasAPagar.setText("Lista de contas á pagar:");

        this.listaContaAPagar.setAdapter(adaptador06);
    }

    public void inicializarComportamentoEditTextValorDaConta() {

        this.editTextValorDaconta.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here

                // yourEditText...
            }


            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                textViewRE39RE41.setText("");
                String sequencia = s.toString();
                Formatacao formatacao = new Formatacao();
                if (!sequencia.isEmpty()) {

                    textViewValorFormatado.setText(formatacao.formatarValorMonetario(s.toString()));
                } else {

                    textViewValorFormatado.setText(getResources().getString(R.string.activityContasAPagar_textViewValorFormatado));
                }
            }
        });
    }

    public void inicializarComportamentoEditTextDescricaoDaConta() {

        this.editTextDescricaoDaConta.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }


            public void onTextChanged(CharSequence s, int start, int before, int count) {

                textViewRE38.setText("");

            }


            public void afterTextChanged(Editable s) {

            }
        });
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

                Intent activityCentral = new Intent(ContasAPagarActivity.this, CentralActivity.class);
                startActivity(activityCentral);
                return true;

            case R.id.action_perfilDaConta:

                Intent activityPerfilDaConta = new Intent(ContasAPagarActivity.this, PerfilContaActivity.class);
                startActivity(activityPerfilDaConta);
                return true;

            case R.id.action_somaSaldo:

                Intent activitySomaDeSaldo = new Intent(ContasAPagarActivity.this, SomaDeSaldoActivity.class);
                startActivity(activitySomaDeSaldo);
                return true;

            case R.id.action_subtracaoSaldo:

                Intent activitySubtracaoDeSaldo = new Intent(ContasAPagarActivity.this, SubtracaoDeSaldoActivity.class);
                startActivity(activitySubtracaoDeSaldo);
                return true;

            case R.id.action_lucroMensal:

                Intent activityLucroMensal = new Intent(ContasAPagarActivity.this, LucroMensalActivity.class);
                startActivity(activityLucroMensal);
                return true;

            case R.id.action_contasPagar:

                Intent activityContasAPagar = new Intent(ContasAPagarActivity.this, ContasAPagarActivity.class);
                startActivity(activityContasAPagar);
                return true;

            case R.id.action_selecaoConta:

                Intent activitySelecaoConta = new Intent(ContasAPagarActivity.this, SelecaoContaActivity.class);
                startActivity(activitySelecaoConta);
                return true;

            case R.id.action_simulador:

                Intent activitySimulador = new Intent(ContasAPagarActivity.this, SimuladorActivity.class);
                startActivity(activitySimulador);
                return true;

            case R.id.action_meta:

                Intent activityMeta = new Intent(ContasAPagarActivity.this, MetaActivity.class);
                startActivity(activityMeta);
                return true;

            case R.id.action_sair:

                Intent activityLogin = new Intent(ContasAPagarActivity.this, LoginActivity.class);
                startActivity(activityLogin);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void inicializarComportamentoEditTextQuantidadeDeParcelas() {

        this.editTextParcelas.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }


            public void onTextChanged(CharSequence s, int start, int before, int count) {

                textViewRE40.setText("");

            }


            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void contasAPagarTrocarTela(View v) {

        if (mostrandoLista) {

            this.mostrandoLista = false;
            this.buttonContasAPagar.setText("Listar");
            this.layoutListaContasAPagar.setVisibility(View.GONE);
            this.layoutInserirContasAPagar.setVisibility(View.VISIBLE);
            this.textViewTituloContasAPagar.setVisibility(View.GONE);
        } else {

            this.mostrandoLista = true;
            this.textViewRE39RE41.setText("");
            this.textViewRE38.setText("");
            this.textViewRE40.setText("");
            this.buttonContasAPagar.setText("Incluir");
            this.layoutInserirContasAPagar.setVisibility(View.GONE);
            this.layoutListaContasAPagar.setVisibility(View.VISIBLE);
            this.textViewTituloContasAPagar.setVisibility(View.VISIBLE);
        }
    }

    public void buttonIncluirContasAPagar (View v){

        LongOperation longOperation = new LongOperation();
        longOperation.execute();
    }

    public void interfaceIncluirContasAPagar() {

        int quantidadeParcela = 0;
        if(!this.editTextParcelas.getText().toString().isEmpty()) {

            quantidadeParcela = Integer.parseInt(this.editTextParcelas.getText().toString());
        }
        Requisicao requisicao = new Requisicao();
        requisicao.requestIncluirConta(this.editTextDescricaoDaConta.getText().toString(),
                Double.parseDouble(this.editTextValorDaconta.getText().toString()),
                this.prioridadeSelecionada, quantidadeParcela);
    }

    public void mostrarSucesso(){

        AlertDialog.Builder popup = new AlertDialog.Builder(ContasAPagarActivity.this);
        popup.setTitle(R.string.activityContasAPagar_MsgSucesso);
        popup.setMessage(R.string.activityContasAPagar_TextViewRE37);
        popup.setPositiveButton(R.string.activityContasAPagar_ok, null);
        popup.create();
        popup.show();
        this.editTextDescricaoDaConta.setText("");
        this.editTextValorDaconta.setText("");
        this.editTextParcelas.setText("");


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

    public void onRadioButtonClicked1 (View v) {

        boolean checked = ((RadioButton) v).isChecked();

        switch(((RadioButton) v).getId()) {
            case R.id.button_Fixa:
                if (checked) {

                    this.prestacaoSelecionada = "Fixa";
                    this.aPrazo = false;
                }
                    this.editTextParcelas.setVisibility(View.GONE);

                break;
            case R.id.button_APrazo:
                if (checked) {

                    this.aPrazo = true;
                }
                    this.editTextParcelas.setVisibility(View.VISIBLE);

                break;
        }
    }

    public boolean validarCampos(){

        boolean validar = true;

        if (editTextDescricaoDaConta.getText().toString().equals("")){

            this.campoDescricaoVazio = true;
            validar = false;

        }

        if (this.aPrazo && editTextParcelas.getText().toString().isEmpty()){
            this.campoQuantidadeVazio = true;
            validar = false;
        } else if(this.aPrazo){

            String strValor = editTextParcelas.getText().toString();
            double valor1 = Double.parseDouble(strValor);

            if (valor1 ==0){
                this.valorZeroP = true;
                validar = false;
            }
        }

        if(editTextValorDaconta.getText().toString().equals("")){

            this.campoValorVazio = true;
            validar = false;
        } else {

            double valor = Double.parseDouble(editTextValorDaconta.getText().toString());

            if (valor ==0){

                this.valorZero = true;
                validar = false;
            }
        }

        return validar;
    }

    public void checarValidacoes() {

        if (this.campoDescricaoVazio){

            textViewRE38.setVisibility(View.VISIBLE);
            textViewRE38.setText(R.string.activityContasAPagar_TextViewRE38);
        }

        if (this.campoQuantidadeVazio ){

            textViewRE40.setVisibility(View.VISIBLE);
            textViewRE40.setText(R.string.activityContasAPagar_TextViewRE40);

        }else if(this.valorZeroP){
            textViewRE40.setVisibility(View.VISIBLE);
            textViewRE40.setText(R.string.activityContasAPagar_TextViewRE41);
        }



        if (this.campoValorVazio){

            textViewRE39RE41.setVisibility(View.VISIBLE);
            textViewRE39RE41.setText(R.string.activityContasAPagar_TextViewRE39);
        }else if (this.valorZero){

            textViewRE39RE41.setVisibility(View.VISIBLE);
            textViewRE39RE41.setText(R.string.activityContasAPagar_TextViewRE41);

        }

        this.campoValorVazio = false;
        this.campoDescricaoVazio = false;
        this.campoQuantidadeVazio = false;
        this.valorZero = false;
        this.valorZeroP = false;
    }

    public void buttonQuitar(View v2) {

        this.v = v2;
        AlertDialog.Builder popup = new AlertDialog.Builder(ContasAPagarActivity.this);
        popup.setTitle(R.string.activityContasAPagar_Atencao);
        popup.setMessage(R.string.activityContasAPagar_RE42);
        popup.setPositiveButton(R.string.activityContasAPagar_ButtonSim, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                LongOperation03 longOperation03 = new LongOperation03();
                longOperation03.execute();
            }
        });
        popup.setNegativeButton(R.string.activityContasAPagar_ButtonNao, null);
        popup.create();
        popup.show();
    }

    public void quitar() {

        Requisicao requisicao = new Requisicao();
        requisicao.requestQuitarDivida(this.adaptador06.getItem(this.v.getId()).getId());
    }

    public void mostrarSucessoQuitar() {

        AlertDialog.Builder popup = new AlertDialog.Builder(ContasAPagarActivity.this);
        popup.setTitle(R.string.activityContasAPagar_MsgSucesso);
        popup.setMessage(R.string.activityContasAPagar_RE43);
        popup.setPositiveButton(R.string.activityContasAPagar_ok, null);
        popup.create();
        popup.show();
    }

    public void buttonPagar(View v2) {

        this.v = v2;
        AlertDialog.Builder popup = new AlertDialog.Builder(ContasAPagarActivity.this);
        popup.setTitle(R.string.activityContasAPagar_Atencao);
        popup.setMessage(R.string.activityContasAPagar_RE44);
        popup.setPositiveButton(R.string.activityContasAPagar_ButtonSim, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                LongOperation04 longOperation04 = new LongOperation04();
                longOperation04.execute();

            }
        });
        popup.setNegativeButton(R.string.activityContasAPagar_ButtonNao, null);
        popup.create();
        popup.show();
    }

    public void pagar() {

        Requisicao requisicao = new Requisicao();
        requisicao.requestPagarDivida(adaptador06.getItem(v.getId()).getId());
    }

    public void mostrarSucessoPagar() {

        AlertDialog.Builder popup = new AlertDialog.Builder(ContasAPagarActivity.this);
        popup.setTitle(R.string.activityContasAPagar_MsgSucesso);
        popup.setMessage(R.string.activityContasAPagar_RE45);
        popup.setPositiveButton(R.string.activityContasAPagar_ok, null);
        popup.create();
        popup.show();
    }


    public void buttonExcluir(View v2) {

        this.v = v2;
        AlertDialog.Builder popup = new AlertDialog.Builder(ContasAPagarActivity.this);
        popup.setTitle(R.string.activityContasAPagar_Atencao);
        popup.setMessage(R.string.activityContasAPagar_RE46);
        popup.setPositiveButton(R.string.activityContasAPagar_ButtonSim, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                LongOperation02 longOperation02 = new LongOperation02();
                longOperation02.execute();

            }
        });
        popup.setNegativeButton(R.string.activityContasAPagar_ButtonNao, null);
        popup.create();
        popup.show();
    }
    public void excluir() {

        Requisicao requisicao = new Requisicao();
        requisicao.requestExcluirDivida(this.adaptador06.getItem(this.v.getId()).getId());
    }

    public void mostrarSucessoExclusao() {

        AlertDialog.Builder popup = new AlertDialog.Builder(ContasAPagarActivity.this);
        popup.setTitle(R.string.activityContasAPagar_MsgSucesso);
        popup.setMessage(R.string.activityContasAPagar_RE47);
        popup.setPositiveButton(R.string.activityContasAPagar_ok, null);
        popup.create();
        popup.show();
    }


    public class LongOperation extends AsyncTask<Void, Void, Boolean> {


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

            interfaceIncluirContasAPagar();
            carregarLista();
            return true;
        }

        @Override
        protected void onPreExecute() {

            pbHeaderProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

            if(aBoolean) {

                pbHeaderProgress.setVisibility(View.GONE);
                setLista();
                mostrarSucesso();
            }

            pbHeaderProgress.setVisibility(View.GONE);
            checarValidacoes();
        }
    }

    private class LongOperation02 extends AsyncTask<Void, Void, Boolean> {


        @Override
        protected Boolean doInBackground(Void... voids) {

            excluir();
            carregarLista();
            return true;
        }

        @Override
        protected void onPreExecute() {

            pbHeaderProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

            pbHeaderProgress.setVisibility(View.GONE);
            setLista();
            mostrarSucessoExclusao();
        }
    }

    private class LongOperation03 extends AsyncTask<Void, Void, Boolean> {


        @Override
        protected Boolean doInBackground(Void... voids) {

            quitar();
            carregarLista();
            return true;
        }

        @Override
        protected void onPreExecute() {

            pbHeaderProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

            pbHeaderProgress.setVisibility(View.GONE);
            setLista();
            mostrarSucessoQuitar();
        }
    }

    private class LongOperation04 extends AsyncTask<Void, Void, Boolean> {


        @Override
        protected Boolean doInBackground(Void... voids) {

            pagar();
            carregarLista();
            return true;
        }

        @Override
        protected void onPreExecute() {

            pbHeaderProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

            pbHeaderProgress.setVisibility(View.GONE);
            setLista();
            mostrarSucessoPagar();
        }
    }




    }






