package com.rubino.add2contentprovider.Adaptadores;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.rubino.add2contentprovider.R;
import com.rubino.add2contentprovider.musica.Cancion;
import com.rubino.add2contentprovider.musica.Interprete;


/**
 * Created by ivan on 1/18/2016.
 */
public class AdaptadorCancion extends CursorAdapter {
    Interprete inter = new Interprete();
    public AdaptadorCancion(Context co, Cursor cu, Interprete inter) {
        super(co, cu, true);
        this.inter = inter;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater i = LayoutInflater.from(parent.getContext());
        View v = i.inflate(R.layout.item_song, parent, false);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvCan = (TextView)view.findViewById(R.id.tvCancion);
        TextView tvBy = (TextView)view.findViewById(R.id.tvInter);
        Cancion can = new Cancion();

        can.set(cursor);
        tvCan.setText(can.getTitulo());
        tvBy.setText(inter.getNombreI());


    }


}