package com.sky.musiclearn.instrument.piano;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.sky.musiclearn.R;
import com.sky.musiclearn.utils.MusicLearnConfig;

/**
 * Created by Sky000 on 2016/8/12.
 * BlackKeyZero:只有一个长方形纯白键
 * 宽：30dp 高：300dp
 */
public class BlackKeyZero extends FrameLayout {
    Context context;
    ImageButton whitekey;
    public BlackKeyZero(Context context){
        super(context);
        this.context = context;

        setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        whitekey = new ImageButton(context);
        LayoutParams whitekeyParams = new LayoutParams(MusicLearnConfig.getResolutionValue(36),
                MusicLearnConfig.getResolutionValue(300));
        whitekey.setBackgroundResource(R.drawable.whitekey_bg);
        whitekey.setLayoutParams(whitekeyParams);
        addView(whitekey);
    }
    public ImageButton getWhitekey(){
        return whitekey;
    }
}
