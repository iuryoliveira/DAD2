package com.example.iury.gerenciadordeenergiaeltrica;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends  SQLiteOpenHelper {
    public static final String DATABASE_NAME = "energia.db";
    public static final String TABLE_NAME = "recurso";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "TIPO";
    public static final String COL_3 = "VOLTAGEM";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
//        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, TIPO CHAR(1), VOLTAGEM FLOAT(9,2) )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public Boolean inserData(String tipo, String voltagem){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, tipo);
        contentValues.put(COL_3, voltagem);

        Long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME, null);
        return res;
    }

    public Double getSum(){
        Double soma = 0.00;
        String sql = "select sum("+COL_3+") from "+TABLE_NAME;

        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            soma = cursor.getDouble(0);
        }

        cursor.close();
        return soma;
    }
}
