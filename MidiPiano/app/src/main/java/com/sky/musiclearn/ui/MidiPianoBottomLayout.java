package com.sky.musiclearn.ui;

import android.content.Context;
import android.graphics.Color;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.sky.musiclearn.instrument.piano.KeyBoardView;
import com.sky.musiclearn.utils.MusicLearnConfig;

/**
 * Created by Sky000 on 2016/8/12.
 *
 */
public class MidiPianoBottomLayout extends FrameLayout{
    Context context;
    KeyBoardView keyboardView;

    public MidiPianoBottomLayout(Context context) {
        super(context);
        this.context = context;
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        setBackgroundColor(Color.TRANSPARENT);

        keyboardView = new KeyBoardView(context);
        LayoutParams keyboardViewParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
       // keyboardViewParams.topMargin = MusicLearnConfig.getResolutionValue(50);
        keyboardViewParams.leftMargin = MusicLearnConfig.getResolutionValue(24);
        keyboardView.setLayoutParams(keyboardViewParams);
        addView(keyboardView);

    }
    public ImageButton getImageButton(int index){
        return  keyboardView.getImageButton(index);
    }
}
