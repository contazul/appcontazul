package br.com.appcontazul.activity;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
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

        if(validarCampos()) {

            // Ai pode acessar
            ReferenciaUsuario.nomeUsuarioLogado = editTextUsuario.getText().toString();
            Intent acessar = new Intent(LoginActivity.this, SelecaoContaActivity.class);
            startActivity(acessar);
        }
    }

    public boolean validarCampos() {

        Requisicao requisicao = new Requisicao();
        boolean validado = true;
        if(editTextUsuario.getText().toString().equals("")) {

            textViewRE06.setText(R.string.activityCadastro_RE06);
            validado = false;
        }

        if(editTextSenha.getText().toString().equals("")){
            textViewRE09RE07.setText(R.string.activityCadastro_RE07);
            validado = false;
        }
        else if (requisicao.requestEx08(editTextUsuario.getText().toString(),
                editTextSenha.getText().toString()).equals(Excecoes.EX08)){


            textViewRE09RE07.setText(R.string.activityLogin_RE09);
            validado = false;

        }

        return validado;
    }
}
