package com.example.iury.gerenciadordeenergiaeltrica;
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class RecursoAdaptador extends BaseAdapter {
    private final List<Recurso> recursos;
    private final Activity activity;

    public RecursoAdaptador(List<Recurso> recursos, Activity activity){
        this.recursos = recursos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return this.recursos.size();
    }

    @Override
    public Object getItem(int i) {
        return this.recursos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return this.recursos.get(i).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View linha = convertView;
        Recurso recurso = recursos.get(i);
        Bitmap bitmap;

        if(linha == null){
            //linha = this.activity.getLayoutInflater().inflate(R.layout.)
        }

        return null;
    }
}
