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

public class GestorDisco {

    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorDisco(Context c){
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

    public long insert(Disco p) {
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaDisco._ID, p.getId());
        valores.put(Contrato.TablaDisco.NOMBREDISCO, p.getNombreD());
        valores.put(Contrato.TablaDisco.IDINTERPRETE, p.getIdInterprete());
        long id = bd.insert(Contrato.TablaDisco.TABLA, null, valores);
        return id;
    }

    public int delete(Disco r) {
        return delete(r.getId());
    }

    public int delete(long id){
        String condicion = Contrato.TablaDisco._ID + " = ?";
        String[] argumentos = { id + "" };
        int cuenta = bd.delete(
                Contrato.TablaDisco.TABLA, condicion, argumentos);
        return cuenta;
    }

    public int update(Disco r){
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaDisco.NOMBREDISCO, r.getNombreD());
        valores.put(Contrato.TablaDisco.IDINTERPRETE, r.getIdInterprete());
        String condicion = Contrato.TablaDisco._ID + " = ?";
        String[] argumentos = { r.getNombreD() + "" };
        int cuenta = bd.update(Contrato.TablaDisco.TABLA, valores,
                condicion, argumentos);
        return cuenta;
    }

    public Disco getRow(Cursor c) {
        Disco r = new Disco();
        r.setId(c.getInt(c.getColumnIndex(Contrato.TablaDisco._ID)));
        r.setNombreD(c.getString(c.getColumnIndex(Contrato.TablaDisco.NOMBREDISCO)));
        r.setIdInterprete(c.getInt(c.getColumnIndex(Contrato.TablaDisco.IDINTERPRETE)));
        return r;
    }

    public Disco getRow(long id) {
        Cursor c = getCursor("_id = ?", new String[]{id+""});
        return getRow(c);
    }

    public Cursor getCursor(){
        return getCursor(null, null);
    }

    public Cursor getCursor(String condicion, String[] parametros) {
        Cursor cursor = bd.query(
                Contrato.TablaDisco.TABLA, null, condicion, parametros, null,
                null, Contrato.TablaDisco.NOMBREDISCO);
        return cursor;
    }

    public List<Disco> select(String condicion, String[] parametros) {
        List<Disco> la = new ArrayList<>();
        Cursor cursor = getCursor(condicion, parametros);
        Disco p;
        while (cursor.moveToNext()) {
            p = getRow(cursor);
            la.add(p);
        }
        cursor.close();
        return la;
    }

    public List<Disco> select() {
        return select(null,null);
    }

}