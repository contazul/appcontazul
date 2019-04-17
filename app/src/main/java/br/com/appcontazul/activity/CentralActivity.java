package br.com.appcontazul.activity;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.com.appcontazul.R;
import br.com.appcontazul.contentstatic.ReferenciaUsuario;
import br.com.appcontazul.rest.Requisicao;
import br.com.appcontazul.rest.model.Central;
import br.com.appcontazul.util.Formatacao;

public class CentralActivity extends AppCompatActivity {

    private TextView textViewCentralStatus;
    private TextView textViewCentralSaldo;
    private TextView textViewCentralTotalSubtracaoSaldoBaixaPrioridade;
    private TextView textViewCentralTotalSubtracaoSaldoGeral;
    private TextView textViewCentralTotalBeneficioMensal;
    private TextView textViewCentralTotalSomaSaldo;
    private TextView textViewValorEconomizado;
    private TextView textViewPercentualEconomizado;
    private TextView textViewCentralTotalDividasBaixaPrioridade;
    private TextView textViewCentralTotalDividasGeral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_central);

        this.criarElementos();
        this.carregarElementos();
    }

    public void criarElementos() {

        this.textViewCentralStatus = (TextView) findViewById(R.id.textView_central_status);
        this.textViewCentralSaldo = (TextView) findViewById(R.id.textView_central_saldo);
        this.textViewCentralTotalSubtracaoSaldoBaixaPrioridade = (TextView) findViewById(R.id.textView_central_totalSubtracaoSaldoBaixaPrioridade);
        this.textViewCentralTotalSubtracaoSaldoGeral = (TextView) findViewById(R.id.textView_central_totalSubtracaoSaldoGeral);
        this.textViewCentralTotalBeneficioMensal = (TextView) findViewById(R.id.textView_central_totalBeneficioMensal);
        this.textViewCentralTotalSomaSaldo = (TextView) findViewById(R.id.textView_central_totalSomaSaldo);
        this.textViewValorEconomizado = (TextView) findViewById(R.id.textView_valorEconomizado);
        this.textViewPercentualEconomizado = (TextView) findViewById(R.id.textView_percentualEconomizado);
        this.textViewCentralTotalDividasBaixaPrioridade = (TextView) findViewById(R.id.textView_central_totalDividasBaixaPrioridade);
        this.textViewCentralTotalDividasGeral = (TextView) findViewById(R.id.textView_central_totalDividasGeral);
    }

    public void carregarElementos() {

        Requisicao requisicao = new Requisicao();
        Formatacao formatacao = new Formatacao();
        Central central = requisicao.requestInformacoesCentral();
        this.textViewCentralStatus.setText(central.getStatus());
        this.textViewCentralSaldo.setText(formatacao.formatarValorMonetario("" + central.getSaldo()));
        this.textViewCentralTotalSubtracaoSaldoBaixaPrioridade.setText(formatacao.formatarValorMonetario("" + central.getTotalSubtracaoSaldoBaixaPrioridade()));
        this.textViewCentralTotalSubtracaoSaldoGeral.setText(formatacao.formatarValorMonetario("" + central.getTotalSubtracaoSaldoGeral()));
        this.textViewCentralTotalBeneficioMensal.setText(formatacao.formatarValorMonetario("" + central.getTotalBeneficioMensal()));
        this.textViewCentralTotalSomaSaldo.setText(formatacao.formatarValorMonetario("" + central.getTotalSomaSaldo()));
        this.textViewValorEconomizado.setText(formatacao.formatarValorMonetario((""
                + (central.getTotalBeneficioMensal() - central.getTotalDividaMensal()))));
        this.textViewPercentualEconomizado.setText(formatacao.formatarValorPercentual(central.getPercentualEconomizado()));
        this.textViewCentralTotalDividasBaixaPrioridade.setText(formatacao.formatarValorMonetario("" + central.getTotalDividaMensalBaixaPrioridade()));
        this.textViewCentralTotalDividasGeral.setText(formatacao.formatarValorMonetario("" + central.getTotalDividaMensal()));
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

                Intent activityCentral = new Intent(CentralActivity.this, CentralActivity.class);
                startActivity(activityCentral);
                return true;

            case R.id.action_perfilDaConta:

                Intent activityPerfilDaConta = new Intent(CentralActivity.this, PerfilContaActivity.class);
                startActivity(activityPerfilDaConta);
                return true;

            case R.id.action_somaSaldo:

                Intent activitySomaDeSaldo = new Intent(CentralActivity.this, SomaDeSaldoActivity.class);
                startActivity(activitySomaDeSaldo);
                return true;

            case R.id.action_subtracaoSaldo:

                Intent activitySubtracaoDeSaldo = new Intent(CentralActivity.this, SubtracaoDeSaldoActivity.class);
                startActivity(activitySubtracaoDeSaldo);
                return true;

            case R.id.action_lucroMensal:

                Intent activityLucroMensal = new Intent(CentralActivity.this, LucroMensalActivity.class);
                startActivity(activityLucroMensal);
                return true;

            case R.id.action_contasPagar:

                Intent activityContasAPagar = new Intent(CentralActivity.this, ContasAPagarActivity.class);
                startActivity(activityContasAPagar);
                return true;

            case R.id.action_meta:

                Intent activityMeta = new Intent(CentralActivity.this, MetaActivity.class);
                startActivity(activityMeta);
                return true;


            case R.id.action_selecaoConta:

                Intent activitySelecaoConta = new Intent(CentralActivity.this, SelecaoContaActivity.class);
                startActivity(activitySelecaoConta);
                return true;

            case R.id.action_sair:

                Intent activityLogin = new Intent(CentralActivity.this, LoginActivity.class);
                startActivity(activityLogin);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
