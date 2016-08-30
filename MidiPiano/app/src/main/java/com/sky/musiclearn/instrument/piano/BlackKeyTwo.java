package com.sky.musiclearn.instrument.piano;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.sky.musiclearn.R;
import com.sky.musiclearn.utils.MusicLearnConfig;

/**
 * Created by Sky000 on 2016/8/12.
 * BlackKeyTwo：三个白键间夹两个黑键，即白键(左)+黑键+白键（中）+黑键+白键（右）的组合方式
 * BlackKeyTwo 宽：30+5+30+5+30=100dp 高：300dp
 * 其中 白键 宽：30dp 高：300dp
 *      黑键 宽：15dp 高：200dp
 *      白键与白键间距离为：5dp
 */
public class BlackKeyTwo extends FrameLayout {
    Context context;
    ImageButton whiteKeyLeft;
    ImageButton blackKey1;
    ImageButton whiteKeyMiddle;
    ImageButton blackKey2;
    ImageButton whiteKeyRight;
    public BlackKeyTwo(Context context){
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

        whiteKeyMiddle = new ImageButton(context);
        LayoutParams middleParams = new LayoutParams(MusicLearnConfig.getResolutionValue(36),
                MusicLearnConfig.getResolutionValue(300));
        middleParams.leftMargin = MusicLearnConfig.getResolutionValue(36);//父布局左边<---35
        whiteKeyMiddle.setLayoutParams(middleParams);
        whiteKeyMiddle.setBackgroundResource(R.drawable.whitekey_middle_bg);
        addView(whiteKeyMiddle);

        whiteKeyRight = new ImageButton(context);
        LayoutParams rightParams = new LayoutParams(MusicLearnConfig.getResolutionValue(36),
                MusicLearnConfig.getResolutionValue(300));
        rightParams.leftMargin = MusicLearnConfig.getResolutionValue(36+36);//父布局左边<---70
        whiteKeyRight.setLayoutParams(rightParams);
        whiteKeyRight.setBackgroundResource(R.drawable.whitekey_right_bg);
        addView(whiteKeyRight);

        blackKey1 = new ImageButton(context);
        LayoutParams blackKey1Params = new LayoutParams(MusicLearnConfig.getResolutionValue(18),
                MusicLearnConfig.getResolutionValue(180));
        blackKey1Params.leftMargin = MusicLearnConfig.getResolutionValue(27);//父布局左边<---20
        blackKey1.setLayoutParams(blackKey1Params);
        blackKey1.setBackgroundResource(R.drawable.blackkey_bg);
        addView(blackKey1);

        blackKey2 = new ImageButton(context);
        LayoutParams blackKey2Params = new LayoutParams(MusicLearnConfig.getResolutionValue(18),
                MusicLearnConfig.getResolutionValue(180));
        blackKey2Params.leftMargin = MusicLearnConfig.getResolutionValue(63);//父布局左边<---45
        blackKey2.setLayoutParams(blackKey2Params);
        blackKey2.setBackgroundResource(R.drawable.blackkey_bg);
        addView(blackKey2);

    }

    public ImageButton getImageButton(int index) {
        ImageButton imageButton = null;
        switch (index){
            case 24:
            case 36:
            case 48:
            case 60:
            case 72:
            case 84:
            case 96:
                imageButton = whiteKeyLeft;
                break;
            case 25:
            case 37:
            case 49:
            case 61:
            case 73:
            case 85:
            case 97:
                imageButton = blackKey1;
                break;
            case 26:
            case 38:
            case 50:
            case 62:
            case 74:
            case 86:
            case 98:
                imageButton = whiteKeyMiddle;
                break;
            case 27:
            case 39:
            case 51:
            case 63:
            case 75:
            case 87:
            case 99:
                imageButton = blackKey2;
                break;
            case 28:
            case 40:
            case 52:
            case 64:
            case 76:
            case 88:
            case 100:
                imageButton = whiteKeyRight;
                break;
            default:
                break;
        }
        return imageButton;

    }
}
