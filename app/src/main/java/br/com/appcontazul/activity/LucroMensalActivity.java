package br.com.appcontazul.activity;

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
import android.widget.TextView;

import java.util.List;

import br.com.appcontazul.R;
import br.com.appcontazul.rest.Requisicao;
import br.com.appcontazul.rest.model.ListaLucroMensal;
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
    private boolean campoDescricaoVazio;
    private boolean campoValorVazio;
    private boolean valorZero;
    private ProgressBar pbHeaderProgress;
    private boolean mostrandoLista;
    private Button buttonLucroMensal;
    private LinearLayout layoutListarLucroMensal;
    private LinearLayout layoutIncluirLucroMensal;
    private Button buttonIncluir;
    private TextView textViewTituloLucroMensal;
    private boolean listaVazia;

    public View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucro_mensal);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        this.criarElementos();
        this.carregarLista();
        this.setLista();
        this.inicializarComportamentoEditTextValorDoLucro();
        this.inicializarComportamentoEditTextDescricaoDoBeneficio();
    }

    public void criarElementos() {

        this.editTextDescricaoDoBeneficio = (EditText) findViewById(R.id.editText_DescricaoDoBeneficio);
        this.editTextValorDoLucro = (EditText) findViewById(R.id.editText_ValorDoLucro);
        this.textViewValorFormatado = (TextView) findViewById(R.id.textView_ValorFormatado);
        this.textViewRE30 = (TextView) findViewById(R.id.textView_RE30);
        this.textViewRE31 = (TextView) findViewById(R.id.textView_RE31);
        this.campoDescricaoVazio = false;
        this.campoValorVazio = false;
        this.valorZero = false;
        this.pbHeaderProgress = (ProgressBar) findViewById(R.id.pbHeaderProgress);
        this.mostrandoLista = true;
        this.buttonLucroMensal = (Button) findViewById(R.id.button_lucroMensal);
        this.layoutListarLucroMensal = (LinearLayout) findViewById(R.id.layout_listaLucroMensal);
        this.layoutIncluirLucroMensal = (LinearLayout) findViewById(R.id.layout_InserirLucroMensal);
        this.buttonIncluir = (Button) findViewById(R.id.button_Incluir);
        this.textViewTituloLucroMensal = (TextView) findViewById(R.id.textView_tituloLucroMensal);
        this.listaVazia = true;
    }

    public void carregarLista() {

        this.listaLucroMensal = (ListView) findViewById(R.id.listaLucroMensal);
        Requisicao requisicao = new Requisicao();
        List<ListaLucroMensal> lucroMensal = requisicao.requestListaLucroMensal();
        if(lucroMensal.size() != 0) {

            this.listaVazia = false;
            // this.textViewTituloLucroMensal.setText("Lista de lucro mensal:");
        } else {

            this.listaVazia = true;
        }
        this.adaptador05 = new Adaptador05(lucroMensal, this);
    }

    public void setLista() {

        if(!listaVazia) {

            this.textViewTituloLucroMensal.setText("Lista de lucro mensal:");
        } else {

            this.textViewTituloLucroMensal.setText("Não há lucro mensal cadastrado");
        }
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

    public void lucroMensalTrocarTela(View v) {

        if(mostrandoLista) {

            this.mostrandoLista = false;
            this.buttonLucroMensal.setText(getResources().getString(R.string.activityLucroMensal_ListarLucroMensal));
            this.layoutListarLucroMensal.setVisibility(View.GONE);
            this.layoutIncluirLucroMensal.setVisibility(View.VISIBLE);
            this.textViewTituloLucroMensal.setVisibility(View.GONE);
        } else {

            this.mostrandoLista = true;
            this.textViewRE30.setText("");
            this.textViewRE31.setText("");
            this.buttonLucroMensal.setText(getResources().getString(R.string.activityLucroMensal_IncluirLucroMensal));
            this.layoutIncluirLucroMensal.setVisibility(View.GONE);
            this.layoutListarLucroMensal.setVisibility(View.VISIBLE);
            this.textViewTituloLucroMensal.setVisibility(View.VISIBLE);
        }
    }

    public void buttonIncluirLucro(View v) {

        LongOperation longOperation = new LongOperation();
        longOperation.execute();
    }

    public void incluirLucroMensal() {

        Requisicao requisicao = new Requisicao();
        requisicao.requestInserirLucroMensal(this.editTextDescricaoDoBeneficio.getText().toString(),
                this.editTextValorDoLucro.getText().toString());
    }

    public void mostrarSucesso() {

        AlertDialog.Builder popup = new AlertDialog.Builder(LucroMensalActivity.this);
        popup.setTitle(R.string.activityLucroMensal_Sucesso);
        popup.setMessage(R.string.activityLucroMensal_REXX);
        popup.setPositiveButton(R.string.activityLucroMensal_OK, null);
        popup.create();
        popup.show();
        this.editTextDescricaoDoBeneficio.setText("");
        this.editTextValorDoLucro.setText("");
    }

    public boolean validarCampos() {

        boolean validar = true;

        if(this.editTextDescricaoDoBeneficio.getText().toString().equals("")) {

            this.campoDescricaoVazio = true;
            validar = false;
        }

        if(this.editTextValorDoLucro.getText().toString().equals("")) {

            this.campoValorVazio = true;
            validar = false;
        } else {

            double valor = Double.parseDouble(this.editTextValorDoLucro.getText().toString());
            if(valor <= 0) {

                this.valorZero = true;
                validar = false;
            }
        }

        return validar;
    }

    public void checarValidacoes() {

        if(this.campoDescricaoVazio) {

            this.textViewRE30.setText(getResources().getString(R.string.activityLucroMensal_textViewRE30));
        }

        if(this.campoValorVazio) {

            this.textViewRE31.setText(getResources().getString(R.string.activityLucroMensal_textViewRE31));

        } else if(this.valorZero) {

            this.textViewRE31.setText(getResources().getString(R.string.activitySomaSaldoMensagem));
        }

        this.campoDescricaoVazio = false;
        this.campoValorVazio = false;
        this.valorZero = false;
    }

    public void buttonReceber(View v2) {

        this.v = v2;
        AlertDialog.Builder popup = new AlertDialog.Builder(LucroMensalActivity.this);
        popup.setTitle(R.string.activityLucroMensal_Atencao);
        popup.setMessage(R.string.activityLucroMensal_RE35);
        popup.setPositiveButton(R.string.activityLucroMensal_ButtonSim, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                LongOperation03 longOperation03 = new LongOperation03();
                longOperation03.execute();
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

                LongOperation02 longOperation02 = new LongOperation02();
                longOperation02.execute();

            }
        });
        popup.setNegativeButton(R.string.activityLucroMensal_ButtonNao, null);
        popup.create();
        popup.show();
    }

    public void excluir() {

        Requisicao requisicao = new Requisicao();
        requisicao.requestExcluirLucroMensal("" + adaptador05.getItem(v.getId()).getId());
        carregarLista();
    }

    public void mostrarSucessoExclusao() {

        AlertDialog.Builder popup = new AlertDialog.Builder(LucroMensalActivity.this);
        popup.setTitle(R.string.activityLucroMensal_Sucesso);
        popup.setMessage(R.string.activityLucroMensal_RE34);
        popup.setPositiveButton(R.string.activityLucroMensal_OK, null);
        popup.create();
        popup.show();
    }

    public void receber() {

        Requisicao requisicao = new Requisicao();
        requisicao.requestReceber("" + adaptador05.getItem(v.getId()).getId());
        carregarLista();
    }

    public void mostrarSucessoInclusao() {

        AlertDialog.Builder popup = new AlertDialog.Builder(LucroMensalActivity.this);
        popup.setTitle(R.string.activityLucroMensal_Sucesso);
        popup.setMessage(R.string.activityLucroMensal_RE36);
        popup.setPositiveButton(R.string.activityLucroMensal_OK, null);
        popup.create();
        popup.show();
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

                Intent activityCentral = new Intent(LucroMensalActivity.this, CentralActivity.class);
                startActivity(activityCentral);
                return true;

            case R.id.action_perfilDaConta:

                Intent activityPerfilDaConta = new Intent(LucroMensalActivity.this, PerfilContaActivity.class);
                startActivity(activityPerfilDaConta);
                return true;

            case R.id.action_somaSaldo:

                Intent activitySomaDeSaldo = new Intent(LucroMensalActivity.this, SomaDeSaldoActivity.class);
                startActivity(activitySomaDeSaldo);
                return true;

            case R.id.action_subtracaoSaldo:

                Intent activitySubtracaoDeSaldo = new Intent(LucroMensalActivity.this, SubtracaoDeSaldoActivity.class);
                startActivity(activitySubtracaoDeSaldo);
                return true;

            case R.id.action_lucroMensal:

                Intent activityLucroMensal = new Intent(LucroMensalActivity.this, LucroMensalActivity.class);
                startActivity(activityLucroMensal);
                return true;

            case R.id.action_contasPagar:

                Intent activityContasAPagar = new Intent(LucroMensalActivity.this, ContasAPagarActivity.class);
                startActivity(activityContasAPagar);
                return true;

            case R.id.action_selecaoConta:

                Intent activitySelecaoConta = new Intent(LucroMensalActivity.this, SelecaoContaActivity.class);
                startActivity(activitySelecaoConta);
                return true;


            case R.id.action_meta:

                Intent activityMeta = new Intent(LucroMensalActivity.this, MetaActivity.class);
                startActivity(activityMeta);
                return true;

            case R.id.action_sair:

                Intent activityLogin = new Intent(LucroMensalActivity.this, LoginActivity.class);
                startActivity(activityLogin);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void desabilitarItens() {

        this.editTextDescricaoDoBeneficio.setEnabled(false);
        this.editTextValorDoLucro.setEnabled(false);
        this.buttonIncluir.setEnabled(false);
    }

    public void habilitarItens() {

        this.editTextDescricaoDoBeneficio.setEnabled(true);
        this.editTextValorDoLucro.setEnabled(true);
        this.buttonIncluir.setEnabled(true);
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

            incluirLucroMensal();
            carregarLista();
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
                setLista();
                habilitarItens();
                mostrarSucesso();
            }

            pbHeaderProgress.setVisibility(View.GONE);
            habilitarItens();
            checarValidacoes();
        }
    }

    private class LongOperation02 extends AsyncTask<Void, Void, Boolean> {


        @Override
        protected Boolean doInBackground(Void... voids) {

            excluir();
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

            receber();
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
            mostrarSucessoInclusao();
        }
    }
}