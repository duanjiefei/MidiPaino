package com.sky.musiclearn.ui;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.sky.musiclearn.R;
import com.sky.musiclearn.utils.MusicLearnConfig;

/**
 * Created by Sky000 on 2016/8/12.
 */
public class MidiPianoLayout extends FrameLayout{
    Context context;
    static  MidiPianoBottomLayout bottomLayout;
    MidiPianoTopLayout topLayout;
    public MidiPianoLayout(Context context){
        super(context);
        this.context = context;
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        //setOrientation(VERTICAL);
        setBackgroundResource(R.drawable.personal_model_bg);


        topLayout = new MidiPianoTopLayout(context);
        LayoutParams topLayoutParams = new LayoutParams( LayoutParams.MATCH_PARENT, 680);
        topLayout.setLayoutParams(topLayoutParams);
        addView(topLayout);

        bottomLayout = new MidiPianoBottomLayout(context);
        LayoutParams bottomLayoutParams = new LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT );
        bottomLayoutParams.topMargin = MusicLearnConfig.getResolutionValue(1080-300-24);
        bottomLayout.setLayoutParams(bottomLayoutParams);
        addView(bottomLayout);
    }
    public static ImageButton getImageButton(int index){
        return  bottomLayout.getImageButton(index);
    }
}
