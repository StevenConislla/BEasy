package com.plug.beasy;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

/**
 * Created by OSKAR on 04/07/2017.
 */

public class Configuracion extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        /*ImagePipelineConfig config = ImagePipelineConfig.newBuilder(getApplicationContext())
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(getApplicationContext(), config);*/
        Fresco.initialize(this);
    }
}
