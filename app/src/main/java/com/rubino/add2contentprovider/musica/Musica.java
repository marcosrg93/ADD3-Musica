package com.rubino.add2contentprovider.musica;

/**
 * Created by marco on 18/01/2016.
 */
public class Musica {
    private int idDisco, idCancion, idInterprete;
    private String cancion,album, interprete;

    public Musica() {
        this.idDisco = 0;
        this.idCancion = 0;
        this.idInterprete = 0;
        this.cancion = "" ;
        this.album = "";
        this.interprete = "";
    }

    public Musica(int idDisco, int idCancion, int idInterprete, String cancion, String album, String interprete) {
        this.idDisco = idDisco;
        this.idCancion = idCancion;
        this.idInterprete = idInterprete;
        this.cancion = cancion;
        this.album = album;
        this.interprete = interprete;
    }

    public int getIdDisco() {
        return idDisco;
    }

    public void setIdDisco(int idDisco) {
        this.idDisco = idDisco;
    }

    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    public int getIdInterprete() {
        return idInterprete;
    }

    public void setIdInterprete(int idInterprete) {
        this.idInterprete = idInterprete;
    }

    public String getCancion() {
        return cancion;
    }

    public void setCancion(String cancion) {
        this.cancion = cancion;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getInterprete() {
        return interprete;
    }

    public void setInterprete(String interprete) {
        this.interprete = interprete;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Musica musica = (Musica) o;

        if (idDisco != musica.idDisco) return false;
        if (idCancion != musica.idCancion) return false;
        if (idInterprete != musica.idInterprete) return false;
        if (cancion != null ? !cancion.equals(musica.cancion) : musica.cancion != null)
            return false;
        if (album != null ? !album.equals(musica.album) : musica.album != null) return false;
        return !(interprete != null ? !interprete.equals(musica.interprete) : musica.interprete != null);

    }

    @Override
    public int hashCode() {
        int result = idDisco;
        result = 31 * result + idCancion;
        result = 31 * result + idInterprete;
        result = 31 * result + (cancion != null ? cancion.hashCode() : 0);
        result = 31 * result + (album != null ? album.hashCode() : 0);
        result = 31 * result + (interprete != null ? interprete.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Musica{" +
                "idDisco=" + idDisco +
                ", idCancion=" + idCancion +
                ", idInterprete=" + idInterprete +
                ", cancion='" + cancion + '\'' +
                ", album='" + album + '\'' +
                ", interprete='" + interprete + '\'' +
                '}';
    }
}
