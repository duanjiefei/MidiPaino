package com.sky.musiclearn;

import android.app.Activity;
import android.os.Bundle;

import com.sky.musiclearn.ui.RecordModeView;

/**
 * Created by Sky000 on 2016/8/27.
 */
public class RecordModeActivity  extends Activity{
    RecordModeView recordView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recordView = new RecordModeView(this);
        setContentView(recordView);
    }
}
