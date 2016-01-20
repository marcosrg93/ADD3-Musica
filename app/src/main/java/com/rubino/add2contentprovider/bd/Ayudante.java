package com.rubino.add2contentprovider.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class Ayudante extends SQLiteOpenHelper{

    public static final String DATABASE_NAME ="musica.sqlite";
    public static final int DATABASE_VERSION = 1;

    public Ayudante(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {

        String sql="drop table if exists "
                + Contrato.TablaInterprete.TABLA;
        db.execSQL(sql);

        String sql1="drop table if exists "
                + Contrato.TablaDisco.TABLA;
        db.execSQL(sql1);

        String sql2="drop table if exists "
                + Contrato.TablaCancion.TABLA;
        db.execSQL(sql2);

        onCreate(db);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {//Cuando se baja la aplicación y se crea por primera vez(no hay versión previa de la aplicación)
        Log.v("AYUDANTEEEE","Creamos la base de datos");
        String sql,  sql1, sql2;
        sql="create table "+ Contrato.TablaInterprete.TABLA+ " ("+
                Contrato.TablaInterprete._ID+ " integer primary key autoincrement, "+
                Contrato.TablaInterprete.NOMBREINTER+" text)";
        Log.v("PRIMERA TABLA",sql);
        db.execSQL(sql);

        sql1="create table "+Contrato.TablaDisco.TABLA+ " ("+
                Contrato.TablaDisco._ID+ " integer primary key autoincrement, "+
                Contrato.TablaDisco.NOMBREDISCO+" text, "+
                Contrato.TablaDisco.IDINTERPRETE+" integer)";

        db.execSQL(sql1);
        Log.v("Segunda TABLA", sql1);


        sql2="create table "+Contrato.TablaCancion.TABLA+ " ("+
                Contrato.TablaCancion._ID+ " integer primary key autoincrement, "+
                Contrato.TablaCancion.NOMBRECANCION+" text, "+
                Contrato.TablaCancion.IDDISCO+" integer)";

        db.execSQL(sql2);
        Log.v("Tercera TABLA", sql2);


    }

}
