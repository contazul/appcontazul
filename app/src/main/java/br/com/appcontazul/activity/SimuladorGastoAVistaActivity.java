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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.appcontazul.R;
import br.com.appcontazul.contentstatic.Simulador;
import br.com.appcontazul.rest.Requisicao;
import br.com.appcontazul.rest.model.ListaSubtracaoSaldo;
import br.com.appcontazul.util.Adaptador04;
import br.com.appcontazul.util.Formatacao;

public class SimuladorGastoAVistaActivity extends AppCompatActivity {

    // COMPONENTES
    private TextView saldoSimulado;
    private TextView valorFormatado;
    private EditText valor;
    private TextView mensagemDescricaoVazia;
    private EditText descricao;
    private TextView mensagemValidacaoValor;
    private RadioButton prioridadeAlta;
    private LinearLayout layoutListar;
    private LinearLayout layoutSimular;
    private ListView lista;
    private Button listar;
    private Button finalizar;
    // FIM COMPONENTES

    // ATRIBUTOS DE CONTROLE
    private double saldo;
    private String prioridadeSelecionada;
    private List<ListaSubtracaoSaldo> listaControle;
    // FIM ATRIBUTOS DE CONTROLE

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulador_gasto_avista);

        this.criarElementos();
    }

    public void criarElementos() {

        // CRIANDO COMPONENTES
        this.saldoSimulado = (TextView) findViewById(R.id.textView_saldoSimulado);
        this.requisicaoSaldo();
        this.valorFormatado = (TextView) findViewById(R.id.textView_valorFormatado);
        this.valor = (EditText) findViewById(R.id.editText_valor);
        this.inicializarEditTextValor();
        this.mensagemDescricaoVazia = (TextView) findViewById(R.id.textView_RE28);
        this.descricao = (EditText) findViewById(R.id.editText_descricao);
        this.inicializarEditTextDescricao();
        this.mensagemValidacaoValor = (TextView) findViewById(R.id.textView_RE29);
        this.prioridadeAlta = (RadioButton) findViewById(R.id.button_alta);
        this.prioridadeAlta.setChecked(true);
        this.prioridadeAlta.setSelected(true);
        this.layoutListar = (LinearLayout) findViewById(R.id.listar_itens_adicionado);
        this.layoutSimular = (LinearLayout) findViewById(R.id.simular_gasto_a_vista);
        this.lista = (ListView) findViewById(R.id.listaSimulacao);
        this.inicializarCliqueLista();
        this.listar = (Button) findViewById(R.id.button_listar);
        this.finalizar = (Button) findViewById(R.id.button_finalizar);
        // FIM CRIANDO COMPONENENTES

        // INICIALIZANDO ATRIBUTOS DE CONTROLE
        this.prioridadeSelecionada = "Alta";
        this.listaControle = new ArrayList<>();
        // FIM INICIALIZANDO ATRIBUTOS DE CONTROLE
    }

    public void inicializarEditTextValor() {

        this.valor.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here

                // yourEditText...
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                mensagemValidacaoValor.setVisibility(View.GONE);
                String sequencia = s.toString();
                Formatacao formatacao = new Formatacao();
                if(!sequencia.isEmpty()) {

                    String formatado = formatacao.formatarValorMonetario(s.toString());
                    double saldoSubtraido = saldo - Double.parseDouble(s.toString());
                    saldoSimulado.setText(formatacao.formatarValorMonetario("" + saldoSubtraido));
                    valorFormatado.setText(formatado);
                } else {

                    saldoSimulado.setText(formatacao.formatarValorMonetario("" + saldo));
                    valorFormatado.setText("R$0,00");
                }
            }
        });
    }

    public void inicializarEditTextDescricao() {

        this.descricao.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here

                // yourEditText...
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                mensagemDescricaoVazia.setVisibility(View.GONE);
            }
        });
    }

    public void onRadioButtonClicked (View v) {

        boolean checked = ((RadioButton) v).isChecked();

        switch(((RadioButton) v).getId()) {
            case R.id.button_alta:
                if (checked)

                    this.prioridadeSelecionada = "Alta";
                break;
            case R.id.button_baixa:
                if (checked)

                    this.prioridadeSelecionada = "Baixa";
                break;
        }
    }

    public void inicializarCliqueLista() {

        this.lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View v, int pos, long id) {

                somarSaldoSimulado(listaControle.get(pos).getValor());
                listaControle.remove(pos);
                atualizarLista();
                exibirExluidoComSucesso();
                return true;
            }
        });
    }

    // MÉTODOS DE REQUISIÇÃO
    public void requisicaoSaldo() {

        Requisicao requisicao = new Requisicao();
        Formatacao formatacao = new Formatacao();
        this.saldo = requisicao.requestSaldo().getSaldo();
        this.saldoSimulado.setText(formatacao.formatarValorMonetario("" + saldo));
    }
    // FIM MÉTODOS DE REQUISIÇÃO

    // MÉTODOS DE VALIDAÇÃO
    public boolean validarCampos() {

        boolean camposPrenchidos = true;

        if(this.descricao.getText().toString().isEmpty()) {

            this.mensagemDescricaoVazia.setVisibility(View.VISIBLE);
            this.mensagemDescricaoVazia.setText(getResources().getString(R.string.simulador_descricaovazia));
            camposPrenchidos = false;
        }

        if(this.valor.getText().toString().isEmpty()) {

            this.mensagemValidacaoValor.setVisibility(View.VISIBLE);
            this.mensagemValidacaoValor.setText(getResources().getString(R.string.simulador_valorvazio));
            camposPrenchidos = false;
        } else {

            int conversao = Integer.parseInt(this.valor.getText().toString());
            if(conversao == 0) {

                this.mensagemValidacaoValor.setVisibility(View.VISIBLE);
                this.mensagemValidacaoValor.setText(getResources().getString(R.string.simulador_valorzero));
                camposPrenchidos = false;
            }
        }

        return camposPrenchidos;
    }
    // FIM MÉTODOS DE VALIDAÇÃO

    // MÉTODOS DE ONCLICK
    public void adicionar(View v) {

        if(validarCampos()) {

            this.listaControle.add(new ListaSubtracaoSaldo(descricao.getText().toString(),
                    Double.parseDouble(valor.getText().toString()), prioridadeSelecionada));
            Adaptador04 adaptador04 = new Adaptador04(listaControle, this);
            this.lista.setAdapter(adaptador04);
            this.atualizarSaldoSimulado();
            this.limparCampos();
            this.exibirAdicionadoComSucesso();
            this.listar.setAlpha(1);
            this.listar.setEnabled(true);
            this.finalizar.setAlpha(1);
            this.finalizar.setEnabled(true);
        }
    }

    public void listar(View v) {

        this.layoutListar.setVisibility(View.VISIBLE);
        this.layoutSimular.setVisibility(View.GONE);
    }

    public void voltar(View v) {

        this.layoutListar.setVisibility(View.GONE);
        this.layoutSimular.setVisibility(View.VISIBLE);
    }

    public void finalizar(View v) {

        Intent activitySimuladorGastoAVistaResultado = new Intent(SimuladorGastoAVistaActivity.this
                , SimuladorGastoAVistaResultadoActivity.class);

        Formatacao formatacao = new Formatacao();

        activitySimuladorGastoAVistaResultado.putExtra("saldoSimulado",
                formatacao.formatarValorMonetario("" + saldo));

        activitySimuladorGastoAVistaResultado.putExtra("totalBaixa",
                calcularTotalGastoBaixaPrioridade());

        activitySimuladorGastoAVistaResultado.putExtra("totalGeral", calcularTotalGeral());

        Simulador.listaSimulacao = listaControle;

        startActivity(activitySimuladorGastoAVistaResultado);
    }
    // FIM MÉTODOS DE ONCLICK

    // MÉTODOS DE EXIBIR MENSAGEM
    public void exibirAdicionadoComSucesso() {

        Toast.makeText(this, getResources().getString(R.string.simulador_adicionadosucesso), Toast.LENGTH_SHORT).show();
    }

    public void exibirExluidoComSucesso() {

        Toast.makeText(this, getResources().getString(R.string.simulador_excluidosucesso), Toast.LENGTH_SHORT).show();
    }
    // FIM MÉTODOS DE EXIBIR MENSAGEM

    // MÉTODOS DE CONTROLE
    public void atualizarSaldoSimulado() {

        this.saldo = this.saldo - Double.parseDouble(valor.getText().toString());
    }

    public void somarSaldoSimulado(double valor) {

        Formatacao formatacao = new Formatacao();
        this.saldo += valor;
        this.saldoSimulado.setText(formatacao.formatarValorMonetario("" + this.saldo));
    }

    public void limparCampos() {

        this.descricao.setText("");
        this.valor.setText("");
    }

    public void atualizarLista() {

        if(listaControle.size() == 0)
            this.desabilitarBotoesParaListaVazia();
        Adaptador04 adaptador04 = new Adaptador04(this.listaControle, this);
        this.lista.setAdapter(adaptador04);
    }

    public void desabilitarBotoesParaListaVazia() {

        this.listar.setAlpha(0.2f);
        this.listar.setEnabled(false);
        this.finalizar.setAlpha(0.2f);
        this.finalizar.setEnabled(false);
        this.voltar(null);
    }

    public String calcularTotalGastoBaixaPrioridade() {

        double resultado = 0;
        for(ListaSubtracaoSaldo subtracaoSaldo : listaControle) {

            if(subtracaoSaldo.getPrioridade().equals("Baixa"))
                resultado += subtracaoSaldo.getValor();
        }

        Formatacao formatacao = new Formatacao();
        return formatacao.formatarValorMonetario("" + resultado);
    }

    public String calcularTotalGeral() {

        double resultado = 0;
        for(ListaSubtracaoSaldo subtracaoSaldo : listaControle) {

            resultado += subtracaoSaldo.getValor();
        }

        Formatacao formatacao = new Formatacao();
        return formatacao.formatarValorMonetario("" + resultado);
    }
    // FIM MÉTODOS DE CONTROLE

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

                Intent activityCentral = new Intent(SimuladorGastoAVistaActivity.this, CentralActivity.class);
                startActivity(activityCentral);
                return true;

            case R.id.action_perfilDaConta:

                Intent activityPerfilDaConta = new Intent(SimuladorGastoAVistaActivity.this, PerfilContaActivity.class);
                startActivity(activityPerfilDaConta);
                return true;

            case R.id.action_somaSaldo:

                Intent activitySomaDeSaldo = new Intent(SimuladorGastoAVistaActivity.this, SomaDeSaldoActivity.class);
                startActivity(activitySomaDeSaldo);
                return true;

            case R.id.action_subtracaoSaldo:

                Intent activitySubtracaoDeSaldo = new Intent(SimuladorGastoAVistaActivity.this, SubtracaoDeSaldoActivity.class);
                startActivity(activitySubtracaoDeSaldo);
                return true;

            case R.id.action_lucroMensal:

                Intent activityLucroMensal = new Intent(SimuladorGastoAVistaActivity.this, LucroMensalActivity.class);
                startActivity(activityLucroMensal);
                return true;

            case R.id.action_contasPagar:

                Intent activityContasAPagar = new Intent(SimuladorGastoAVistaActivity.this, ContasAPagarActivity.class);
                startActivity(activityContasAPagar);
                return true;

            case R.id.action_simulador:

                Intent activitySimulador = new Intent(SimuladorGastoAVistaActivity.this, SimuladorActivity.class);
                startActivity(activitySimulador);
                return true;

            case R.id.action_meta:

                Intent activityMeta = new Intent(SimuladorGastoAVistaActivity.this, MetaActivity.class);
                startActivity(activityMeta);
                return true;


            case R.id.action_selecaoConta:

                Intent activitySelecaoConta = new Intent(SimuladorGastoAVistaActivity.this, SelecaoContaActivity.class);
                startActivity(activitySelecaoConta);
                return true;

            case R.id.action_sair:

                Intent activityLogin = new Intent(SimuladorGastoAVistaActivity.this, LoginActivity.class);
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














