package com.sky.musiclearn;

import android.app.Activity;
import android.os.Bundle;

import com.sky.musiclearn.ui.ShareModeView;

/**
 * Created by Sky000 on 2016/8/27.
 */
public class ShareModeActivity extends Activity {
ShareModeView shareModeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shareModeView = new ShareModeView(this);
        setContentView(shareModeView);
    }
}
