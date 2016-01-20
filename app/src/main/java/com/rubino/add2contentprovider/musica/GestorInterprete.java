package com.rubino.add2contentprovider.musica;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.rubino.add2contentprovider.bd.Ayudante;
import com.rubino.add2contentprovider.bd.Contrato;

import java.util.ArrayList;
import java.util.List;

public class GestorInterprete {

    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorInterprete(Context c){
        Log.v("SQLAAD","constructor del gestor de películas");
        abd = new Ayudante(c);
    }
    public void open() {
        bd = abd.getWritableDatabase();
    }
    public void openRead() {
        bd = abd.getReadableDatabase();
    }
    public void close() {
        abd.close();
    }

    public long insert(Interprete p) {
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaInterprete._ID, p.getId());
        valores.put(Contrato.TablaInterprete.NOMBREINTER, p.getNombreI());
        long id = bd.insert(Contrato.TablaInterprete.TABLA, null, valores);
        return id;
    }

    public int delete(Interprete r) {
        return delete(r.getId());
    }

    public int delete(long id){
        String condicion = Contrato.TablaInterprete._ID + " = ?";
        String[] argumentos = { id + "" };
        int cuenta = bd.delete(
                Contrato.TablaInterprete.TABLA, condicion, argumentos);
        return cuenta;
    }

    public int update(Interprete r){
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaInterprete.NOMBREINTER, r.getNombreI());
        String condicion = Contrato.TablaInterprete._ID + " = ?";
        String[] argumentos = { r.getNombreI() + "" };
        int cuenta = bd.update(Contrato.TablaInterprete.TABLA, valores,
                condicion, argumentos);
        return cuenta;
    }

    public Interprete getRow(Cursor c) {
        Interprete r = new Interprete();
        r.setId(c.getInt(c.getColumnIndex(Contrato.TablaInterprete._ID)));
        r.setNombreI(c.getString(c.getColumnIndex(Contrato.TablaInterprete.NOMBREINTER)));
        return r;
    }

    public Interprete getRow(long id) {
        Cursor c = getCursor("_id = ?", new String[]{id+""});
        return getRow(c);
    }

    public Cursor getCursor(){
        return getCursor(null, null);
    }

    public Cursor getCursor(String condicion, String[] parametros) {
        Cursor cursor = bd.query(
                Contrato.TablaInterprete.TABLA, null, condicion, parametros, null,
                null, Contrato.TablaInterprete.NOMBREINTER);
        return cursor;
    }

    public List<Interprete> select(String condicion, String[] parametros) {
        List<Interprete> la = new ArrayList<>();
        Cursor cursor = getCursor(condicion, parametros);
        Interprete p;
        while (cursor.moveToNext()) {
            p = getRow(cursor);
            la.add(p);
        }
        cursor.close();
        return la;
    }

    public List<Interprete> select() {
        return select(null,null);
    }

}