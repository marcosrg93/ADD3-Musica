package com.rubino.add2contentprovider.musica;

import android.content.ContentValues;
import android.database.Cursor;

import com.rubino.add2contentprovider.bd.Contrato;

/**
 * Created by marco on 18/01/2016.
 */
public class Cancion {

    private int id, idDisco;
    private String titulo;

    public Cancion() {
        this.id = 0;
        this.idDisco = 0;
        this.titulo = "TITULO GENERICO";
    }

    public Cancion(int id, int idDisco, String titulo) {
        this.id = id;
        this.idDisco = idDisco;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDisco() {
        return idDisco;
    }

    public void setIdDisco(int idDisco) {
        this.idDisco = idDisco;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cancion cancion = (Cancion) o;

        if (id != cancion.id) return false;
        if (idDisco != cancion.idDisco) return false;
        return !(titulo != null ? !titulo.equals(cancion.titulo) : cancion.titulo != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idDisco;
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "id=" + id +
                ", idDisco=" + idDisco +
                ", titulo='" + titulo + '\'' +
                '}';
    }


    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        cv.put(Contrato.TablaCancion.IDDISCO,this.idDisco);
        cv.put(Contrato.TablaCancion.NOMBRECANCION,this.titulo);

        return cv;
    }

    public void set(Cursor c){ //A partir del cursor recuperar nombre, apellido y telefono
        this.id = c.getInt(c.getColumnIndex(Contrato.TablaCancion._ID));
        this.idDisco = c.getInt(c.getColumnIndex(Contrato.TablaCancion.IDDISCO));
        this.titulo= c.getString(c.getColumnIndex(Contrato.TablaCancion.NOMBRECANCION));

    }
}
