package com.sky.musiclearn.ui;

import android.content.Context;
import android.graphics.Color;
import android.widget.FrameLayout;

/**
 * Created by Sky000 on 2016/8/12.
 */
public class MidiPianoTopLayout extends FrameLayout {
    Context context;

    public MidiPianoTopLayout(Context context) {
        super(context);
        this.context = context;
        setBackgroundColor(Color.TRANSPARENT);
    }


}
