package br.com.appcontazul.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.appcontazul.R;
import br.com.appcontazul.contentstatic.ReferenciaUsuario;

public class PerfilContaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_conta);
    }

    public void perfilDaContaMostrarMenu(View v) {

        Intent menuActivity = new Intent(PerfilContaActivity.this, MenuActivity.class);
        startActivity(menuActivity);
    }
}
