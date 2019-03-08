package br.com.appcontazul.util;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Adaptador extends ArrayAdapter<String> {

    ArrayList<String> valores;

    public Adaptador(Context context, int textViewResourceId, ArrayList<String> objects) {

        super(context, textViewResourceId, objects);
        this.valores = objects;
    }
}
