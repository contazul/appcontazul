package br.com.appcontazul.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import br.com.appcontazul.R;

public class SimuladorActivity extends AppCompatActivity {

    // COMPONENTES
    private Switch switch01;
    private Switch switch02;
    private Switch switch03;
    private Switch switch04;
    private Switch switch05;
    private Switch switch06;
    // FIM COMPONENTES

    // ATRIBUTOS DE CONTROLE
    private boolean[] opcoes;
    // FIM ATRIBUTOS DE CONTROLE

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulador);

        this.criarElementos();
    }

    public void criarElementos() {

        // INICIANDO COMPONENTES
        this.switch01 = (Switch) findViewById(R.id.switch_01);
        this.switch02 = (Switch) findViewById(R.id.switch_02);
        this.switch03 = (Switch) findViewById(R.id.switch_03);
        this.switch04 = (Switch) findViewById(R.id.switch_04);
        this.switch05 = (Switch) findViewById(R.id.switch_05);
        this.switch06 = (Switch) findViewById(R.id.switch_06);

        this.inicilizarSwitch01();
        this.inicilizarSwitch02();
        this.inicilizarSwitch03();
        this.inicilizarSwitch04();
        this.inicilizarSwitch05();
        this.inicilizarSwitch06();
        // FIM INICIANDO COMPONENTES

        // INCIANDO ATRIBUTOS DE CONTROLE
        this.opcoes = new boolean[5];
        // FIM INICIANDO ATRIBUTOS DE CONTROLE
    }

    public void inicilizarSwitch01() {

        this.switch01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                controlarSwitchsParaSwitch01(!isChecked);
            }
        });
    }

    public void inicilizarSwitch02() {

        this.switch02.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                controlarSwitch01ParaTodos(!isChecked);
            }
        });
    }

    public void inicilizarSwitch03() {

        this.switch03.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                controlarSwitch01ParaTodos(!isChecked);
            }
        });
    }

    public void inicilizarSwitch04() {

        this.switch04.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                controlarSwitch01ParaTodos(!isChecked);
            }
        });
    }

    public void inicilizarSwitch05() {

        this.switch05.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                controlarSwitch01ParaTodos(!isChecked);
            }
        });
    }

    public void inicilizarSwitch06() {

        this.switch06.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                controlarSwitch01ParaTodos(!isChecked);
            }
        });
    }

    // MÉTODOS DE CONTROLE
    public void controlarSwitchsParaSwitch01(boolean desabilitar) {

        this.switch02.setChecked(false);
        this.switch02.setEnabled(desabilitar);
        this.switch03.setChecked(false);
        this.switch03.setEnabled(desabilitar);
        this.switch04.setChecked(false);
        this.switch04.setEnabled(desabilitar);
        this.switch05.setChecked(false);
        this.switch05.setEnabled(desabilitar);
        this.switch06.setChecked(false);
        this.switch06.setEnabled(desabilitar);
    }

    public void controlarSwitch01ParaTodos(boolean desabilitar) {

        this.switch01.setChecked(false);
        this.switch01.setEnabled(desabilitar);
    }

    public void setarOpcoes() {

        this.opcoes[0] = switch02.isChecked();
        this.opcoes[1] = switch03.isChecked();
        this.opcoes[2] = switch04.isChecked();
        this.opcoes[3] = switch05.isChecked();
        this.opcoes[4] = switch06.isChecked();
    }
    // FIM MÉTODOS DE CONTROLE

    // MENSAGENS
    public void exibirNaoEscolheuOpcao() {

        AlertDialog.Builder popup = new AlertDialog.Builder(SimuladorActivity.this);
        popup.setTitle(R.string.simulador_atencao);
        popup.setMessage(R.string.simulador_validacao01);
        popup.setPositiveButton(R.string.simulador_ok, null);
        popup.create();
        popup.show();
    }
    // FIM MENSAGENS

    // ONCLICK
    public void prosseguir(View v) {

        if(!this.validarEscolhaOpcao())
            this.exibirNaoEscolheuOpcao();
        else {

            if(switch01.isChecked()) {

                // CHAMAR SIMULAÇÃO COMPRA Á VISTA
                Intent activitySimuladorGastoAVista = new Intent(SimuladorActivity.this, SimuladorGastoAVistaActivity.class);
                startActivity(activitySimuladorGastoAVista);
            } else {

                this.setarOpcoes();
                // CHAMAR SIMULAÇÃO OUTRAS PASSANDO O ARRAY OPÇÕES
                Intent activityAdesaoDivida = new Intent(SimuladorActivity.this, SimuladorAdesaoDividaActivity.class);
                activityAdesaoDivida.putExtra("opcoes", this.opcoes);
                startActivity(activityAdesaoDivida);
            }
        }
    }
    // FIM ONCLICK

    // VALIDAÇÕES
    public boolean validarEscolhaOpcao() {

        return switch01.isChecked() || switch02.isChecked() || switch03.isChecked() ||
                switch04.isChecked() || switch05.isChecked() || switch06.isChecked();
    }
    // FIM VALIDAÇÕES

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

                Intent activityCentral = new Intent(SimuladorActivity.this, CentralActivity.class);
                startActivity(activityCentral);
                return true;

            case R.id.action_perfilDaConta:

                Intent activityPerfilDaConta = new Intent(SimuladorActivity.this, PerfilContaActivity.class);
                startActivity(activityPerfilDaConta);
                return true;

            case R.id.action_somaSaldo:

                Intent activitySomaDeSaldo = new Intent(SimuladorActivity.this, SomaDeSaldoActivity.class);
                startActivity(activitySomaDeSaldo);
                return true;

            case R.id.action_subtracaoSaldo:

                Intent activitySubtracaoDeSaldo = new Intent(SimuladorActivity.this, SubtracaoDeSaldoActivity.class);
                startActivity(activitySubtracaoDeSaldo);
                return true;

            case R.id.action_lucroMensal:

                Intent activityLucroMensal = new Intent(SimuladorActivity.this, LucroMensalActivity.class);
                startActivity(activityLucroMensal);
                return true;

            case R.id.action_contasPagar:

                Intent activityContasAPagar = new Intent(SimuladorActivity.this, ContasAPagarActivity.class);
                startActivity(activityContasAPagar);
                return true;

            case R.id.action_simulador:

                Intent activitySimulador = new Intent(SimuladorActivity.this, SimuladorActivity.class);
                startActivity(activitySimulador);
                return true;

            case R.id.action_meta:

                Intent activityMeta = new Intent(SimuladorActivity.this, MetaActivity.class);
                startActivity(activityMeta);
                return true;


            case R.id.action_selecaoConta:

                Intent activitySelecaoConta = new Intent(SimuladorActivity.this, SelecaoContaActivity.class);
                startActivity(activitySelecaoConta);
                return true;

            case R.id.action_sair:

                Intent activityLogin = new Intent(SimuladorActivity.this, LoginActivity.class);
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












