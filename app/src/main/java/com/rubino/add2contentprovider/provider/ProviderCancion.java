package com.rubino.add2contentprovider.provider;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.rubino.add2contentprovider.bd.Ayudante;
import com.rubino.add2contentprovider.bd.Contrato;

/**
 * Created by marco on 18/01/2016.
 */
public class ProviderCancion extends android.content.ContentProvider {

    //La Uri es cm una URL. LLegamos a un mismo sitio. UriMatcher establece si es un 1 haz una cosa, si es 2 haz otra cosa.
    public static final UriMatcher convierteUri2Int;
    public static final int CANCION = 1;
    public static final int CANCION_ID = 2;

    static{
        convierteUri2Int = new UriMatcher(UriMatcher.NO_MATCH);
        convierteUri2Int.addURI(Contrato.TablaCancion.AUTHORITY, Contrato.TablaCancion.TABLA, CANCION);//Le damos la instrucción de qué hacer a la URI
        convierteUri2Int.addURI(Contrato.TablaCancion.AUTHORITY, Contrato.TablaCancion.TABLA + "/#", CANCION_ID);
    }

    private Ayudante abd;

    @Override
    public boolean onCreate() {
        abd = new Ayudante(getContext());
        return true;
    }



    @Nullable
    @Override
    public String getType(Uri uri) {//Devuelve el tipo mime que corresponde a la uri con que se ha llamado
        switch (convierteUri2Int.match(uri)){
            case CANCION:
                return Contrato.TablaCancion.MULTIPLE_MIME;
            case CANCION_ID:
                return Contrato.TablaCancion.SINGLE_MIME;
            default:
                throw new IllegalArgumentException("Tipo de actividad desconocida " + uri);
        }
    }

    //METODO INSERT
    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // Comprobar que la uri utilizada para hacer la insercion es correcta
        if (convierteUri2Int.match(uri) != CANCION) {
            throw new IllegalArgumentException("URI desconocida : " + uri);//SI no es correcta la Uri
        }

        //Si el ContentValues es nulo, crea un ContentValues
        ContentValues contentValues;
        if (values == null) {
            throw new IllegalArgumentException("Cliente null ");
        }
        //Validar

        // Inserción de nueva fila
        SQLiteDatabase db = abd.getWritableDatabase();//Conectamos a la base de datos en modo escritura
        long rowId = db.insert(Contrato.TablaCancion.TABLA, null, values);
        if (rowId > 0) {
            //Si se ha insertado el elemento correctamente, entonces devolvemos la uri del elemento que se acaba de insertar
            Uri uri_actividad = ContentUris.withAppendedId(Contrato.TablaCancion.CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(uri_actividad, null);
            return uri_actividad;
        }
        throw new SQLException("Error al insertar fila en : " + uri);

    }

    //METODO BORRAR
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = abd.getWritableDatabase();// Vuelve a abrir la base de datos para conectar con ella en modo escritura
        int match = convierteUri2Int.match(uri);//Obtengo la uri
        int affected;
        switch (match) {
            case CANCION: //Muchos clientes
                affected = db.delete(Contrato.TablaCancion.TABLA,selection,selectionArgs);
                break;
            case CANCION_ID: //Un sólo cliente
                long idActividad = ContentUris.parseId(uri);
                affected = db.delete(Contrato.TablaCancion.TABLA, Contrato.TablaCancion._ID + "= ?" , new String [] {idActividad + ""});

                break;
            default:
                throw new IllegalArgumentException("Elemento actividad desconocido: " +
                        uri);
        }
        // Notificar cambio asociado a la urigetContext().getContentResolver().notifyChange(uri, null);
        getContext().getContentResolver().notifyChange(uri, null);
        return affected;//Devuelve el numero de filas borradas
    }



    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = abd.getWritableDatabase();
        int affected;
        switch (convierteUri2Int.match(uri)) {
            case CANCION:
                affected = db.update(Contrato.TablaCancion.TABLA, values, selection, selectionArgs);
                break;
            case CANCION_ID:
                String idActividad = uri.getPathSegments().get(1);
                affected = db.update(Contrato.TablaCancion.TABLA, values,
                        Contrato.TablaCancion._ID + "= ?" , new String [] {idActividad});
                break;
            default:
                throw new IllegalArgumentException("URI desconocida: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return affected;
    }


    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        // Obtener base de datos
        SQLiteDatabase db = abd.getReadableDatabase();
        // Comparar Uri
        int match = convierteUri2Int.match(uri);

        Cursor c;

        switch (match) {
            case CANCION:
                // Consultando todos los registros
                c = db.query(Contrato.TablaCancion.TABLA, projection, selection, selectionArgs, null, null, sortOrder);
                Log.v("Camino", "nos hemos metido por el camino del case CANCION_ID");
                break;
            case CANCION_ID:
                // Consultando un solo registro basado en el Id del Uri
                long idActividad = ContentUris.parseId(uri);
                c = db.query(Contrato.TablaCancion.TABLA, projection, Contrato.TablaCancion._ID + "= ? " , new String [] {idActividad + ""},
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("URI no soportada: " + uri);
        }
        c.setNotificationUri(getContext().getContentResolver(), Contrato.TablaCancion.CONTENT_URI);
        return c;
    }

}