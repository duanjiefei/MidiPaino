package com.sky.musiclearn.instrument.piano;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.sky.musiclearn.R;
import com.sky.musiclearn.utils.MusicLearnConfig;

/**
 * Created by Sky000 on 2016/8/12.
 */
public class BlackKeyThree extends FrameLayout {
    Context context;
    ImageButton whiteKeyLeft;
    ImageButton blackKey1;
    ImageButton whiteKeyMiddle1;
    ImageButton blackKey2;
    ImageButton whiteKeyMiddle2;
    ImageButton blackKey3;
    ImageButton whiteKeyRight;
    public BlackKeyThree(Context context){
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

        whiteKeyMiddle1 = new ImageButton(context);
        LayoutParams middle1Params = new LayoutParams(MusicLearnConfig.getResolutionValue(36),
                MusicLearnConfig.getResolutionValue(300));
        middle1Params.leftMargin = MusicLearnConfig.getResolutionValue(36);//父布局左边<---36
        whiteKeyMiddle1.setLayoutParams(middle1Params);
        whiteKeyMiddle1.setBackgroundResource(R.drawable.whitekey_middle_bg);
        addView(whiteKeyMiddle1);

        whiteKeyMiddle2 = new ImageButton(context);
        LayoutParams middle2Params = new LayoutParams(MusicLearnConfig.getResolutionValue(36),
                MusicLearnConfig.getResolutionValue(300));
        middle2Params.leftMargin = MusicLearnConfig.getResolutionValue(36+36);//父布局左边<---72
        whiteKeyMiddle2.setLayoutParams(middle2Params);
        whiteKeyMiddle2.setBackgroundResource(R.drawable.whitekey_middle_bg);
        addView(whiteKeyMiddle2);

        whiteKeyRight = new ImageButton(context);
        LayoutParams rightParams = new LayoutParams(MusicLearnConfig.getResolutionValue(36),
                MusicLearnConfig.getResolutionValue(300));
        rightParams.leftMargin = MusicLearnConfig.getResolutionValue(72+36);//父布局左边<---108
        whiteKeyRight.setLayoutParams(rightParams);
        whiteKeyRight.setBackgroundResource(R.drawable.whitekey_right_bg);
        addView(whiteKeyRight);

        blackKey1 = new ImageButton(context);
        LayoutParams blackKey1Params = new LayoutParams(MusicLearnConfig.getResolutionValue(18),
                MusicLearnConfig.getResolutionValue(180));
        blackKey1Params.leftMargin = MusicLearnConfig.getResolutionValue(27);//父布局左边<---27
        blackKey1.setLayoutParams(blackKey1Params);
        blackKey1.setBackgroundResource(R.drawable.blackkey_bg);
        addView(blackKey1);

        blackKey2 = new ImageButton(context);
        LayoutParams blackKey2Params = new LayoutParams(MusicLearnConfig.getResolutionValue(18),
                MusicLearnConfig.getResolutionValue(180));
        blackKey2Params.leftMargin = MusicLearnConfig.getResolutionValue(36+27);//父布局左边<---63
        blackKey2.setLayoutParams(blackKey2Params);
        blackKey2.setBackgroundResource(R.drawable.blackkey_bg);
        addView(blackKey2);

        blackKey3 = new ImageButton(context);
        LayoutParams blackKey3Params = new LayoutParams(MusicLearnConfig.getResolutionValue(18),
                MusicLearnConfig.getResolutionValue(180));
        blackKey3Params.leftMargin = MusicLearnConfig.getResolutionValue(72+27);//父布局左边<---99
        blackKey3.setLayoutParams(blackKey3Params);
        blackKey3.setBackgroundResource(R.drawable.blackkey_bg);
        addView(blackKey3);

    }

    public ImageButton getImageButton(int index) {
        ImageButton imageButton = null;
        switch (index){

            case 29:
            case 41:
            case 53:
            case 65:
            case 77:
            case 89:
            case 101:
                imageButton = whiteKeyLeft;
                break;
            case 30:
            case 42:
            case 54:
            case 66:
            case 78:
            case 90:
            case 102:
                imageButton = blackKey1;
                break;
            case 31:
            case 43:
            case 55:
            case 67:
            case 79:
            case 91:
            case 103:
                imageButton = whiteKeyMiddle1;
                break;
            case 32:
            case 44:
            case 56:
            case 68:
            case 80:
            case 92:
            case 104:
                imageButton = blackKey2;
                break;
            case 33:
            case 45:
            case 57:
            case 69:
            case 81:
            case 93:
            case 105:
                imageButton = whiteKeyMiddle2;
                break;
            case 34:
            case 46:
            case 58:
            case 70:
            case 82:
            case 94:
            case 106:
                imageButton = blackKey3;
                break;
            case 35:
            case 47:
            case 59:
            case 71:
            case 83:
            case 95:
            case 107:
                imageButton = whiteKeyRight;
                break;
            default:
                break;
       }
        return  imageButton;
    }
}
