package br.com.appcontazul.activity;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableRow;
import android.widget.TextView;

import br.com.appcontazul.R;
import br.com.appcontazul.contentstatic.Excecoes;
import br.com.appcontazul.contentstatic.ReferenciaUsuario;
import br.com.appcontazul.rest.Requisicao;
import br.com.appcontazul.util.Validacao;

public class CadastroActivity extends AppCompatActivity {

    EditText editTextNomeUsu;
    EditText editTextSenhaUsu;
    EditText editTextConfSenha;
    TextView textViewRE03RE02RE06;
    TextView textViewRE05RE07;
    TextView textViewRE08RE04;
    ProgressBar pbHeaderProgress;
    Button buttonCadastrar;
    boolean campoUsuarioVazio;
    boolean camporUsuarioCaracterEspecial;
    boolean usuarioJaExiste;
    boolean campoSenhaVazio;
    boolean campoSenhaQtdCaracter;
    boolean campoConfirmarSenhaObrigatorio;
    boolean campoSenhasDiferentes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_cadastro);
        editTextNomeUsu = (EditText) findViewById(R.id.editText_NomeUsu);
        editTextSenhaUsu = (EditText) findViewById(R.id.editText_SenhaUsu);
        editTextConfSenha = (EditText) findViewById(R.id.editText_ConfSenha);
        textViewRE03RE02RE06 = (TextView) findViewById(R.id.textView_RE03_RE02_RE06);
        textViewRE05RE07 = (TextView) findViewById(R.id.textViewRE05_RE07);
        textViewRE08RE04 = (TextView) findViewById(R.id.textViewRE08_RE04);
        pbHeaderProgress = (ProgressBar) findViewById(R.id.pbHeaderProgress);
        buttonCadastrar = (Button) findViewById(R.id.bottom_Cadastrar1);
        campoUsuarioVazio = false;
        camporUsuarioCaracterEspecial = false;
        usuarioJaExiste = false;
        campoSenhaVazio = false;
        campoSenhaQtdCaracter = false;
        campoConfirmarSenhaObrigatorio = false;
        campoSenhasDiferentes = false;

        editTextNomeUsu.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {


            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

                textViewRE03RE02RE06.setText("");
            }
        });

        editTextSenhaUsu.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {


            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

                textViewRE05RE07.setText("");
            }
        });

        editTextConfSenha.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {


            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

                textViewRE08RE04.setText("");
            }
        });

    }

    public void mostrarSucesso(View v) {

        LongOperation longOperation = new LongOperation();
        longOperation.execute();
    }

    public void mostrarPopup() {

        AlertDialog.Builder popup = new AlertDialog.Builder(CadastroActivity.this);
        popup.setTitle(R.string.activityCadastro_tituloSucesso);
        popup.setMessage(R.string.activityCadastro_RE01);
        popup.setPositiveButton(R.string.activityCadastro_popupBotaoProsseguir, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                ReferenciaUsuario.nomeUsuarioLogado = editTextNomeUsu.getText().toString();
                Intent selecaoActivity = new Intent(CadastroActivity.this, SelecaoContaActivity.class);
                startActivity(selecaoActivity);
            }
        });
        popup.create();
        popup.show();
    }

    public void cadastrarUsuario() {

        Requisicao requisicao = new Requisicao();
        requisicao.requestInserirUsuario(editTextNomeUsu.getText().toString(),
                editTextSenhaUsu.getText().toString());
    }

    public void checarValidacoes() {

        if(campoUsuarioVazio)
            textViewRE03RE02RE06.setText(R.string.activityCadastro_RE06);

            else if(camporUsuarioCaracterEspecial)
                textViewRE03RE02RE06.setText(R.string.activityCadastro_RE03);

            else if(usuarioJaExiste)
                textViewRE03RE02RE06.setText(R.string.activityCadastro_RE02);

        if(campoSenhaVazio)
            textViewRE05RE07.setText(R.string.activityLogin_RE07);

            else if(campoSenhaQtdCaracter)
                textViewRE05RE07.setText(R.string.activityCadastro_RE05);

        if(campoConfirmarSenhaObrigatorio)
            textViewRE08RE04.setText(R.string.activityCadastro_RE08);

            else if(campoSenhasDiferentes)
                textViewRE08RE04.setText(R.string.activityCadastro_RE04);

        campoUsuarioVazio = false;
        camporUsuarioCaracterEspecial = false;
        usuarioJaExiste = false;
        campoSenhaVazio = false;
        campoSenhaQtdCaracter = false;
        campoConfirmarSenhaObrigatorio = false;
        campoSenhasDiferentes = false;
    }

   /* public void cadastrar (View v){

        if(validarcampos1(v)){
            //Ai pode acessar
        }
    }*/

    public boolean validarcampos(){

        Requisicao requisicao = new Requisicao();
        Validacao validacao = new Validacao();
        boolean validar= true;
        if(editTextNomeUsu.getText().toString().equals("")){

            campoUsuarioVazio = true;
            validar = false;

        }
        else if(validacao.contemCaracterEspecial(editTextNomeUsu.getText().toString())){

            camporUsuarioCaracterEspecial = true;
            validar= false;

        } else if(requisicao.requestEx01(editTextNomeUsu.getText().toString()).equals(Excecoes.EX01)) {

            usuarioJaExiste = true;
            validar= false;
        }

        if(editTextSenhaUsu.getText().toString().equals("")){

            campoSenhaVazio = true;
            validar = false;
        }
        else if(editTextSenhaUsu.getText().toString().length() < 4){

            campoSenhaQtdCaracter = true;
            validar = false;
        }

        if (editTextConfSenha.getText().toString().equals("")){

            campoConfirmarSenhaObrigatorio = true;
            validar = false;
        }
        else if(!editTextConfSenha.getText().toString().equals(editTextSenhaUsu.getText().toString())){

            campoSenhasDiferentes = true;
/*            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            layoutParams.leftToLeft = R.id.editText_ConfSenha;
            layoutParams.topToBottom = R.id.editText_ConfSenha;
            textViewRE08RE04.setLayoutParams(layoutParams);*/
            validar = false;
        }

        return validar;
    }

    public void desabilitarItens() {

        editTextNomeUsu.setEnabled(false);
        editTextConfSenha.setEnabled(false);
        editTextSenhaUsu.setEnabled(false);
    }

    public void habilitarItens() {

        editTextNomeUsu.setEnabled(true);
        editTextConfSenha.setEnabled(true);
        editTextSenhaUsu.setEnabled(true);
    }

    private class LongOperation extends AsyncTask<Void, Void, Boolean> {


        @Override
        protected Boolean doInBackground(Void... voids) {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(!validarcampos()) {

                return false;
            }

            cadastrarUsuario();
            return true;
        }

        @Override
        protected void onPreExecute() {

            desabilitarItens();
            pbHeaderProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

            if(aBoolean) {

                mostrarPopup();
                pbHeaderProgress.setVisibility(View.GONE);
            }

            pbHeaderProgress.setVisibility(View.GONE);
            checarValidacoes();
            habilitarItens();
        }
    }
}
