package com.example.iury.gerenciadordeenergiaeltrica;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

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
    public View getView(int i, View convertView, ViewGroup parent) {
        View linha = convertView;
        Recurso recurso = recursos.get(i);
        Bitmap bitmap;

        if(linha == null){
            linha = this.activity.getLayoutInflater().inflate(R.layout.celula_recurso, parent, false);
        }

        TextView descricao = (TextView) linha.findViewById(R.id.descricao);
        TextView voltagem = (TextView) linha.findViewById(R.id.voltagem);
        TextView potenciaUso = (TextView) linha.findViewById(R.id.potenciaUso);
        TextView potenciaStand = (TextView) linha.findViewById(R.id.potenciaStand);

        ImageView foto = (ImageView) linha.findViewById(R.id.imagemCelula);

        if(i % 2 == 0){
            linha.setBackgroundColor(activity.getResources().getColor(R.color.colorAccent));
        }

        descricao.setText(recurso.getDescricao());
        voltagem.setText(recurso.getVoltagem().toString());
        potenciaUso.setText(recurso.getPotenciaUso().toString());
        potenciaStand.setText(recurso.getPotenciaStand().toString());

        if(recurso.getFoto() != null)
            bitmap = BitmapFactory.decodeFile(recurso.getFoto());

        else{
            bitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.energia);
        }

        foto.setImageBitmap(bitmap);
        return linha;
    }
}
