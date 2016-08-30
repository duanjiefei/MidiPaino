package com.sky.musiclearn;

import android.app.Activity;
import android.os.Bundle;

import com.sky.musiclearn.ui.MusicStarModeView;

/**
 * Created by Sky000 on 2016/8/27.
 */
public class MusicStarModeActivity extends Activity{

    MusicStarModeView musicStarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        musicStarView = new MusicStarModeView(this);
        setContentView(musicStarView);
    }
}
