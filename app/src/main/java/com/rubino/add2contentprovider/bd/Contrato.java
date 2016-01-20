package com.rubino.add2contentprovider.bd;

import android.net.Uri;
import android.provider.BaseColumns;


public class Contrato {

    private Contrato(){
    }

    /*Tabla interprete : id, nombre
    Tabla disco : id, nombre, idinterprete
    Tabla cancion: id, titulo, iddisco
    */

    public static abstract class TablaDisco implements BaseColumns {
        public static final String _ID = "_id";
        public static final String TABLA = "disco";
        public static final String NOMBREDISCO = "nombreDis";
        public static final String IDINTERPRETE = "idInterprete";

        //La autoridad es la cadena que identifica a qué contentprovider se llama.
        public final static String AUTHORITY = "com.rubino.add2contentprovider.provider.ProviderDisco";
        //Definir como llego a la tabla musica (a que tabla estoy llegando)
        public final static Uri CONTENT_URI =
                Uri.parse("content://" + AUTHORITY + "/" + TABLA);
        public final static String SINGLE_MIME =
                "vnd.android.cursor.item_song/vnd." + AUTHORITY + TABLA;
        public final static String MULTIPLE_MIME =
                "vnd.android.cursor.dir/vnd." + AUTHORITY + TABLA;
    }

    public static abstract class TablaCancion implements BaseColumns {
        public static final String _ID = "_id";
        public static final String TABLA = "cancion";
        public static final String NOMBRECANCION = "nombreCan";
        public static final String IDDISCO = "idDisco";

        //La autoridad es la cadena que identifica a qué contentprovider se llama.
        public final static String AUTHORITY = "com.rubino.add2contentprovider.provider.ProviderCancion";
        //Definir como llego a la tabla musica (a que tabla estoy llegando)
        public final static Uri CONTENT_URI =
                Uri.parse("content://" + AUTHORITY + "/" + TABLA);
        public final static String SINGLE_MIME =
                "vnd.android.cursor.item_song/vnd." + AUTHORITY + TABLA;
        public final static String MULTIPLE_MIME =
                "vnd.android.cursor.dir/vnd." + AUTHORITY + TABLA;
    }

    public static abstract class TablaInterprete implements BaseColumns {
        public static final String _ID = "_id";
        public static final String NOMBREINTER = "nombreInt";
        public static final String TABLA = "interprete";

        //La autoridad es la cadena que identifica a qué contentprovider se llama.
        public final static String AUTHORITY = "com.rubino.add2contentprovider.provider.ProviderInterprete";
        //Definir como llego a la tabla musica (a que tabla estoy llegando)
        public final static Uri CONTENT_URI =
                Uri.parse("content://" + AUTHORITY + "/" + TABLA);
        public final static String SINGLE_MIME =
                "vnd.android.cursor.item_song/vnd." + AUTHORITY + TABLA;
        public final static String MULTIPLE_MIME =
                "vnd.android.cursor.dir/vnd." + AUTHORITY + TABLA;
    }

}