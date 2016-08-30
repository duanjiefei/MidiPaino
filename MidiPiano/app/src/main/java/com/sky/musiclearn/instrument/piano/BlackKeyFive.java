package com.sky.musiclearn.instrument.piano;

import android.content.Context;
import android.graphics.Color;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.sky.musiclearn.utils.MusicLearnConfig;

/**
 * Created by Sky000 on 2016/8/14.
 * BlackKeyFive：BlackKeyTwo（3白键2黑键）与BlackKeyThree（4白键3黑键）的组合方式
 * BlackKeyFive 宽：100+5+135=240dp 高：300dp
 * 其中 BlackKeyTwo 宽：100dp
 *      BlackKeyThree 宽：135dp
 *      BlackKeyTwo与BlackKeyThree间距离为：5dp
 *
 */
public class BlackKeyFive extends FrameLayout{
    Context context;
    BlackKeyTwo blackKeyTwo;
    BlackKeyThree blackKeyThree;
    public BlackKeyFive(Context context) {
        super(context);
        this.context = context;
        setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        setBackgroundColor(Color.TRANSPARENT);

        blackKeyTwo = new BlackKeyTwo(context);
        FrameLayout.LayoutParams blackKeyTwoParams = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        blackKeyTwo.setLayoutParams(blackKeyTwoParams);
        addView(blackKeyTwo);

        blackKeyThree = new BlackKeyThree(context);
        FrameLayout.LayoutParams blackKeyThreeParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        blackKeyThreeParams.leftMargin = MusicLearnConfig.getResolutionValue(36+36+36);//父布局左边<---108
        blackKeyThree.setLayoutParams(blackKeyThreeParams);
        addView(blackKeyThree);

    }

    public ImageButton getImageButton(int index) {
        ImageButton imageButton = null;
        switch (index){
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:

            case 36:
            case 37:
            case 38:
            case 39:
            case 40:

            case 48:
            case 49:
            case 50:
            case 51:
            case 52:

            case 60:
            case 61:
            case 62:
            case 63:
            case 64:

            case 72:
            case 73:
            case 74:
            case 75:
            case 76:

            case 84:
            case 85:
            case 86:
            case 87:
            case 88:

            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
                imageButton =blackKeyTwo.getImageButton(index);
                break;
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:

            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:

            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:

            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:

            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            case 83:

            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:

            case 101:
            case 102:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
                imageButton =blackKeyThree.getImageButton(index);
                break;
            default:
                break;
        }
        return  imageButton;
    }
}
