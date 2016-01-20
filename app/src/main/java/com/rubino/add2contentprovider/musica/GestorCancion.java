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

public class GestorCancion {

    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorCancion(Context c){
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

    public long insert(Cancion p) {
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaCancion._ID, p.getId());
        valores.put(Contrato.TablaCancion.NOMBRECANCION, p.getTitulo());
        valores.put(Contrato.TablaCancion.IDDISCO, p.getIdDisco());
        long id = bd.insert(Contrato.TablaCancion.TABLA, null, valores);
        return id;
    }

    public int delete(Cancion r) {
        return delete(r.getId());
    }

    public int delete(long id){
        String condicion = Contrato.TablaCancion._ID + " = ?";
        String[] argumentos = { id + "" };
        int cuenta = bd.delete(
                Contrato.TablaCancion.TABLA, condicion, argumentos);
        return cuenta;
    }

    public int update(Cancion r){
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaCancion.NOMBRECANCION, r.getTitulo());
        valores.put(Contrato.TablaCancion.IDDISCO, r.getIdDisco());
        String condicion = Contrato.TablaCancion._ID + " = ?";
        String[] argumentos = { r.getTitulo() + "" };
        int cuenta = bd.update(Contrato.TablaCancion.TABLA, valores,
                condicion, argumentos);
        return cuenta;
    }

    public Cancion getRow(Cursor c) {
        Cancion r = new Cancion();
        r.setId(c.getInt(c.getColumnIndex(Contrato.TablaCancion._ID)));
        r.setTitulo(c.getString(c.getColumnIndex(Contrato.TablaCancion.NOMBRECANCION)));
        r.setIdDisco(c.getInt(c.getColumnIndex(Contrato.TablaCancion.IDDISCO)));
        return r;
    }

    public Cancion getRow(long id) {
        Cursor c = getCursor("_id = ?", new String[]{id+""});
        return getRow(c);
    }

    public Cursor getCursor(){
        return getCursor(null, null);
    }

    public Cursor getCursor(String condicion, String[] parametros) {
        Cursor cursor = bd.query(
                Contrato.TablaCancion.TABLA, null, condicion, parametros, null,
                null, Contrato.TablaCancion.NOMBRECANCION);
        return cursor;
    }

    public List<Cancion> select(String condicion, String[] parametros) {
        List<Cancion> la = new ArrayList<>();
        Cursor cursor = getCursor(condicion, parametros);
        Cancion p;
        while (cursor.moveToNext()) {
            p = getRow(cursor);
            la.add(p);
        }
        cursor.close();
        return la;
    }

    public List<Cancion> select() {
        return select(null,null);
    }

}