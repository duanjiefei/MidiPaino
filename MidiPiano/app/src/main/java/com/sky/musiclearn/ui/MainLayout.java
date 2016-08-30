package com.sky.musiclearn.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.Toast;

import com.sky.musiclearn.CommpetionModeActivity;
import com.sky.musiclearn.MusicStarModeActivity;
import com.sky.musiclearn.PersonalModeActivity;
import com.sky.musiclearn.R;
import com.sky.musiclearn.RecordModeActivity;
import com.sky.musiclearn.ShareModeActivity;
import com.sky.musiclearn.StudyModelActivity;
import com.sky.musiclearn.utils.Debugger;
import com.sky.musiclearn.utils.MusicLearnConfig;

/**
 * Created by Sky000 on 2016/8/17.
 */
public class MainLayout extends FrameLayout{
    Context context;
    HorizontalScrollView modelSelectView;
    FrameLayout modelView;
    ImageView personalModel;
    ImageView studyModel;
    ImageView recordModel;
    ImageView collectModel;
    ImageView competitionModel;
    ImageView shareModel;


    public MainLayout(Context context){
        super(context);
        this.context = context;
        setBackgroundResource(R.drawable.musiclearn_bg);


        modelSelectView = new HorizontalScrollView(context);
        LayoutParams modelSelectParams = new LayoutParams(LayoutParams.MATCH_PARENT,
                MusicLearnConfig.getResolutionValue(1080-480));
        modelSelectParams.topMargin = MusicLearnConfig.getResolutionValue(150);
        modelSelectParams.leftMargin = MusicLearnConfig.getResolutionValue(40);
        modelSelectParams.rightMargin = MusicLearnConfig.getResolutionValue(40);
        modelSelectView.setLayoutParams(modelSelectParams);
        modelSelectView.setHorizontalScrollBarEnabled(false);
        addView(modelSelectView);

        modelView = new FrameLayout(context);
        LayoutParams modelViewParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        modelView.setBackgroundColor(Color.TRANSPARENT);
        modelView.setLayoutParams(modelViewParams);
        modelSelectView.addView(modelView);

        personalModel = new ImageView(context);
        LayoutParams personalModelParams = new LayoutParams(MusicLearnConfig.getResolutionValue(400),
                MusicLearnConfig.getResolutionValue(500));
        personalModel.setLayoutParams(personalModelParams);
        personalModel.setFocusable(true);
        personalModel.requestFocus();
        personalModel.setImageResource(R.drawable.model_personal_selector);
        modelView.addView(personalModel);

        studyModel = new ImageView(context);
        LayoutParams studyModelParams = new LayoutParams(MusicLearnConfig.getResolutionValue(400),
                MusicLearnConfig.getResolutionValue(500));
        studyModelParams.leftMargin = MusicLearnConfig.getResolutionValue(480);
        studyModel.setLayoutParams(studyModelParams);
        studyModel.setFocusable(true);
        studyModel.setBackgroundResource(R.drawable.model_study_selector);
        modelView.addView(studyModel);

        recordModel = new ImageView(context);
        LayoutParams recordingModelParams = new LayoutParams(MusicLearnConfig.getResolutionValue(400),
                MusicLearnConfig.getResolutionValue(500));
        recordingModelParams.leftMargin = MusicLearnConfig.getResolutionValue(960);
        recordModel.setLayoutParams(recordingModelParams);
        recordModel.setFocusable(true);
        recordModel.setBackgroundResource(R.drawable.model_record_selector);
        modelView.addView(recordModel);

        collectModel = new ImageView(context);
        LayoutParams managerModelParams = new LayoutParams(MusicLearnConfig.getResolutionValue(400),
                MusicLearnConfig.getResolutionValue(500));
        managerModelParams.leftMargin = MusicLearnConfig.getResolutionValue(1440);
        collectModel.setLayoutParams(managerModelParams);
        collectModel.setFocusable(true);
        collectModel.setBackgroundResource(R.drawable.model_collect_selector);
        modelView.addView(collectModel);

        competitionModel = new ImageView(context);
        LayoutParams competitionModelParams = new LayoutParams(MusicLearnConfig.getResolutionValue(400),
                MusicLearnConfig.getResolutionValue(500));
        competitionModelParams.leftMargin = MusicLearnConfig.getResolutionValue(1920);
        competitionModel.setLayoutParams(competitionModelParams);
        competitionModel.setFocusable(true);
        competitionModel.setBackgroundResource(R.drawable.model_competition_selector);
        modelView.addView(competitionModel);

        shareModel = new ImageView(context);
        LayoutParams shareModelParams = new LayoutParams(MusicLearnConfig.getResolutionValue(400),
                MusicLearnConfig.getResolutionValue(500));
        shareModelParams.leftMargin = MusicLearnConfig.getResolutionValue(2400);
        shareModel.setLayoutParams(shareModelParams);
        shareModel.setFocusable(true);
        shareModel.setBackgroundResource(R.drawable.model_share_selector);
        modelView.addView(shareModel);


        /***********/
        if(personalModel.isFocused()){
            Toast.makeText(context,"studyModel focused",Toast.LENGTH_SHORT).show();
        }
        setOnListener();
    }

    public ImageView getPersonalModelInstance(){
        return personalModel;
    }
    public ImageView getStudyModelInstance(){
        return studyModel;
    }


    private void setOnListener() {

        personalModel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startPersonalActivity();
            }
        });

        studyModel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startStudyModelActivity();
            }
        });
        collectModel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               startMusicStarActivity();
            }
        });
        recordModel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecordModelActivity();
            }
        });
        competitionModel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startCommpetionModelActivity();
            }
        });
        shareModel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startShareModeActivity();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_DPAD_CENTER:

               break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:

                break;

        }
        return super.onKeyDown(keyCode, event);
    }

    private void startStudyModelActivity(){
        Debugger.i("######## StudyModelActivity");
        Intent intent = new Intent();
        intent.setClass((Activity)context, StudyModelActivity.class);
        ((Activity)context).startActivity(intent);
    }
    private void startPersonalActivity(){
        Debugger.i("######## StudyModelActivity");
        Intent intent = new Intent();
        intent.setClass((Activity)context, PersonalModeActivity.class);
        ((Activity)context).startActivity(intent);
    }

    private void startRecordModelActivity(){
        Debugger.i("######## StudyModelActivity");
        Intent intent = new Intent();
        intent.setClass((Activity)context, RecordModeActivity.class);
        ((Activity)context).startActivity(intent);
    }
    private void startMusicStarActivity(){
        Debugger.i("######## StudyModelActivity");
        Intent intent = new Intent();
        intent.setClass((Activity)context, MusicStarModeActivity.class);
        ((Activity)context).startActivity(intent);
    }
    private void startCommpetionModelActivity(){
        Debugger.i("######## StudyModelActivity");
        Intent intent = new Intent();
        intent.setClass((Activity)context, CommpetionModeActivity.class);
        ((Activity)context).startActivity(intent);
    }
    private void startShareModeActivity(){
        Debugger.i("######## StudyModelActivity");
        Intent intent = new Intent();
        intent.setClass((Activity)context, ShareModeActivity.class);
        ((Activity)context).startActivity(intent);
    }

}
