package br.com.appcontazul.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import br.com.appcontazul.R;
import br.com.appcontazul.rest.teste.ListaLucroMensalRepository;
import br.com.appcontazul.util.Adaptador;
import br.com.appcontazul.util.Adaptador05;

public class LucroMensalActivity extends AppCompatActivity {


    private ListView listaLucroMensal;
    private Adaptador05 adaptador05;
    private static final double saldoAtual = 500;
    private TextView textViewValorFormatado;
    EditText editTextDescricaoBeneficio;
    TextView textViewRE30;
    EditText editTextValorBeneficio;
    TextView textViewRE31;
    TextView saldoConta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucro_mensal);

        this.listaLucroMensal = (ListView) findViewById(R.id.listaSaldoConta);
        ListaLucroMensalRepository listaLucro = new ListaLucroMensalRepository();
        this.adaptador05 = new Adaptador05(listaLucroMensal.getListaLucroMensal())

    }



    public void contralMostrarMenu(View v) {

        Intent menuActivity = new Intent(LucroMensalActivity.this, MenuActivity.class);
        startActivity(menuActivity);
    }

}
