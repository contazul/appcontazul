package br.com.appcontazul.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.appcontazul.R;
import br.com.appcontazul.contentstatic.ReferenciaUsuario;

public class CentralActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_central);
    }

    public void contralMostrarMenu(View v) {

        Intent menuActivity = new Intent(CentralActivity.this, MenuActivity.class);
        startActivity(menuActivity);
    }
}
