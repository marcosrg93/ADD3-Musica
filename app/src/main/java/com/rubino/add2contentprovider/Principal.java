package com.rubino.add2contentprovider;



import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.rubino.add2contentprovider.Adaptadores.AdaptadorDisco;
import com.rubino.add2contentprovider.Adaptadores.AdaptadorInterprete;
import com.rubino.add2contentprovider.bd.Contrato;
import com.rubino.add2contentprovider.musica.Cancion;
import com.rubino.add2contentprovider.musica.Disco;
import com.rubino.add2contentprovider.musica.GestorCancion;
import com.rubino.add2contentprovider.musica.GestorDisco;
import com.rubino.add2contentprovider.musica.GestorInterprete;
import com.rubino.add2contentprovider.musica.Interprete;


public class Principal extends AppCompatActivity {

    private GestorCancion gc;
    private GestorDisco gd;
    private GestorInterprete gi;
    private ListView lv;
    private Cursor cursorDis;
    private Cursor cursorInter;
    private Uri uriC = Contrato.TablaCancion.CONTENT_URI;
    private Uri uriD = Contrato.TablaDisco.CONTENT_URI;
    private Uri uriI = Contrato.TablaInterprete.CONTENT_URI;
    private FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv= (ListView)findViewById(R.id.lv);
        init();

    }

    public void init(){
        //Abrimos los gestores de cada clase
        gc = new GestorCancion(this);
        gd = new GestorDisco(this);
        gi = new GestorInterprete(this);

        gc.open();
        gd.open();
        gi.open();
        pasarBd();

        cursorInter = getContentResolver().query(uriI, null,null,null,null);
        cursorDis = getContentResolver().query(uriD, null,null,null,null);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdaptadorInterprete adp = new AdaptadorInterprete(getApplicationContext(), cursorInter);
                lv.setAdapter(adp);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });

            }
        });

        AdaptadorDisco adp = new AdaptadorDisco(this, cursorDis);
        lv.setAdapter(adp);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                verCanciones(cursorDis);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        gc.open();
        gd.open();
        gi.open();
        Log.v("APLICACION", "Resume Alta Open");
    }

    @Override
    protected void onPause() {
        super.onPause();
        gc.close();
        gd.close();
        gi.close();
        Log.v("APLICACION", "Resume Alta Close");
    }




    public void verCanciones(Cursor c) {
        Intent i=new Intent(this,ListaCanciones.class);
        Disco x = new Disco();
        x.set(c);
        i.putExtra("id_disc", x.getId());
        startActivity(i);
    }

    public void pasarBd(){

        Cancion c = new Cancion();
        Disco d = new Disco();
        Interprete i = new Interprete();


        Cursor cur = getContentResolver().query(android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Audio.Media.IS_MUSIC + "= 1", null, null);
        while (cur.moveToNext()) {

            int idCancion = Integer.parseInt(cur.getString(0));
            int idInterprete = Integer.parseInt(cur.getString(11));
            int idDisco = Integer.parseInt(cur.getString(13));

            String can = cur.getString(2);
            String album = cur.getString(28);
            String interprete = cur.getString(22);

            //Metemos lo datos de la Cancion en la BD
            c.setId(idCancion);
            c.setTitulo(can);
            c.setIdDisco(idDisco);
            gc.insert(c);


            //Metemos lo datos  del disco en la BD
            d.setId(idDisco);
            d.setNombreD(album);
            d.setIdInterprete(idInterprete);
            gd.insert(d);

            //Metemos lo datos  del Interprete en la BD
            i.setId(idInterprete);
            i.setNombreI(interprete);
            gi.insert(i);


        }


        cur.close();


    }

}
