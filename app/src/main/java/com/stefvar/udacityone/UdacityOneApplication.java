package com.stefvar.udacityone;

import android.app.Application;

import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.fonts.IoniconsModule;
import com.joanzapata.iconify.fonts.MaterialCommunityModule;
import com.joanzapata.iconify.fonts.MaterialModule;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.NoEncryption;

/**
 * Created by steve62742 on 5/2/2017.
 */

public class UdacityOneApplication extends Application {


    @Override public void onCreate() {
        super.onCreate();
        Iconify
                .with(new FontAwesomeModule())
                .with(new IoniconsModule())
                .with(new MaterialModule())
                .with(new MaterialCommunityModule())
        ;
        // setup default typefaces
        TypefaceProvider.registerDefaultIconSets();

        Hawk.init(this)
                .setEncryption(new NoEncryption())
                .build();
    }

}
