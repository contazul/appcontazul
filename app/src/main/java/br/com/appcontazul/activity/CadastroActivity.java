package br.com.appcontazul.activity;

import android.app.Notification;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.appcontazul.R;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    public void mostrarSucesso(View v) {

        AlertDialog.Builder popup = new AlertDialog.Builder(CadastroActivity.this);
        popup.setTitle(R.string.activityCadastro_tituloSucesso);
        popup.setMessage(R.string.activityCadastro_RE01);
        popup.setPositiveButton(R.string.activityCadastro_popupBotaoProsseguir, null);
        popup.create();
        popup.show();
    }
}
