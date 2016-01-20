package com.rubino.add2contentprovider.musica;

import android.content.ContentValues;
import android.database.Cursor;

import com.rubino.add2contentprovider.bd.Contrato;

/**
 * Created by marco on 18/01/2016.
 */
public class Interprete {
    private int id;
    private String nombreI;


    public Interprete() {
        this.id = 0;
        this.nombreI = "INTERPRETE GENERICO";
    }
    public Interprete(int id) {
        this.id = id;
        this.nombreI = "INTERPRETE GENERICO";
    }

    public Interprete(int id, String nombreI) {
        this.id = id;
        this.nombreI = nombreI;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreI() {
        return nombreI;
    }

    public void setNombreI(String nombreI) {
        this.nombreI = nombreI;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Interprete that = (Interprete) o;

        if (id != that.id) return false;
        return !(nombreI != null ? !nombreI.equals(that.nombreI) : that.nombreI != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nombreI != null ? nombreI.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "Interprete{" +
                "id=" + id +
                ", nombreI='" + nombreI + '\'' +
                '}';
    }


    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        cv.put(Contrato.TablaInterprete.NOMBREINTER,this.nombreI);

        return cv;
    }

    public void set(Cursor c){
        this.id = c.getInt(c.getColumnIndex(Contrato.TablaInterprete._ID));
        this.nombreI = c.getString(c.getColumnIndex(Contrato.TablaInterprete.NOMBREINTER));
    }
}
