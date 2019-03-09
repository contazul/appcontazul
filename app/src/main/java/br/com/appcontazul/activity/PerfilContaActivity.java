package br.com.appcontazul.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import br.com.appcontazul.R;
import br.com.appcontazul.contentstatic.ReferenciaUsuario;
import br.com.appcontazul.rest.Requisicao;
import br.com.appcontazul.rest.model.PerfilContazul;

public class PerfilContaActivity extends AppCompatActivity {

    private TextView textViewPerfilConta;
    private TextView textViewNumeroContazul;
    private TextView textViewStatus;
    private EditText editTextDescricaoConta;
    private EditText editTextValorIdeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_perfil_conta);

        this.textViewPerfilConta = (TextView) findViewById(R.id.textView_perfilConta);
        this.textViewNumeroContazul = (TextView) findViewById(R.id.textView_numeroContazul);
        this.textViewStatus = (TextView) findViewById(R.id.textView_status);

        Requisicao requisicao = new Requisicao();
        PerfilContazul perfilContazul = requisicao.requestPerfilConta();

        this.textViewPerfilConta.setText(getResources().getString(R.string.activityPerfilConta_perfilDaConta)
                + " " + perfilContazul.getPerfil());

        this.textViewNumeroContazul.setText(getResources().getString(R.string.activityPerfilConta_numeroContazul)
                + " " + perfilContazul.getNumeroContazul());

        this.textViewStatus.setText(getResources().getString(R.string.activityPerfilConta_status)
                + " " + perfilContazul.getStatus());

        this.editTextDescricaoConta = (EditText) findViewById(R.id.editText_DescricaoConta);
        this.editTextValorIdeal = (EditText) findViewById(R.id.editText_ValorIdeal);
    }

    public void perfilDaContaMostrarMenu(View v) {

        Intent menuActivity = new Intent(PerfilContaActivity.this, MenuActivity.class);
        startActivity(menuActivity);
    }

    public void definir(View v) {

        String valorIdeal = this.editTextValorIdeal.getText().toString();
        if(valorIdeal.equals("")) {

            valorIdeal = "0.0";
        }
        Requisicao requisicao = new Requisicao();
        requisicao.requestAtualizarPerfilContazul(this.editTextDescricaoConta.getText().toString(),
                valorIdeal);
        AlertDialog.Builder popup = new AlertDialog.Builder(PerfilContaActivity.this);
        popup.setTitle(R.string.activityPerfilConta_sucesso);
        popup.setMessage(R.string.activityPerfilConta_RE25);
        popup.setPositiveButton(R.string.activityPerfilConta_buttonPopupOk, null);
        popup.create();
        popup.show();

    }
}












