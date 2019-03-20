package br.com.appcontazul.activity;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
    }

    public void carregarElementos() {

        Requisicao requisicao = new Requisicao();
        Formatacao formatacao = new Formatacao();
        Central central = requisicao.requestInformacoesCentral();
        this.textViewCentralStatus.setText(getResources().getString(R.string.activityCentral_status) + " " + central.getStatus());
        this.textViewCentralSaldo.setText(formatacao.formatarValorMonetario("" + central.getSaldo()));
        this.textViewCentralTotalSubtracaoSaldoBaixaPrioridade.setText(formatacao.formatarValorMonetario("" + central.getTotalSubtracaoSaldoBaixaPrioridade()));
        this.textViewCentralTotalSubtracaoSaldoGeral.setText(formatacao.formatarValorMonetario("" + central.getTotalSubtracaoSaldoGeral()));
        this.textViewCentralTotalBeneficioMensal.setText(formatacao.formatarValorMonetario("" + central.getTotalBeneficioMensal()));
        this.textViewCentralTotalSomaSaldo.setText(formatacao.formatarValorMonetario("" + central.getTotalSomaSaldo()));
    }

    public void contralMostrarMenu(View v) {

        Intent menuActivity = new Intent(CentralActivity.this, MenuActivity.class);
        startActivity(menuActivity);
    }
}
