package com.sky.musiclearn.instrument.piano;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.sky.musiclearn.utils.Debugger;
import com.sky.musiclearn.utils.MusicLearnApplication;
import com.sky.musiclearn.utils.MusicLearnConfig;

/**
 * Created by Sky000 on 2016/8/17.
 * KeyBoardView;钢琴键盘布局
 * 宽：36*52=1872 dp
 * 高：300 dp
 */
public class KeyBoardView extends FrameLayout {
    Context context;
    BlackKeyOne blackKeyOne1;;
    BlackKeyFive blackKeyFive1,blackKeyFive2,blackKeyFive3,blackKeyFive4,blackKeyFive5,blackKeyFive6,blackKeyFive7;
    BlackKeyZero blackKeyZero;//长方形纯白键
    ImageView noteImageView[] = new ImageView[52];
    int noteImageId[] =  new int[52];
    ImageView musicNote01,musicNote02,musicNote03,musicNote04,musicNote05;
    public KeyBoardView(Context context){
        super(context);
        this.context = context;
        Debugger.i("########## KeyBoardView is added");

        blackKeyOne1 = new BlackKeyOne(context);
        LayoutParams blackKeyOne1Params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        blackKeyOne1.setLayoutParams(blackKeyOne1Params);
        addView(blackKeyOne1);

        blackKeyFive1 = new BlackKeyFive(context);
        LayoutParams blackKeyFive1Params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        blackKeyFive1Params.leftMargin = MusicLearnConfig.getResolutionValue(72);//父布局左边<---72
        blackKeyFive1.setLayoutParams(blackKeyFive1Params);
        addView(blackKeyFive1);

        blackKeyFive2 = new BlackKeyFive(context);
        LayoutParams blackKeyFive2Params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        blackKeyFive2Params.leftMargin = MusicLearnConfig.getResolutionValue(72+252);//父布局左边<---324
        blackKeyFive2.setLayoutParams(blackKeyFive2Params);
        addView(blackKeyFive2);

        blackKeyFive3 = new BlackKeyFive(context);
        LayoutParams blackKeyFive3Params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        blackKeyFive3Params.leftMargin = MusicLearnConfig.getResolutionValue(324+252);//父布局左边<---576
        blackKeyFive3.setLayoutParams(blackKeyFive3Params);
        addView(blackKeyFive3);

        blackKeyFive4 = new BlackKeyFive(context);
        LayoutParams blackKeyFive4Params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        blackKeyFive4Params.leftMargin = MusicLearnConfig.getResolutionValue(576+252);//父布局左边<---828
        blackKeyFive4.setLayoutParams(blackKeyFive4Params);
        addView(blackKeyFive4);

        blackKeyFive5 = new BlackKeyFive(context);
        LayoutParams blackKeyFive5Params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        blackKeyFive5Params.leftMargin = MusicLearnConfig.getResolutionValue(828+252);//父布局左边<---1080
        blackKeyFive5.setLayoutParams(blackKeyFive5Params);
        addView(blackKeyFive5);

        blackKeyFive6 = new BlackKeyFive(context);
        LayoutParams blackKeyFive6Params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        blackKeyFive6Params.leftMargin = MusicLearnConfig.getResolutionValue(1080+252);//父布局左边<---1332
        blackKeyFive6.setLayoutParams(blackKeyFive6Params);
        addView(blackKeyFive6);

        blackKeyFive7 = new BlackKeyFive(context);
        LayoutParams blackKeyFive7Params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        blackKeyFive7Params.leftMargin = MusicLearnConfig.getResolutionValue(1332+252);//父布局左边<---1584
        blackKeyFive7.setLayoutParams(blackKeyFive7Params);
        addView(blackKeyFive7);

        blackKeyZero = new BlackKeyZero(context);
        LayoutParams blackKeyZeroParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        blackKeyZeroParams.leftMargin = MusicLearnConfig.getResolutionValue(1584+252);//父布局左边<---1836
        blackKeyZero.setLayoutParams(blackKeyZeroParams);
        addView(blackKeyZero);

        addNoteImageView();
    }

    public void addNoteImageView(){
        int i = 0;
        for(i=0; i<52; i++){
            int index = i+1;
            noteImageId[i] = context.getResources().getIdentifier("music_note_"+ index,
                    "drawable",context.getPackageName());
            noteImageView[i] = new ImageView(context);
            LayoutParams noteImageParams = new LayoutParams(MusicLearnConfig.getResolutionValue(24),
                    MusicLearnConfig.getResolutionValue(24));
            noteImageParams.topMargin = MusicLearnConfig.getResolutionValue(270);
            noteImageParams.leftMargin = MusicLearnConfig.getResolutionValue(6+36*i);
            noteImageView[i].setLayoutParams(noteImageParams);
            noteImageView[i].setImageResource(noteImageId[i]);
            addView(noteImageView[i]);
        }
    }


    public ImageButton getImageButton(int index){
        ImageButton imageButton = null;
        switch (index){
            case 21:
            case 22:
            case 23:
                imageButton = blackKeyOne1.getImageButton(index);
                break;

            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
                imageButton = blackKeyFive1.getImageButton(index);
                 break;

            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
                imageButton = blackKeyFive2.getImageButton(index);
                break;
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
                imageButton = blackKeyFive3.getImageButton(index);
                break;

            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
                imageButton = blackKeyFive4.getImageButton(index);
                break;

            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            case 83:
                imageButton = blackKeyFive5.getImageButton(index);
                break;

            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
                imageButton = blackKeyFive6.getImageButton(index);
                break;
            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
            case 102:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
                imageButton = blackKeyFive7.getImageButton(index);
                break;
            case 108:
                imageButton = blackKeyZero.getWhitekey();
                break;
        }
        return imageButton;

    }

}
