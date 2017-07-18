package com.plug.beasy;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by OSKAR on 04/07/2017.
 */

public class Configuracion extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(getApplicationContext());
    }
}
