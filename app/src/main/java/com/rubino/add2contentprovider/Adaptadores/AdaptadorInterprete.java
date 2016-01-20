package com.rubino.add2contentprovider.Adaptadores;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.rubino.add2contentprovider.R;
import com.rubino.add2contentprovider.musica.Interprete;


/**
 * Created by ivan on 1/18/2016.
 */
public class AdaptadorInterprete extends CursorAdapter {

    public AdaptadorInterprete(Context co, Cursor cu) {
        super(co, cu, true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater i = LayoutInflater.from(parent.getContext());
        View v = i.inflate(R.layout.item_inter, parent, false);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvdisco = (TextView)view.findViewById(R.id.tvInterpreteN);
        Interprete m = new Interprete();
        m.set(cursor);

        tvdisco.setText(m.getNombreI());


    }

}
