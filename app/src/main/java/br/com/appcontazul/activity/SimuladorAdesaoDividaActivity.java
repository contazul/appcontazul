package br.com.appcontazul.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.appcontazul.R;
import br.com.appcontazul.contentstatic.Simulador;
import br.com.appcontazul.rest.model.InclusaoDivida;
import br.com.appcontazul.rest.model.SimuladorEntrada;
import br.com.appcontazul.util.Adaptador08;
import br.com.appcontazul.util.Formatacao;
import br.com.appcontazul.util.model.ListaSimuladorAdesaoDivida;

public class SimuladorAdesaoDividaActivity extends AppCompatActivity {

    // COMPONENTES
    private EditText editText_descricao;
    private TextView textView_descricaoVazia;
    private TextView textView_valorFormatado;
    private EditText editText_valor;
    private TextView textview_validacaoValor;
    private Switch switch_prioridade;
    private Switch switch_tipoDivida;
    private EditText editText_quantidadeParcela;
    private TextView textview_validacaoParcela;
    private ListView listaSimulacao;
    private Button button_listar;
    private Button button_finalizar;
    private LinearLayout layout_simuladorAdesaoDivida;
    private LinearLayout layout_listar_itens;
    // FIM COMPONENTES

    // ATRIBUTOS DE CONTROLE
    private List<ListaSimuladorAdesaoDivida> lista;
    // FIM ATRIBUTOS DE CONTROLE

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulador_adesao_divida);

        this.criarElementos();
    }

    private void criarElementos() {

        // INICIALIZANDO COMPONENTES
        this.editText_descricao = (EditText) findViewById(R.id.editText_descricao);
        this.inicializarEditTextDescricao();
        this.textView_descricaoVazia = (TextView) findViewById(R.id.textview_descricaoVazia);
        this.textView_valorFormatado = (TextView) findViewById(R.id.textView_valorFormatado);
        this.editText_valor = (EditText) findViewById(R.id.editText_valor);
        this.inicializarEditTextValor();
        this.textview_validacaoValor = (TextView) findViewById(R.id.textview_validacaoValor);
        this.switch_prioridade = (Switch) findViewById(R.id.switch_prioridade);
        this.switch_tipoDivida = (Switch) findViewById(R.id.switch_tipoDivida);
        this.editText_quantidadeParcela = (EditText) findViewById(R.id.editText_quantidadeParcela);
        this.inicializarSwitchTipoDivida();
        this.textview_validacaoParcela = (TextView) findViewById(R.id.textview_validacaoParcela);
        this.inicializarEditTextQuantidadeParcela();
        this.listaSimulacao = (ListView) findViewById(R.id.listaSimulacao);
        this.inicializarCliqueLista();
        this.button_listar = (Button) findViewById(R.id.button_listar);
        this.button_finalizar = (Button) findViewById(R.id.button_finalizar);
        this.layout_simuladorAdesaoDivida = (LinearLayout) findViewById(R.id.layout_simuladorAdesaoDivida);
        this.layout_listar_itens = (LinearLayout) findViewById(R.id.layout_listar_itens);
        // FIM INICIALIZANDO COMPONENTES

        // INICIALIZANDO ATRIBUTOS DE CONTROLE
        this.lista = new ArrayList<>();
        // FIM INICIALIZANDO ATRIBUTOS DE CONTROLE

        // INICIALIZANDO CONTROLE ESTÁTICO
        Simulador.simuladorEntrada = new SimuladorEntrada();
        // FIM INICIALIZANDO CONTROLE ESTÁTICO

        // ACIONANDO MÉTODOS DE CONTROLE
        this.verificarOpcao();
        // FIM ACIONANDO MÉTODOS DE CONTROLE
    }

    public void inicializarEditTextDescricao() {

        this.editText_descricao.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here

                // yourEditText...
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                textView_descricaoVazia.setVisibility(View.GONE);
            }
        });
    }

    public void inicializarEditTextValor() {

        this.editText_valor.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here

                // yourEditText...
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                textview_validacaoValor.setVisibility(View.GONE);
                String sequencia = s.toString();
                Formatacao formatacao = new Formatacao();
                if(!sequencia.isEmpty()) {

                    textView_valorFormatado.setText(formatacao.formatarValorMonetario(s.toString()));
                } else {

                    textView_valorFormatado.setText(getResources().getString(R.string.simulador_valorformatado));
                }
            }
        });
    }

    public void inicializarSwitchTipoDivida() {

        this.switch_tipoDivida.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                    editText_quantidadeParcela.setVisibility(View.VISIBLE);
                else
                    editText_quantidadeParcela.setVisibility(View.GONE);
            }
        });
    }

    public void inicializarEditTextQuantidadeParcela() {

        this.editText_quantidadeParcela.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here

                // yourEditText...
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                textview_validacaoParcela.setVisibility(View.GONE);
            }
        });
    }

    public void inicializarCliqueLista() {

        this.listaSimulacao.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View v, int pos, long id) {

                lista.remove(pos);
                atualizarLista();
                mostrarToastItemRemovido();
                return true;
            }
        });
    }

    // ONCLICK
    public void adicionar(View v) {

        if(this.validarCampos()) {

            String descricao = this.editText_descricao.getText().toString();
            double valor = Double.parseDouble(this.editText_valor.getText().toString());
            String prioridade = this.switch_prioridade.isChecked() ? "Baixa" : "Alta";
            boolean isAprazo = this.switch_tipoDivida.isChecked();
            int quantidadeParcela = isAprazo ?
                    Integer.parseInt(this.editText_quantidadeParcela.getText().toString()) : 0;
            lista.add(new ListaSimuladorAdesaoDivida(descricao, valor, prioridade, isAprazo,
                    quantidadeParcela));
            Adaptador08 adaptador = new Adaptador08(lista, this);
            listaSimulacao.setAdapter(adaptador);
            this.habilitarDesabilitarBotoes(true);
            this.limparCampos();
            this.mostrarToastItemAdicionado();
        }
    }

    public void listar(View v) {

        this.layout_simuladorAdesaoDivida.setVisibility(View.GONE);
        this.layout_listar_itens.setVisibility(View.VISIBLE);
    }

    public void voltar(View v) {

        this.layout_simuladorAdesaoDivida.setVisibility(View.VISIBLE);
        this.layout_listar_itens.setVisibility(View.GONE);
    }

    public void finalizar(View v) {

        double totalDivida = 0;
        for(ListaSimuladorAdesaoDivida item : this.lista) {

            totalDivida += item.getValor();
        }
        Simulador.simuladorEntrada.setTotalDivida(totalDivida);

        Simulador.dividasSimuladas = new ArrayList<>();
        for(ListaSimuladorAdesaoDivida item : this.lista) {

            Simulador.dividasSimuladas.add(new InclusaoDivida(item.getDescricao(), item.getValor(),
                    item.getPrioridade(), item.getQuantidadeParcela()));
        }

        if(!this.button_finalizar.getText().toString().equals(getResources().getString(R.string.simulador_proxima))) {


            Intent activityResultado = new Intent(SimuladorAdesaoDividaActivity.this, SimuladorResultadoActivity.class);
            activityResultado.putExtra("opcoes", getIntent().getBooleanArrayExtra("opcoes"));
            startActivity(activityResultado);
        }
    }
    // FIM ONCLICK

    // MÉTODOS DE CONTROLE
    public void habilitarDesabilitarBotoes(boolean habilitar) {

        if(habilitar) {

            this.button_listar.setAlpha(1);
            this.button_finalizar.setAlpha(1);
        } else {

            this.button_listar.setAlpha(0.2f);
            this.button_finalizar.setAlpha(0.2f);
        }
        this.button_listar.setEnabled(habilitar);
        this.button_finalizar.setEnabled(habilitar);
    }

    public void limparCampos() {

        this.editText_descricao.setText("");
        this.switch_prioridade.setChecked(false);
        this.switch_tipoDivida.setChecked(false);
        this.editText_valor.setText("");
        this.editText_quantidadeParcela.setText("");
    }

    public void atualizarLista() {

        Adaptador08 adaptador = new Adaptador08(lista, this);
        this.listaSimulacao.setAdapter(adaptador);
        if(lista.size() == 0) {

            this.habilitarDesabilitarBotoes(false);
            this.voltar(null);
        }
    }

    public void verificarOpcao() {

        boolean[] opcoes = getIntent().getBooleanArrayExtra("opcoes");
        Simulador.simuladorEntrada.setSimulandoMeta(opcoes[4]);
        if(opcoes[0]) {

            if(opcoes[1] || opcoes[2] || opcoes[3] || opcoes[4])
                this.button_finalizar.setText(getResources().getString(R.string.simulador_proxima));
        } else {

            // PASSA PRO PŔOXIMO
        }
    }
    // FIM MÉTODOS DE CONTROLE

    // VALIDAÇÃO
    public boolean validarCampos() {

        boolean passouValidacao = true;

        if(this.editText_descricao.getText().toString().isEmpty()) {

            passouValidacao = false;
            this.textView_descricaoVazia.setVisibility(View.VISIBLE);
        }

        if(this.editText_valor.getText().toString().isEmpty()) {

            this.textview_validacaoValor.setVisibility(View.VISIBLE);
            this.textview_validacaoValor.setText(getResources().getString(R.string.simulador_valorvazio));
            passouValidacao = false;
        } else {

            int conversao = Integer.parseInt(this.editText_valor.getText().toString());
            if(conversao == 0) {

                this.textview_validacaoValor.setVisibility(View.VISIBLE);
                this.textview_validacaoValor.setText(getResources().getString(R.string.simulador_valorzero));
                passouValidacao = false;
            }
        }

        if(this.switch_tipoDivida.isChecked()) {

            if(this.editText_quantidadeParcela.getText().toString().isEmpty()) {

                this.textview_validacaoParcela.setVisibility(View.VISIBLE);
                this.textview_validacaoParcela.setText(getResources().getString(
                        R.string.simulador_textviewparcelavazia));
                passouValidacao = false;
            } else {

                int conversao = Integer.parseInt(this.editText_quantidadeParcela.getText().toString());
                if(conversao == 0) {

                    this.textview_validacaoParcela.setVisibility(View.VISIBLE);
                    this.textview_validacaoParcela.setText(getResources().getString(
                            R.string.simulador_textviewparcelazero));
                    passouValidacao = false;
                }
            }
        }

        return passouValidacao;
    }
    // FIM VALIDAÇÃO

    // MENSAGENS
    public void mostrarToastItemAdicionado() {

        Toast.makeText(this, getResources().getString(R.string.simulador_adicionadosucesso), Toast.LENGTH_SHORT).show();
    }

    public void mostrarToastItemRemovido() {

        Toast.makeText(this, getResources().getString(R.string.simulador_excluidosucesso), Toast.LENGTH_SHORT).show();
    }
    // FIM MENSAGENS
}
