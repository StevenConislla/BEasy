package com.plug.beasy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by OSKAR on 04/07/2017.
 */

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.plug.beasy.R;

public class CarnetAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        //private Context context;
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //Llamar el diseño en el cual vamos a tomar como estructura
        //view= LayoutInflater.from(context).inflate(R.layout.carnet_detalle,viewGroup,false);
        //Vinculamos las variables con nuestro diseño

        SimpleDraweeView ivImagen=(SimpleDraweeView)view.findViewById(R.id.ivCarnet);

        //Obtenemos la noticia seleccionada


        //Cambiamos los valores de los componentes

        //URI PARA CUALQUIER FORMA DISTINTA A DRAWABLLE
        //ivImagen.setImageDrawable(context.getResources().getDrawable(R.drawable.fondo));
        //ivImagen.setImageURI(Uri.parse("res:/"+R.mipmap.ic_launcher));
        //ivImagen.setImageURI(Uri.parse(""));
        //Retornamos el diseño a



        return view;  }
}
