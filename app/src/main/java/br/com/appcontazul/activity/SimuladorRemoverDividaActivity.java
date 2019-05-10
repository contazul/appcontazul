package br.com.appcontazul.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.appcontazul.R;
import br.com.appcontazul.contentstatic.Simulador;
import br.com.appcontazul.rest.Requisicao;
import br.com.appcontazul.rest.model.ListaDividaFixa;
import br.com.appcontazul.util.Adaptador;
import br.com.appcontazul.util.Adaptador09;

public class SimuladorRemoverDividaActivity extends AppCompatActivity {

    // COMPONENTES
    private ProgressBar load;
    private LinearLayout layout_simulacaoExclusaoDivida;
    private Button button_acao;
    private ListView listaSimulacao;
    // FIM COMPONENTES

    // ATRIBUTOS DE CONTROLE
    private List<ListaDividaFixa> lista;
    private String dividas;
    private int contadorDividas;
    private double totalDivida;
    // FIM ATRIBUTOS DE CONTROLE

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulador_remover_divida);

        this.criarElementos();
    }

    private void criarElementos() {

        // INICIALIZANDO COMPONENTES
        this.load = (ProgressBar) findViewById(R.id.pbHeaderProgress);
        this.layout_simulacaoExclusaoDivida = (LinearLayout) findViewById(R.id.layout_simulacaoExclusaoDivida);
        this.button_acao = (Button) findViewById(R.id.button_acao);
        this.button_acao.setEnabled(false);
        this.button_acao.setAlpha(0.2f);
        this.listaSimulacao = (ListView) findViewById(R.id.listaSimulacao);
        this.inicilizarCliqueLista();
        // FIM INICIALIZANDO COMPONENTES

        // INICIALIZANDO ATRIBUTOS DE CONTROLE
        this.lista = new ArrayList<>();
        this.dividas = "";
        this.contadorDividas = 0;
        this.totalDivida = 0.0;
        // FIM INICIALIZANDO ATRIBUTOS DE CONTROLE

        this.verificarOpcao();
        this.carregarLista();
    }

    public void inicilizarCliqueLista() {

        this.listaSimulacao.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View v, int pos, long id) {

                totalDivida += lista.get(pos).getValor();
                dividas += lista.get(pos).getId() + ";";
                contadorDividas++;
                lista.remove(pos);
                montarLista();
                mostrarToastItemRemovido();
                button_acao.setEnabled(true);
                button_acao.setAlpha(1);
                if(lista.size() == 0)
                    finalizar(null);
                return true;
            }
        });
    }

    public void montarLista() {

        Adaptador09 adaptador = new Adaptador09(lista, this);
        listaSimulacao.setAdapter(adaptador);
    }

    // MÉTODOS DE CONTROLE
    public void verificarOpcao() {

        boolean[] opcoes = getIntent().getBooleanArrayExtra("opcoes");
        if(opcoes[1]) {

            if(opcoes[2] || opcoes[3] || opcoes[4])
                this.button_acao.setText(getResources().getString(R.string.simulador_proxima));
        } else {

            // PASSA PRO PŔOXIMO
            Intent activity = new Intent(SimuladorRemoverDividaActivity.this, SimuladorNovoBeneficioActivity.class);
            activity.putExtra("opcoes", opcoes);
            startActivity(activity);
        }
    }

    public void carregarLista() {

        LongOperation operation = new LongOperation();
        operation.execute();
    }
    // FIM MÉTODOS DE CONTROLE

    // MÉTODOS DE REQUISIÇÃO
    public void listarDividaFixa() {

        Requisicao requisicao = new Requisicao();
        this.lista = requisicao.requestListaDividaFixa();
    }
    // FIM MÉTODOS DE REQUISIÇÃO

    // ONCLICK
    public void finalizar(View v) {

        Simulador.dividas = new long[contadorDividas];
        String[] strDividas = this.dividas.split(";");
        for(int i = 0; i < contadorDividas; i++) {

            Simulador.dividas[i] = Long.parseLong(strDividas[i]);
        }

        Simulador.simuladorEntrada.setTotalDividaRemovida(totalDivida);

        if(!this.button_acao.getText().toString().equals(getResources().getString(R.string.simulador_proxima))) {

            Intent activity = new Intent(SimuladorRemoverDividaActivity.this, SimuladorResultadoActivity.class);
            activity.putExtra("opcoes", getIntent().getBooleanArrayExtra("opcoes"));
            startActivity(activity);
        } else {

            Intent activity = new Intent(SimuladorRemoverDividaActivity.this, SimuladorNovoBeneficioActivity.class);
            activity.putExtra("opcoes", getIntent().getBooleanArrayExtra("opcoes"));
            startActivity(activity);
        }
    }
    // FIM ONCLICK

    // MENSAGENS
    public void mostrarToastItemRemovido() {

        Toast.makeText(this, getResources().getString(R.string.simulador_excluidosucesso), Toast.LENGTH_SHORT).show();
    }
    // FIM MENSAGENS

    // MÉTODOS ASCINCRONOS
    private class LongOperation extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            listarDividaFixa();
            return true;
        }

        @Override
        protected void onPreExecute() {

            layout_simulacaoExclusaoDivida.setVisibility(View.GONE);
            load.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

            layout_simulacaoExclusaoDivida.setVisibility(View.VISIBLE);
            load.setVisibility(View.GONE);
            montarLista();
        }
    }
    // FIM MÉTODOS ASCINCRONOS
}
















