package com.sky.musiclearn;

import android.app.Activity;
import android.os.Bundle;

import com.sky.musiclearn.ui.CommpetionModeView;

/**
 * Created by Sky000 on 2016/8/27.
 */
public class CommpetionModeActivity extends Activity {

    CommpetionModeView commpetionModeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commpetionModeView = new CommpetionModeView(this);
        setContentView(commpetionModeView);
    }
}
