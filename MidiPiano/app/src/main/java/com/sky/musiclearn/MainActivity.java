package com.sky.musiclearn;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.sky.musiclearn.ui.MainLayout;

public class MainActivity extends Activity {
    String TAG = "MIDI";
    MainLayout MainLayout;
    ImageView studyModel;
    ImageView teachModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainLayout = new MainLayout(this);
        setContentView(MainLayout);

        studyModel = MainLayout.getStudyModelInstance();


    }



}
