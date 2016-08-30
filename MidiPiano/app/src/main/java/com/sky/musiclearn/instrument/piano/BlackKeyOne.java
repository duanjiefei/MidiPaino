package com.sky.musiclearn.instrument.piano;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.sky.musiclearn.R;
import com.sky.musiclearn.utils.MusicLearnConfig;


/**
 * Created by Sky000 on 2016/8/12.
 * BlackKeyOne：两个白键间夹一个黑键，即白键(左)+黑键+白键（右）的组合方式
 * BlackKeyOne 宽：30+5+30=65dp 高：300dp
 * 其中 白键 宽：30dp 高：300dp
 *      黑键 宽：15dp 高：200dp
 *      白键与白键间距离为：5dp
 *
 */
public class BlackKeyOne extends FrameLayout {
    Context context;
    ImageButton whiteKeyLeft;
    ImageButton blackKey;
    ImageButton whiteKeyRight;
    public BlackKeyOne(Context context){
        super(context);
        this.context = context;
        setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));

        whiteKeyLeft = new ImageButton(context);
        LayoutParams leftParams = new LayoutParams(MusicLearnConfig.getResolutionValue(36),
                MusicLearnConfig.getResolutionValue(300));
        whiteKeyLeft.setLayoutParams(leftParams);
        whiteKeyLeft.setBackgroundResource(R.drawable.whitekey_left_bg);
        addView(whiteKeyLeft);

        whiteKeyRight = new ImageButton(context);
        LayoutParams rightParams = new LayoutParams(MusicLearnConfig.getResolutionValue(36),
                MusicLearnConfig.getResolutionValue(300));
        rightParams.leftMargin = MusicLearnConfig.getResolutionValue(36);//父布局左边<---35
        whiteKeyRight.setLayoutParams(rightParams);
        whiteKeyRight.setBackgroundResource(R.drawable.whitekey_right_bg);
        addView(whiteKeyRight);

        blackKey = new ImageButton(context);
        LayoutParams blackKeyParams = new LayoutParams(MusicLearnConfig.getResolutionValue(18),
                MusicLearnConfig.getResolutionValue(180));
        blackKeyParams.leftMargin = MusicLearnConfig.getResolutionValue(27);//父布局左边<---20
        blackKey.setLayoutParams(blackKeyParams);
        blackKey.setBackgroundResource(R.drawable.blackkey_bg);
        addView(blackKey);

    }

    public ImageButton getImageButton(int index) {
        ImageButton imageButton = null;
        switch (index){
            case 21:
                imageButton = whiteKeyLeft;
                break;
            case 22:
                imageButton = blackKey;
                break;
            case 23:
                imageButton = whiteKeyRight;
            default:
                break;
        }
        return imageButton;
    }
}
