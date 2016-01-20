package com.rubino.add2contentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.rubino.add2contentprovider.Adaptadores.AdaptadorCancion;
import com.rubino.add2contentprovider.bd.Contrato;
import com.rubino.add2contentprovider.musica.Interprete;

public class ListaCanciones extends AppCompatActivity {

    private ListView lv;
    private AdaptadorCancion adp;
    private Uri uriC = Contrato.TablaCancion.CONTENT_URI;
    private Uri uriD = Contrato.TablaDisco.CONTENT_URI;
    private Uri uriI = Contrato.TablaInterprete.CONTENT_URI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_canciones);
        init();
    }

    public void init(){
        lv = (ListView)findViewById(R.id.listaCan);

        String mSelectionClause =   Contrato.TablaCancion.IDDISCO+" = ?";
        String [] selectionArgs = {getIntent().getExtras().getInt("id_disc")+""};
        final Cursor cam = getContentResolver().query(uriC, null, mSelectionClause,selectionArgs,null);

        adp = new AdaptadorCancion(this,cam, nombreInterprete());
        lv.setAdapter(adp);
    }
    public Interprete nombreInterprete() {

        String mSelectionClause = Contrato.TablaDisco._ID + " = ?";
        String[] selectionArgs = {getIntent().getExtras().getInt("id_disc") + ""};
        Cursor cam = getContentResolver().query(uriD, null, mSelectionClause, selectionArgs, null);

        int idInt = 0;

        while (cam.moveToNext()) {
            idInt = cam.getInt(2);

        }

        mSelectionClause = Contrato.TablaInterprete._ID + " = ?";
        String[] selectionArgs2 = {idInt + ""};
        cam = getContentResolver().query(uriI, null, mSelectionClause, selectionArgs2, null);
        Interprete inter = new Interprete();
        while (cam.moveToNext()) {
            inter.setNombreI(cam.getString(1));
            Log.v("aa", inter.toString());
        }
        Log.v("GGG", inter.toString());

        return inter;

    }
}
