package br.com.appcontazul.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import br.com.appcontazul.R;
import br.com.appcontazul.contentstatic.Excecoes;
import br.com.appcontazul.contentstatic.ReferenciaUsuario;
import br.com.appcontazul.rest.Requisicao;

public class LoginActivity extends AppCompatActivity {

    EditText editTextUsuario;
    TextView textViewRE06;
    EditText editTextSenha;
    TextView textViewRE09RE07;
    ProgressBar pbHeaderProgress;
    boolean campoUsuarioVazio;
    boolean campoSenhaObrigatorio;
    boolean nomeUsuarioExiste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_login);

        editTextUsuario = (EditText) findViewById(R.id.editText_usuario);
        textViewRE06 = (TextView) findViewById(R.id.textView_RE06);
        editTextSenha = (EditText) findViewById(R.id.editText_senha);
        textViewRE09RE07 = (TextView)  findViewById(R.id.textView_RE09_RE07);
        pbHeaderProgress = (ProgressBar) findViewById(R.id.pbHeaderProgress);
        campoUsuarioVazio = false;
        campoSenhaObrigatorio = false;
        nomeUsuarioExiste = false;

        editTextUsuario.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

                textViewRE06.setText("");
            }




        });
        editTextSenha.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {


            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

                textViewRE09RE07.setText("");
            }

        });
    }

    public void cadastrar(View v) {

        Intent cadastro = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(cadastro);
    }

    public void acessar(View v) {

        LongOperation longOperation = new LongOperation();
        longOperation.execute();
    }

    public void checarValidacoes() {

        if(campoUsuarioVazio)
            textViewRE06.setText(R.string.activityCadastro_RE06);

        if(campoSenhaObrigatorio)
            textViewRE09RE07.setText(R.string.activityCadastro_RE07);

        if(nomeUsuarioExiste)
            textViewRE09RE07.setText(R.string.activityLogin_RE09);

        campoUsuarioVazio = false;
        campoSenhaObrigatorio = false;
        nomeUsuarioExiste = false;
    }

    public void acessar() {

        ReferenciaUsuario.nomeUsuarioLogado = editTextUsuario.getText().toString();
        Intent acessar = new Intent(LoginActivity.this, SelecaoContaActivity.class);
        startActivity(acessar);
    }

    public boolean validarCampos() {

        Requisicao requisicao = new Requisicao();
        boolean validado = true;
        if(editTextUsuario.getText().toString().equals("")) {

            campoUsuarioVazio = true;
            validado = false;
        }

        if(editTextSenha.getText().toString().equals("")){

            campoSenhaObrigatorio = true;
            validado = false;
        }
        else if (requisicao.requestEx08(editTextUsuario.getText().toString(),
                editTextSenha.getText().toString()).equals(Excecoes.EX08)){

            nomeUsuarioExiste = true;
            validado = false;

        }

        return validado;
    }

    private class LongOperation extends AsyncTask<Void, Void, Boolean> {


        @Override
        protected Boolean doInBackground(Void... voids) {

            return validarCampos();
        }

        @Override
        protected void onPreExecute() {

            pbHeaderProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

            if(aBoolean) {

                acessar();
                pbHeaderProgress.setVisibility(View.GONE);
            }

            pbHeaderProgress.setVisibility(View.GONE);
            checarValidacoes();
        }
    }
}
