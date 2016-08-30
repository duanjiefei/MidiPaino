package com.sky.musiclearn;

/**
 * Created by Sky000 on 2016/8/18.
 */

import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.midi.MidiDevice;
import android.media.midi.MidiDeviceInfo;
import android.media.midi.MidiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.sky.musiclearn.synth.MidiOutputPortConnectionSelector;
import com.sky.musiclearn.synth.MidiPortConnector;
import com.sky.musiclearn.synth.MidiTools;
import com.sky.musiclearn.ui.MidiPianoLayout;
import com.sky.musiclearn.utils.Debugger;


/**
 * Created by Sky000 on 2016/8/17.
 */
public class PersonalModeActivity extends Activity {

    static final String TAG = "MidiSynthExample";
    MidiPianoLayout midiPianoLayout;
    private MidiManager mMidiManager;
    private MidiOutputPortConnectionSelector mPortSelector;
    private  static final int RECEIVE = 1;
    public  static Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case  RECEIVE:
                    byte[] data = (byte[])msg.obj;
                    int keyNum = data[1];     //代表那个按键被按下
                    int keyStatus = data[2];  //代表按键被按下的状态  127代表按键被按下   0 表示按键抬起
                    chageKeyBoard(keyNum,keyStatus);
                    Debugger.i("PersonalModeActivity","data[1]=="+data[1]+"data[2]=="+data[2]);
                    break;
                default:
                    break;
               }
            return false;
        }
    });


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        midiPianoLayout = new MidiPianoLayout(this);
        setContentView(midiPianoLayout);
        Debugger.i(TAG,"oncreate");
        Log.i(TAG,"setupMidi start");
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_MIDI)) {
            setupMidi();
            Log.i(TAG,"setupMidi sucessfull");
        }
        Log.i(TAG,"setupMidi end");

        Debugger.i("PersonalModeActivity","Thread.currentThread()=="+Thread.currentThread());

    }

    private void setupMidi() {
        // Setup MIDI
        mMidiManager = (MidiManager) getSystemService(MIDI_SERVICE);

        MidiDeviceInfo synthInfo = MidiTools.findDevice(mMidiManager, "AndroidTest",
                "SynthExample");
        int portIndex = 0;
        mPortSelector = new MidiOutputPortConnectionSelector(mMidiManager, this, synthInfo, portIndex);
        mPortSelector.setConnectedListener(new MyPortsConnectedListener());
    }

    private void closeSynthResources() {
        if (mPortSelector != null) {
            mPortSelector.close();
        }
    }

    // TODO A better way would be to listen to the synth server
    // for open/close events and then disable/enable the spinner.
    private class MyPortsConnectedListener
            implements MidiPortConnector.OnPortsConnectedListener {
        @Override
        public void onPortsConnected(final MidiDevice.MidiConnection connection) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (connection == null) {
                        Toast.makeText(PersonalModeActivity.this,
                                R.string.error_port_busy, Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        Toast.makeText(PersonalModeActivity.this,
                                R.string.port_open_ok, Toast.LENGTH_SHORT)
                                .show();
                    }
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        closeSynthResources();
        super.onDestroy();
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }


    private static void chageKeyBoard(int KeyNum, int KeyStatus){
        if (KeyStatus == 127){
            MidiPianoLayout.getImageButton(KeyNum).setPressed(true);
        }else if(KeyStatus == 0){
            MidiPianoLayout.getImageButton(KeyNum).setPressed(false);
        }
    }
}
