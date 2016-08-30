package com.sky.musiclearn;

import android.app.Activity;
import android.os.Bundle;

import com.sky.musiclearn.ui.MidiPianoLayout;
import com.sky.musiclearn.utils.Debugger;

/**
 * Created by Sky000 on 2016/8/17.
 * 学习模式
 */
public class StudyModelActivity extends Activity{
    MidiPianoLayout midiPianoLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Debugger.i("########## StudyModelActivity is created");
        midiPianoLayout = new MidiPianoLayout(this);
        setContentView(midiPianoLayout);
    }
}
