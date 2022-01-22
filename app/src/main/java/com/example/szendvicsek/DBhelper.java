package com.example.szendvicsek;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "szendvicsek.db";
    public static final int DB_VERSION = 1;
    public static final String SZENDVICSEK_TABLA = "szendvicsek";

    public static final String COL_ID = "id";
    public static final String COL_NEV = "nev";
    public static final String COL_LEIRAS = "leiras";
    public static final String COL_ELKESZITES = "elkeszites";
    public static final String COL_AR = "ar";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + SZENDVICSEK_TABLA + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NEV + " VARCHAR(255) UNIQUE NOT NULL, " +
                COL_LEIRAS + " VARCHAR(255) NOT NULL, " +
                COL_ELKESZITES + " INTEGER NOT NULL, " +
                COL_AR + " INTEGER NOT NULL" +
                ")";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + SZENDVICSEK_TABLA;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public DBhelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    public boolean adatRogzites(String nev, String leiras, String elkeszites, String ar){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NEV, nev);
        values.put(COL_LEIRAS, leiras);
        values.put(COL_ELKESZITES, elkeszites);
        values.put(COL_AR, ar);
        return db.insert(SZENDVICSEK_TABLA, null, values) != -1;
    }

    public Cursor adatkerdezes(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(SZENDVICSEK_TABLA, new String[]{COL_ID,COL_NEV,COL_LEIRAS,COL_ELKESZITES,COL_AR},
                null,null,null,null,null);
    }
}
