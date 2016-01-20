package com.rubino.add2contentprovider.musica;

import android.content.ContentValues;
import android.database.Cursor;

import com.rubino.add2contentprovider.bd.Contrato;

/**
 * Created by marco on 18/01/2016.
 */
public class Disco {

    private int id, idInterprete;
    private String nombreD;

    public Disco() {
        this.id = 0;
        this.idInterprete = 0;
        this.nombreD = "INTERPRETE GENERICO";
    }

    public Disco(int id, int idInterprete, String nombreD) {
        this.id = id;
        this.idInterprete = idInterprete;
        this.nombreD = nombreD;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdInterprete() {
        return idInterprete;
    }

    public void setIdInterprete(int idInterprete) {
        this.idInterprete = idInterprete;
    }

    public String getNombreD() {
        return nombreD;
    }

    public void setNombreD(String nombreD) {
        this.nombreD = nombreD;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Disco disco = (Disco) o;

        if (id != disco.id) return false;
        if (idInterprete != disco.idInterprete) return false;
        return !(nombreD != null ? !nombreD.equals(disco.nombreD) : disco.nombreD != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idInterprete;
        result = 31 * result + (nombreD != null ? nombreD.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Disco{" +
                "id=" + id +
                ", idInterprete=" + idInterprete +
                ", nombreD='" + nombreD + '\'' +
                '}';
    }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        cv.put(Contrato.TablaDisco.IDINTERPRETE,this.idInterprete);
        cv.put(Contrato.TablaDisco.NOMBREDISCO,this.nombreD);

        return cv;
    }

    public void set(Cursor c){ //A partir del cursor recuperar nombre, apellido y telefono
        this.id = c.getInt(c.getColumnIndex(Contrato.TablaDisco._ID));
        this.idInterprete = c.getInt(c.getColumnIndex(Contrato.TablaDisco.IDINTERPRETE));
        this.nombreD= c.getString(c.getColumnIndex(Contrato.TablaDisco.NOMBREDISCO));

    }
}
