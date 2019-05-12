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
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

import br.com.appcontazul.R;
import br.com.appcontazul.rest.Requisicao;
import br.com.appcontazul.rest.model.ListaMeta;
import br.com.appcontazul.rest.teste.ListaMetaRepository;
import br.com.appcontazul.util.Adaptador07;
import br.com.appcontazul.util.Formatacao;

public class MetaActivity extends AppCompatActivity {

    private TextView textViewValorFormatado;
    private TextView textViewRE51;
    private TextView textViewRE50;
    private TextView textViewRE52;
    private TextView textViewRE53;
    private EditText editTextDescricaoDaMeta;
    private EditText editTextValorDaMeta;
    private EditText editTextValorMinimoEco;
    private TextView textViewValorFormatado2;
    private EditText editTextParcelas;
    private RadioButton buttonAVista;
    private ListView listaMeta;
    private ProgressBar pbHeaderProgress;
    private Adaptador07 adaptador07;
    private boolean mostrandoLista;
    private LinearLayout layoutListaMeta;
    private LinearLayout layoutInserirMeta;
    private Button buttonMeta;
    private boolean aPrazo;
    private boolean aVista;
    private TextView textViewtextViewTituloMeta;
    private boolean listaVazia;
    private boolean campoDescricaoVazio;
    private boolean campoValorVazio;
    private boolean campoValorMinVazio;
    private boolean campoQuantidadeVazio;
    private View v;

