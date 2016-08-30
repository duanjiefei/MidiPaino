package com.sky.musiclearn.ui;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.sky.musiclearn.R;
import com.sky.musiclearn.utils.MusicLearnConfig;


/**
 * Created by Sky000 on 2016/8/18.
 */
public class SplashView extends FrameLayout{
    Context context;
    ImageView splashView1;

    public SplashView (Context context){
        super(context);
        setBackgroundResource(R.drawable.splash_bg_01);
        /*splashView1 = new ImageView(context);
        LayoutParams splashView1Params = new LayoutParams(MusicLearnConfig.getResolutionValue(1920),
                MusicLearnConfig.getResolutionValue(1080));
        splashView1.setLayoutParams(splashView1Params);
        splashView1.setBackgroundResource(R.drawable.splash_bg_01);
        addView(splashView1);*/
    }

}
