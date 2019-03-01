package br.com.appcontazul.activity;

import android.app.ActionBar;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import br.com.appcontazul.R;

public class CadastroActivity extends AppCompatActivity {

   EditText editTextNomeUsu;
   EditText editTextSenhaUsu;
   EditText editTextConfSenha;
   TextView textViewRE03RE02RE06;
   TextView textViewRE05RE07;
   TextView textViewRE08RE04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        editTextNomeUsu = (EditText) findViewById(R.id.editText_NomeUsu);
        editTextSenhaUsu = (EditText) findViewById(R.id.editText_SenhaUsu);
        editTextConfSenha = (EditText) findViewById(R.id.editText_ConfSenha);
        textViewRE03RE02RE06 = (TextView) findViewById(R.id.textView_RE03_RE02_RE06);
        textViewRE05RE07 = (TextView) findViewById(R.id.textViewRE05_RE07);
        textViewRE08RE04 = (TextView) findViewById(R.id.textViewRE08_RE04);


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



        if(validarcampos1()){
            //Ai pode acessar
        }

/*        AlertDialog.Builder popup = new AlertDialog.Builder(CadastroActivity.this);
        popup.setTitle(R.string.activityCadastro_tituloSucesso);
        popup.setMessage(R.string.activityCadastro_RE01);
        popup.setPositiveButton(R.string.activityCadastro_popupBotaoProsseguir, null);
        popup.create();
        popup.show();*/
    }

   /* public void cadastrar (View v){

        if(validarcampos1(v)){
            //Ai pode acessar
        }
    }*/

    public boolean validarcampos1 (){
        boolean validar= true;
        if(editTextNomeUsu.getText().toString().equals("")){

            textViewRE03RE02RE06.setText(R.string.activityCadastro_RE06);
            validar = false;

        }
        else {
            for (int i = 0; i < editTextNomeUsu.getText().toString().length();i++ ){
                if (!Character.isAlphabetic((editTextNomeUsu.getText().toString().charAt(i)))){

                    boolean isNumero = false;
                    try {

                        Integer.parseInt("" + editTextNomeUsu.getText().toString().charAt(i));
                        isNumero = true;
                    } catch (Exception e) {

                        textViewRE03RE02RE06.setText(R.string.activityCadastro_RE03);
                        validar= false;
                        break;
                    }

                    if(!isNumero) {
                        textViewRE03RE02RE06.setText(R.string.activityCadastro_RE03);
                        validar = false;
                        break;
                    }
                }
            }
            /*if (){
                textViewRE03RE02RE06.setText(R.string.activityCadastro_RE02);
                validar = false;
            }*/
        }


        if(editTextSenhaUsu.getText().toString().equals("")){

            textViewRE05RE07.setText(R.string.activityLogin_RE07);
            validar = false;
        }
        else if(textViewRE05RE07.getText().toString().length() < 4){

            textViewRE05RE07.setText(R.string.activityCadastro_RE05);
            validar = false;
        }

        if (editTextConfSenha.getText().toString().equals("")){

            textViewRE08RE04.setText(R.string.activityCadastro_RE08);
            validar = false;
        }
        else if(!editTextConfSenha.getText().toString().equals(editTextSenhaUsu.getText().toString())){

            textViewRE08RE04.setText(R.string.activityCadastro_RE04);
/*            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            layoutParams.leftToLeft = R.id.editText_ConfSenha;
            layoutParams.topToBottom = R.id.editText_ConfSenha;
            textViewRE08RE04.setLayoutParams(layoutParams);*/
            validar = false;
        }




        return validar;

    }







}
