package br.com.appcontazul.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.appcontazul.R;
import br.com.appcontazul.util.Adaptador02;

public class MenuActivity extends AppCompatActivity {

    private ListView listaMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        this.listaMenu = (ListView) findViewById(R.id.listaMenu);

        List<String> funcionalidades = new ArrayList<>();

        funcionalidades.add(getResources().getString(R.string.activityMenu_funcionalidade01));
        funcionalidades.add(getResources().getString(R.string.activityMenu_funcionalidade02));
        funcionalidades.add(getResources().getString(R.string.activityMenu_funcionalidade03));
        funcionalidades.add(getResources().getString(R.string.activityMenu_funcionalidade04));
        funcionalidades.add(getResources().getString(R.string.activityMenu_funcionalidade05));
        funcionalidades.add(getResources().getString(R.string.activityMenu_funcionalidade06));
        funcionalidades.add(getResources().getString(R.string.activityMenu_funcionalidade07));
        funcionalidades.add(getResources().getString(R.string.activityMenu_funcionalidade08));
        funcionalidades.add(getResources().getString(R.string.activityMenu_funcionalidade09));
        funcionalidades.add(getResources().getString(R.string.activityMenu_funcionalidade10));
        funcionalidades.add(getResources().getString(R.string.activityMenu_funcionalidade11));
        funcionalidades.add(getResources().getString(R.string.activityMenu_funcionalidade12));

        final Adaptador02 adaptador02 = new Adaptador02(funcionalidades, this);

        this.listaMenu.setAdapter(adaptador02);

        listaMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapter, View view,
                                    int posicao, long id) {

                String funcionalidade = adaptador02.getItem(posicao);
                switch (funcionalidade) {

                    case "Central":

                        Intent activityCentral = new Intent(MenuActivity.this, CentralActivity.class);
                        startActivity(activityCentral);
                        break;

                    case "Perfil da conta":

                        Intent activityPerfilDaConta = new Intent(MenuActivity.this, PerfilContaActivity.class);
                        startActivity(activityPerfilDaConta);
                        break;

                    case "Soma de saldo":

                        Intent activitySomaDeSaldo = new Intent(MenuActivity.this, SomaDeSaldoActivity.class);
                        startActivity(activitySomaDeSaldo);
                        break;

                    case  "Subtração de saldo":

                        Intent activitySubtracaoDeSaldo = new Intent(MenuActivity.this, SubtracaoDeSaldoActivity.class);
                        startActivity(activitySubtracaoDeSaldo);
                        break;

                    case "Lucro mensal":

                        Intent activityLucroMensal = new Intent(MenuActivity.this, LucroMensalActivity.class);
                        startActivity(activityLucroMensal);
                        break;

                    case "Contas a pagar":

                        Intent activityContasAPagar = new Intent(MenuActivity.this, ContasAPagarActivity.class);
                        startActivity(activityContasAPagar);
                        break;

                    case "Seleção de conta":

                        Intent activitySelecaoConta = new Intent(MenuActivity.this, SelecaoContaActivity.class);
                        startActivity(activitySelecaoConta);
                        break;

                    case "Sair":

                        Intent activityLogin = new Intent(MenuActivity.this, LoginActivity.class);
                        startActivity(activityLogin);
                        break;
                }
            }
        });
    }
}
















