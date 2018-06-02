package com.example.iury.gerenciadordeenergiaeltrica;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RecursoDAO extends SQLiteOpenHelper{
    private static final int VERSAO = 1;
    private  static final String TABELA = "recurso";
    private static final String DATABASE = "energia.db";

    public RecursoDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABELA + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, TIPO CHAR(1), VOLTAGEM FLOAT(9,2), POTENCIA_USO FLOAT(9,2), POTENCIA_STAND FLOAT(9,2), CAMINHO_FOTO TEXT )");
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
}