    private boolean aplicarAVista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meta);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        this.criarElementos();
        this.carregarLista();
        this.setLista();
        this.inicializarComportamentoEditTextDescricaoDaMeta();
        this.inicializarComportamentoEditTextQuantidadeDeParcelas();
        this.inicializarComportamentoEditTextValorDaMeta();
        this.inicializarComportamentoEditTextValorMinimo();
    }

    public void criarElementos() {

        this.editTextDescricaoDaMeta = (EditText) findViewById(R.id.editText_DescricaoDaMeta);
        this.editTextValorDaMeta = (EditText) findViewById(R.id.editText_ValorDaMeta);
        this.editTextValorMinimoEco = (EditText) findViewById(R.id.editText_ValorMinimoEco);
        this.editTextParcelas = (EditText) findViewById(R.id.editText_Parcelas);

        this.textViewValorFormatado = (TextView) findViewById(R.id.textView_ValorFormatado);
        this.textViewValorFormatado2 = (TextView) findViewById(R.id.textView_ValorFormatado2);
        this.textViewRE51 = (TextView) findViewById(R.id.textView_RE51);
        this.textViewRE50 = (TextView) findViewById(R.id.textView_RE50);
        this.textViewRE52 = (TextView) findViewById(R.id.textView_RE52);
        this.textViewRE53 = (TextView) findViewById(R.id.textView_RE53);

        this.campoDescricaoVazio = false;
        this.campoValorVazio = false;
        this.campoValorMinVazio = false;
        this.campoQuantidadeVazio = false;
        this.pbHeaderProgress = (ProgressBar) findViewById(R.id.pbHeaderProgress);
        this.mostrandoLista = true;
        this.layoutInserirMeta = (LinearLayout) findViewById(R.id.layout_InserirMeta);
        this.layoutListaMeta = (LinearLayout) findViewById(R.id.layout_listaMeta);
        this.buttonMeta = (Button) findViewById(R.id.button_meta);
        this.buttonAVista = (RadioButton) findViewById(R.id.button_AVista);
        buttonAVista.setChecked(true);
        buttonAVista.setSelected(true);
        this.aPrazo = false;
        this.aVista = true;
        this.textViewtextViewTituloMeta = (TextView) findViewById(R.id.textView_textViewTituloMeta);
        this.listaVazia = false;
        this.listaMeta = (ListView) findViewById(R.id.listaMeta);

        this.aplicarAVista = false;
    }

    public void carregarLista() {

        Requisicao requisicao = new Requisicao();
        List<ListaMeta> listaMeta = requisicao.requestListaMeta();
        this.listaVazia = listaMeta.size() == 0;
        this.adaptador07 = new Adaptador07(listaMeta, this);
    }

    public void setLista() {

        if(listaVazia)
            this.textViewtextViewTituloMeta.setText("Não há meta cadastrada");
        else
            this.textViewtextViewTituloMeta.setText("Lista de meta:");

        this.listaMeta.setAdapter(adaptador07);
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

                Intent activityCentral = new Intent(MetaActivity.this, CentralActivity.class);
                startActivity(activityCentral);
                return true;

            case R.id.action_perfilDaConta:

                Intent activityPerfilDaConta = new Intent(MetaActivity.this, PerfilContaActivity.class);
                startActivity(activityPerfilDaConta);
                return true;

            case R.id.action_somaSaldo:

                Intent activitySomaDeSaldo = new Intent(MetaActivity.this, SomaDeSaldoActivity.class);
                startActivity(activitySomaDeSaldo);
                return true;

            case R.id.action_subtracaoSaldo:

                Intent activitySubtracaoDeSaldo = new Intent(MetaActivity.this, SubtracaoDeSaldoActivity.class);
                startActivity(activitySubtracaoDeSaldo);
                return true;

            case R.id.action_lucroMensal:

                Intent activityLucroMensal = new Intent(MetaActivity.this, LucroMensalActivity.class);
                startActivity(activityLucroMensal);
                return true;

            case R.id.action_contasPagar:

                Intent activityContasAPagar = new Intent(MetaActivity.this, ContasAPagarActivity.class);
                startActivity(activityContasAPagar);
                return true;

            case R.id.action_simulador:

                Intent activitySimulador = new Intent(MetaActivity.this, SimuladorActivity.class);
                startActivity(activitySimulador);
                return true;

            case R.id.action_meta:

                Intent activityMeta = new Intent(MetaActivity.this, MetaActivity.class);
                startActivity(activityMeta);
                return true;

            case R.id.action_selecaoConta:

                Intent activitySelecaoConta = new Intent(MetaActivity.this, SelecaoContaActivity.class);
                startActivity(activitySelecaoConta);
                return true;

            case R.id.action_sair:

                Intent activityLogin = new Intent(MetaActivity.this, LoginActivity.class);
                startActivity(activityLogin);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void inicializarComportamentoEditTextValorDaMeta() {

        this.editTextValorDaMeta.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here

                // yourEditText...
            }


            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                textViewRE51.setText("");
                String sequencia = s.toString();
                Formatacao formatacao = new Formatacao();
                if (!sequencia.isEmpty()) {

                    textViewValorFormatado.setText(formatacao.formatarValorMonetario(s.toString()));
                } else {

                    textViewValorFormatado.setText(getResources().getString(R.string.activityMeta_TextViewValorFormatado));
                }
            }
        });
    }

    public void inicializarComportamentoEditTextValorMinimo() {

        this.editTextValorMinimoEco.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here

                // yourEditText...
            }


            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                textViewRE53.setText("");
                String sequencia = s.toString();
                Formatacao formatacao = new Formatacao();
                if (!sequencia.isEmpty()) {

                    textViewValorFormatado2.setText(formatacao.formatarValorMonetario(s.toString()));
                } else {

                    textViewValorFormatado2.setText(getResources().getString(R.string.activityMeta_TextViewValorFormatado));
                }
            }
        });
    }

    public void inicializarComportamentoEditTextDescricaoDaMeta() {

        this.editTextDescricaoDaMeta.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }


            public void onTextChanged(CharSequence s, int start, int before, int count) {

                textViewRE50.setText("");

            }


            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void inicializarComportamentoEditTextQuantidadeDeParcelas() {

        this.editTextParcelas.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }


            public void onTextChanged(CharSequence s, int start, int before, int count) {

                textViewRE52.setText("");

            }


            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void metaTrocarTela(View v) {

        if (mostrandoLista) {

            this.mostrandoLista = false;
            this.buttonMeta.setText("Listar");
            this.layoutListaMeta.setVisibility(View.GONE);
            this.layoutInserirMeta.setVisibility(View.VISIBLE);
            this.textViewtextViewTituloMeta.setVisibility(View.GONE);
        } else {

            this.mostrandoLista = true;
            this.textViewRE50.setText("");
            this.textViewRE51.setText("");
            this.textViewRE52.setText("");
            this.textViewRE53.setText("");
            this.buttonMeta.setText("Incluir");
            this.layoutInserirMeta.setVisibility(View.GONE);
            this.layoutListaMeta.setVisibility(View.VISIBLE);
            this.textViewtextViewTituloMeta.setVisibility(View.VISIBLE);
        }
    }

    public void buttonInserirMeta (View v){

        LongOperation longOperation = new LongOperation();
        longOperation.execute();
    }

    public void incluirMeta() {

        Requisicao requisicao = new Requisicao();
        requisicao.requestIncluirMeta(this.getDescricao(), this.getValor(), this.getAVista(),
        this.getValorEconomizar(), this.getQuantidadeParcela());
    }

    public String getDescricao() {

        return this.editTextDescricaoDaMeta.getText().toString();
    }

    public double getValor() {

        return Double.valueOf(this.editTextValorDaMeta.getText().toString());
    }

    public int getAVista() {

        if(aVista)
            return 1;

        return 0;
    }

    public double getValorEconomizar() {

        return Double.valueOf(editTextValorMinimoEco.getText().toString());
    }

    public int getQuantidadeParcela() {

        if(editTextParcelas.getText().toString().isEmpty())
            return 0;

        return Integer.valueOf(this.editTextParcelas.getText().toString());
    }

    public void mostrarSucesso(){

        AlertDialog.Builder popup = new AlertDialog.Builder(MetaActivity.this);
        popup.setTitle(R.string.activityContasAPagar_MsgSucesso);
        popup.setMessage(R.string.activityMeta_MsgMostrarSucesso);
        popup.setPositiveButton(R.string.activityMeta_MsgOk, null);
        popup.create();
        popup.show();
        this.editTextDescricaoDaMeta.setText("");
        this.editTextValorDaMeta.setText("");
        this.editTextParcelas.setText("");
        this.editTextValorMinimoEco.setText("");
    }

    public void onRadioButtonClicked2 (View v) {

        boolean checked = ((RadioButton) v).isChecked();

        switch(((RadioButton) v).getId()) {
            case R.id.button_AVista:
                if (checked){
                    this.aPrazo = false;
                    this.aVista = true;
                }
                this.editTextParcelas.setVisibility(View.GONE);

                break;
            case R.id.button_Fixa:
                if (checked) {
                    this.aPrazo = false;
                    this.aVista = false;
                }
                this.editTextParcelas.setVisibility(View.GONE);

                break;
            case R.id.button_APrazo:
                if (checked) {

                    this.aPrazo = true;
                    this.aVista = false;
                }
                this.editTextParcelas.setVisibility(View.VISIBLE);
                break;
        }
    }

    public boolean validarCampos(){

        boolean validar = true;

        if (editTextDescricaoDaMeta.getText().toString().equals("")){

            this.campoDescricaoVazio = true;
            validar = false;

        }

        if (this.aPrazo && editTextParcelas.getText().toString().isEmpty()){
            this.campoQuantidadeVazio = true;
            validar = false;
        }

        if(editTextValorDaMeta.getText().toString().equals("")){

            this.campoValorVazio = true;
            validar = false;
        }

        if (editTextValorMinimoEco.getText().toString().equals("")){

            this.campoValorMinVazio = true;
            validar = false;
        }

        return validar;
    }

    public void checarValidacoes() {

        if (this.campoDescricaoVazio){

            textViewRE50.setVisibility(View.VISIBLE);
            textViewRE50.setText(R.string.activityMeta_TextViewRE50);
        }

        if (this.campoQuantidadeVazio ){

            textViewRE52.setVisibility(View.VISIBLE);
            textViewRE52.setText(R.string.activityMeta_TextViewRE52);

        }

        if (this.campoValorVazio){

            textViewRE51.setVisibility(View.VISIBLE);
            textViewRE51.setText(R.string.activityMeta_TextViewRE51);
        }

        if (this.campoValorMinVazio){

            textViewRE53.setVisibility(View.VISIBLE);
            textViewRE53.setText(R.string.activityMeta_TextViewRE53);
        }

        this.campoValorVazio = false;
        this.campoDescricaoVazio = false;
        this.campoQuantidadeVazio = false;
        this.campoValorMinVazio = false;

    }

    public void buttonAplicar(View v2) {

        this.v = v2;
        this.aplicarAVista = this.adaptador07.getItem(v.getId()).getIsAvista() == 1;
        AlertDialog.Builder popup = new AlertDialog.Builder(MetaActivity.this);
        popup.setTitle(R.string.activityMeta_MsgAtencao);
        popup.setMessage(R.string.activityMeta_MsgConfirmacao);
        popup.setPositiveButton(R.string.activityMeta_MsgSim, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                LongOperation02 longOperation02 = new LongOperation02();
                longOperation02.execute();
            }
        });
        popup.setNegativeButton(R.string.activityMeta_MsgNao, null);
        popup.create();
        popup.show();
    }

    public void aplicar() {

        Requisicao requisicao = new Requisicao();
        requisicao.requestAplicarMeta(this.adaptador07.getItem(v.getId()).getId());
    }

    public void mostrarSucessoAplicar() {

        if(this.aplicarAVista) {

            AlertDialog.Builder popup = new AlertDialog.Builder(MetaActivity.this);
            popup.setTitle(R.string.activityContasAPagar_MsgSucesso);
            popup.setMessage(R.string.activityContasAPagar_RE43);
            popup.setPositiveButton(R.string.activityContasAPagar_ok, null);
            popup.create();
            popup.show();
        } else {

            AlertDialog.Builder popup = new AlertDialog.Builder(MetaActivity.this);
            popup.setTitle(R.string.activityContasAPagar_MsgSucesso);
            popup.setMessage("Meta realizada! você pode acessa-la em contas á pagar.");
            popup.setPositiveButton(R.string.activityContasAPagar_ok, null);
            popup.create();
            popup.show();
        }
    }

    public void buttonExcluir(View v2) {

        this.v = v2;
        AlertDialog.Builder popup = new AlertDialog.Builder(MetaActivity.this);
        popup.setTitle(R.string.activityMeta_MsgAtencao);
        popup.setMessage(R.string.activityMeta_MsgConfirmacaoexclusao);
        popup.setPositiveButton(R.string.activityMeta_MsgSim, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                LongOperation03 longOperation03 = new LongOperation03();
                longOperation03.execute();

            }
        });
        popup.setNegativeButton(R.string.activityMeta_MsgNao, null);
        popup.create();
        popup.show();
    }

    public void excluir() {

        Requisicao requisicao = new Requisicao();
        requisicao.requestExcluirMeta(this.adaptador07.getItem(this.v.getId()).getId());
    }

    public void mostrarSucessoExclusao() {

        AlertDialog.Builder popup = new AlertDialog.Builder(MetaActivity.this);
        popup.setTitle(R.string.activityMeta_MsgSucesso);
        popup.setMessage(R.string.activityMeta_MsgSucessoExclusao);
        popup.setPositiveButton(R.string.activityMeta_MsgOk, null);
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

            incluirMeta();
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

            aplicar();
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
            mostrarSucessoAplicar();
        }
    }

    private class LongOperation03 extends AsyncTask<Void, Void, Boolean> {


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
}



