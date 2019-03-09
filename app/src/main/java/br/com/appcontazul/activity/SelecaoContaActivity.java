package br.com.appcontazul.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import br.com.appcontazul.R;
import br.com.appcontazul.contentstatic.ReferenciaUsuario;
import br.com.appcontazul.rest.model.ListaContazul;
import br.com.appcontazul.rest.teste.ListaContazulRepository;
import br.com.appcontazul.util.Adaptador;

public class SelecaoContaActivity extends AppCompatActivity {

    ListView listaContas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecao_conta);
        listaContas = (ListView) findViewById(R.id.listaContas);

        ListaContazulRepository lcrepository = new ListaContazulRepository();
        final Adaptador adaptador = new Adaptador(lcrepository.getContas(), this);
        listaContas.setAdapter(adaptador);

        listaContas.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapter, View view,
                                    int posicao, long id) {

                ListaContazul listaContazul = adaptador.getItem(posicao);
                ReferenciaUsuario.numeroContazul = listaContazul.getNumeroContazul();
                Intent centralActivity = new Intent(SelecaoContaActivity.this, CentralActivity.class);
                startActivity(centralActivity);
            }
        });
    }

    public void criarContazul (View v) {
        AlertDialog.Builder popup = new AlertDialog.Builder(SelecaoContaActivity.this);
        popup.setTitle(R.string.activitySelacaoConta_tituloSucesso);
        popup.setMessage(R.string.activitySelacaoConta_RE_10);
        popup.setPositiveButton(R.string.activitySelacaoConta_popupBotaoProsseguir,null);
        popup.create();
        popup.show();
    }
}
