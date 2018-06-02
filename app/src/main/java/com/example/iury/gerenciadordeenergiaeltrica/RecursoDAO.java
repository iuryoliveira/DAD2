package com.example.iury.gerenciadordeenergiaeltrica;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecursoDAO extends SQLiteOpenHelper{
    private static final int VERSAO = 1;
    private  static final String TABELA = "recurso";
    private static final String DATABASE = "energia.db";

    public RecursoDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABELA + "(ID_RECURSO INTEGER PRIMARY KEY AUTOINCREMENT, TIPO CHAR(1), DESCRICAO VARCHAR(50), VOLTAGEM FLOAT(9,2), POTENCIA_USO FLOAT(9,2), POTENCIA_STAND FLOAT(9,2), CAMINHO_FOTO TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion == 1){
            String sql = "ALTER TABLE " + TABELA + " ADD COLUMN POTENCIA_USO FLOAT(9,2);";
            db.execSQL(sql);

            String sql2 =  "ALTER TABLE " + TABELA + " ADD COLUMN POTENCIA_STAND FLOAT(9,2);";
            db.execSQL(sql2);

            String sql3 = "ALTER TABLE " + TABELA + " ADD COLUMN CAMINHO_FOTO TEXT;";
            db.execSQL(sql3);
        }
    }

    /*public Boolean inserirRecurso(String tipo, String voltagem, Double potencia_uso, Double potencia_stand){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TIPO", tipo);
        contentValues.put("VOLTAGEM", voltagem);
        contentValues.put("POTENCIA_USO", potencia_uso);
        contentValues.put("POTENCIA_STAND", potencia_stand);

        Long result = getWritableDatabase().insert(TABELA, null, contentValues );
        if(result == -1)
            return false;
        else
            return true;
    }*/
    public Boolean inserirRecurso(Recurso recurso){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TIPO", recurso.getTipo().toString());
        contentValues.put("DESCRICAO", recurso.getDescricao());
        contentValues.put("VOLTAGEM", recurso.getVoltagem());
        contentValues.put("POTENCIA_USO", recurso.getPotenciaUso());
        contentValues.put("POTENCIA_STAND", recurso.getPotenciaStand());
        contentValues.put("CAMINHO_FOTO", recurso.getFoto());

        Long result = getWritableDatabase().insert(TABELA, null, contentValues );
        if(result == -1)
            return false;
        else
            return true;
    }

    public void apagarRecurso(Recurso recurso){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = {recurso.getId().toString()};
        db.delete(TABELA,"ID=?",args);
    }

    public void apagarTudo(){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = {"1"};
        db.delete(TABELA, " '1'=?",args);
    }

    public void alterarRecurso(Recurso recurso){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("VOLTAGEM", recurso.getVoltagem());
        contentValues.put("POTENCIA_USO", recurso.getPotenciaUso());
        contentValues.put("POTENCIA_STAND", recurso.getPotenciaStand());

        String[] args = {recurso.getId().toString()};
        getWritableDatabase().update(TABELA, contentValues, "ID=?", args);
    }

    public boolean isRecurso(String id){
        String[] parametros = {id};
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABELA + " WHERE ID=?", parametros );
        int total = cursor.getCount();
        return total > 0;
    }

    public List<Recurso> getLista(){
        List<Recurso> recursos = new ArrayList<Recurso>();
        //Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABELA + ";", null);
        Cursor cursor = getReadableDatabase().rawQuery("select * from "+ TABELA, null);

        while (cursor.moveToNext()){
            Recurso recurso = new Recurso();

            recurso.setId(cursor.getLong(cursor.getColumnIndex("ID_RECURSO")));
            recurso.setDescricao(cursor.getString(cursor.getColumnIndex("TIPO")));
            recurso.setDescricao(cursor.getString(cursor.getColumnIndex("DESCRICAO")));
            recurso.setVoltagem(cursor.getDouble(cursor.getColumnIndex("VOLTAGEM")));
            recurso.setPotenciaUso(cursor.getDouble(cursor.getColumnIndex("POTENCIA_USO")));
            recurso.setPotenciaStand(cursor.getDouble(cursor.getColumnIndex("POTENCIA_STAND")));
            recurso.setFoto(cursor.getString(cursor.getColumnIndex("CAMINHO_FOTO")));

            recursos.add(recurso);
        }
        cursor.close();
        return recursos;
    }

}
