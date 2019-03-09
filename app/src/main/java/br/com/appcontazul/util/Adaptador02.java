package br.com.appcontazul.util;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.appcontazul.R;

public class Adaptador02 extends BaseAdapter {

    private final List<String> funcionalidades;
    private final Activity act;

    public Adaptador02(List<String> funcionalidades, Activity act) {

        this.funcionalidades = funcionalidades;
        this.act = act;
    }

    @Override
    public int getCount() {

        return this.funcionalidades.size();
    }

    @Override
    public String getItem(int posicao) {

        return this.funcionalidades.get(posicao);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater()
                .inflate(R.layout.lista_menu, parent, false);
        String funcionalidade = this.funcionalidades.get(position);
        TextView textViewMenuFuncionalidade = (TextView) view.findViewById(R.id.textView_menuFuncionalidade);
        textViewMenuFuncionalidade.setText(funcionalidade);
        return view;
    }
}
