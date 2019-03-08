package br.com.appcontazul.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.appcontazul.R;
import br.com.appcontazul.util.Adaptador;

public class SelecaoContaActivity extends AppCompatActivity {

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecao_conta);
        listView = (ListView) findViewById(R.id.listaContas);

        ArrayList<String> valores = new ArrayList<>();

        valores.add("bruno");
        valores.add("bruno");
        valores.add("bruno");
        valores.add("bruno");
        valores.add("bruno");
        valores.add("bruno");
        valores.add("bruno");
        valores.add("bruno");
        valores.add("bruno");
        valores.add("bruno");
        valores.add("bruno");
        valores.add("bruno");
        valores.add("bruno");
        valores.add("bruno");
        valores.add("bruno");
        valores.add("bruno");
        valores.add("bruno");
        valores.add("igor");

/*        String[] dados = new String[]{
                "bruno","igor","nilo","trouxa1",
                "trouxa2","trouxa3","trouxa4","trouxa5",
                "trouxa6","trouxa7","trouxa8","trouxa9",
                "trouxa10"
        };*/

        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dados);
        // listView.setAdapter(adapter);

        Adaptador adaptador = new Adaptador(this, android.R.layout.simple_list_item_1, valores);
        listView.setAdapter(adaptador);
    }

    public void criarContazul (View v) {
        AlertDialog.Builder popup = new AlertDialog.Builder(SelecaoContaActivity.this);
        popup.setTitle(R.string.activityCadastro_tituloSucesso);
        popup.setMessage(R.string.activitySelacaoConta_RE_10);
        popup.setPositiveButton(R.string.activitySelacaoConta_popupBotaoProsseguir, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {


                Intent selecaoActivity = new Intent(SelecaoContaActivity.this, CentralActivity.class);
                startActivity(selecaoActivity);
            }
        });
        popup.create();
        popup.show();
    }
}
