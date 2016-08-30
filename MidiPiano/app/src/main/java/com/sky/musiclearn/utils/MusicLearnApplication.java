package com.sky.musiclearn.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by Sky000 on 2016/8/17.
 */
public class MusicLearnApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        MusicLearnConfig.setResolutionAndDpiDiv(context);
    }
}
