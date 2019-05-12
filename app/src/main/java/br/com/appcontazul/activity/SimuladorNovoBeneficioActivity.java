package br.com.appcontazul.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.appcontazul.R;
import br.com.appcontazul.contentstatic.Simulador;
import br.com.appcontazul.rest.model.SimuladorEntrada;
import br.com.appcontazul.util.Adaptador10;
import br.com.appcontazul.util.Formatacao;
import br.com.appcontazul.util.model.ListaSimuladorNovoBeneficio;

public class SimuladorNovoBeneficioActivity extends AppCompatActivity {

    // COMPONENTES
    private LinearLayout layout_adicionar;
    private EditText editText_descricao;
    private TextView textView_validacaoDescricao;
    private TextView textView_ValorFormatado;
    private EditText editText_valor;
    private TextView textView_validacaoValor;
    private Button button_listar;
    private Button button_finalizar;
    private ListView listaSimulacao;
    private LinearLayout layout_listar;
    // FIM COMPONENTES

    // ATRIBUTOS DE CONTROLE
    private List<ListaSimuladorNovoBeneficio> lista;
    // FIM ATRIBUTOS DE CONTROLE

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulador_novo_beneficio);

        this.criarElementos();
    }

    private void criarElementos() {

        this.layout_adicionar = (LinearLayout) findViewById(R.id.layout_adicionar);
        this.editText_descricao = (EditText) findViewById(R.id.editText_descricao);
        this.textView_validacaoDescricao = (TextView) findViewById(R.id.textView_validacaoDescricao);
        this.textView_ValorFormatado = (TextView) findViewById(R.id.textView_ValorFormatado);
        this.editText_valor = (EditText) findViewById(R.id.editText_valor);
        this.textView_validacaoValor = (TextView) findViewById(R.id.textView_validacaoValor);
        this.button_listar = (Button) findViewById(R.id.button_listar);
        this.button_finalizar = (Button) findViewById(R.id.button_finalizar);

        this.listaSimulacao = (ListView) findViewById(R.id.listaSimulacao);
        this.layout_listar = (LinearLayout) findViewById(R.id.layout_listar);

        this.inicializarEditTextDescricao();
        this.inicializarEditTextValor();
        this.inicializarCliqueLista();


        this.lista = new ArrayList<>();
        this.verificarOpcao();
    }

    private void inicializarEditTextDescricao() {

        this.editText_descricao.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                textView_validacaoDescricao.setVisibility(View.GONE);
            }
        });
    }

    private void inicializarEditTextValor() {

        this.editText_valor.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here

                // yourEditText...
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                textView_validacaoValor.setVisibility(View.GONE);
                String sequencia = s.toString();
                Formatacao formatacao = new Formatacao();
                if(!sequencia.isEmpty()) {

                    String formatado = formatacao.formatarValorMonetario(s.toString());
                    textView_ValorFormatado.setText(formatado);
                } else {

                    textView_ValorFormatado.setText("R$0,00");
                }
            }
        });
    }

    private void inicializarCliqueLista() {

        this.listaSimulacao.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View v, int pos, long id) {

                lista.remove(pos);
                atualizarLista();
                exibirExluidoComSucesso();
                if(lista.size() == 0) {

                    button_listar.setAlpha(0.2f);
                    button_listar.setEnabled(false);
                    button_finalizar.setAlpha(0.2f);
                    button_finalizar.setEnabled(false);
                    voltar(null);
                }
                return true;
            }
        });
    }

    public void verificarOpcao() {

        boolean[] opcoes = getIntent().getBooleanArrayExtra("opcoes");
        if(opcoes[2]) {

            if(opcoes[3] || opcoes[4])
                this.button_finalizar.setText(getResources().getString(R.string.simulador_proxima));
        } else {

            // PASSA PRO PŔOXIMO
            Intent activity = new Intent(SimuladorNovoBeneficioActivity.this, SimuladorRemoverBeneficioActivity.class);
            activity.putExtra("opcoes", opcoes);
            startActivity(activity);
        }
    }

    // ONCLICK
    public void adicionar(View v) {

        if(validarCampos()) {

            Formatacao fmt = new Formatacao();
            lista.add(new ListaSimuladorNovoBeneficio(this.editText_descricao.getText().toString(),
                    Double.valueOf(editText_valor.getText().toString())));
            this.atualizarLista();
            Simulador.simuladorEntrada.setTotalBeneficio(Simulador.simuladorEntrada.getTotalBeneficio() + Double.valueOf(editText_valor.getText().toString()));
            this.limparCampos();
            this.exibirAdicionadoComSucesso();
            this.button_listar.setAlpha(1);
            this.button_listar.setEnabled(true);
            this.button_finalizar.setAlpha(1);
            this.button_finalizar.setEnabled(true);
        }
    }

    public void listar(View v) {

        this.layout_adicionar.setVisibility(View.GONE);
        this.layout_listar.setVisibility(View.VISIBLE);
    }

    public void voltar(View v) {

        this.layout_adicionar.setVisibility(View.VISIBLE);
        this.layout_listar.setVisibility(View.GONE);
    }

    public void finalizar(View v) {

        Simulador.beneficiosNovos = lista;
        double total = 0;
        for(ListaSimuladorNovoBeneficio item : lista) {

            total += item.getValor();
        }
        Simulador.simuladorEntrada.setTotalBeneficio(total);
        if(!this.button_finalizar.getText().toString().equals(getResources().getString(R.string.simulador_proxima))) {


            Intent activityResultado = new Intent(SimuladorNovoBeneficioActivity.this, SimuladorResultadoActivity.class);
            activityResultado.putExtra("opcoes", getIntent().getBooleanArrayExtra("opcoes"));
            startActivity(activityResultado);
        } else {

            Intent activityResultado = new Intent(SimuladorNovoBeneficioActivity.this, SimuladorRemoverBeneficioActivity.class);
            activityResultado.putExtra("opcoes", getIntent().getBooleanArrayExtra("opcoes"));
            startActivity(activityResultado);
        }
    }
    // FIM ONCLICK

    // VALIDAÇÃO
    private boolean validarCampos() {

        boolean passou = true;

        if(this.editText_descricao.getText().toString().isEmpty()) {

            this.textView_validacaoDescricao.setVisibility(View.VISIBLE);
            this.textView_validacaoDescricao.setText(getResources().getString(R.string.simulador_descricaovazia));
            passou = false;
        }

        if(this.editText_valor.getText().toString().isEmpty()) {

            this.textView_validacaoValor.setVisibility(View.VISIBLE);
            this.textView_validacaoValor.setText(getResources().getString(R.string.simulador_valorvazio));
            passou = false;
        } else {

            int conversao = Integer.parseInt(this.editText_valor.getText().toString());
            if(conversao == 0) {

                this.textView_validacaoValor.setVisibility(View.VISIBLE);
                this.textView_validacaoValor.setText(getResources().getString(R.string.simulador_valorzero));
                passou = false;
            }
        }

        return passou;
    }
    // FIM VALIDAÇÃO

    // CONTROLE
    public void atualizarLista() {

        Adaptador10 adaptador = new Adaptador10(lista, this);
        this.listaSimulacao.setAdapter(adaptador);
    }

    public void limparCampos() {

        this.editText_descricao.setText("");
        this.editText_valor.setText("");
    }
    // FIM CONTROLE

    // MENSAGEM
    public void exibirAdicionadoComSucesso() {

        Toast.makeText(this, getResources().getString(R.string.simulador_adicionadosucesso), Toast.LENGTH_SHORT).show();
    }

    public void exibirExluidoComSucesso() {

        Toast.makeText(this, getResources().getString(R.string.simulador_excluidosucesso), Toast.LENGTH_SHORT).show();
    }
    // FIM MENSAGEM

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

                Intent activityCentral = new Intent(SimuladorNovoBeneficioActivity.this, CentralActivity.class);
                startActivity(activityCentral);
                return true;

            case R.id.action_perfilDaConta:

                Intent activityPerfilDaConta = new Intent(SimuladorNovoBeneficioActivity.this, PerfilContaActivity.class);
                startActivity(activityPerfilDaConta);
                return true;

            case R.id.action_somaSaldo:

                Intent activitySomaDeSaldo = new Intent(SimuladorNovoBeneficioActivity.this, SomaDeSaldoActivity.class);
                startActivity(activitySomaDeSaldo);
                return true;

            case R.id.action_subtracaoSaldo:

                Intent activitySubtracaoDeSaldo = new Intent(SimuladorNovoBeneficioActivity.this, SubtracaoDeSaldoActivity.class);
                startActivity(activitySubtracaoDeSaldo);
                return true;

            case R.id.action_lucroMensal:

                Intent activityLucroMensal = new Intent(SimuladorNovoBeneficioActivity.this, LucroMensalActivity.class);
                startActivity(activityLucroMensal);
                return true;

            case R.id.action_contasPagar:

                Intent activityContasAPagar = new Intent(SimuladorNovoBeneficioActivity.this, ContasAPagarActivity.class);
                startActivity(activityContasAPagar);
                return true;

            case R.id.action_simulador:

                Intent activitySimulador = new Intent(SimuladorNovoBeneficioActivity.this, SimuladorActivity.class);
                startActivity(activitySimulador);
                return true;

            case R.id.action_meta:

                Intent activityMeta = new Intent(SimuladorNovoBeneficioActivity.this, MetaActivity.class);
                startActivity(activityMeta);
                return true;


            case R.id.action_selecaoConta:

                Intent activitySelecaoConta = new Intent(SimuladorNovoBeneficioActivity.this, SelecaoContaActivity.class);
                startActivity(activitySelecaoConta);
                return true;

            case R.id.action_sair:

                Intent activityLogin = new Intent(SimuladorNovoBeneficioActivity.this, LoginActivity.class);
                startActivity(activityLogin);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
    // FIM MENU
}










