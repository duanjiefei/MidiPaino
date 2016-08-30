/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sky.musiclearn.synth;

import android.app.Activity;
import android.media.midi.MidiDeviceInfo;
import android.media.midi.MidiManager;
import android.util.Log;
import android.widget.Button;

import java.io.IOException;

/**
 * Select an output port and connect it to a destination input port.
 * 选择一个输出设备 并将其与一个输入设备进行连接
 *
 * 本程序中  输出设备代表钢琴
 * 输入设备代表  MidiSynthDeviceService   <device manufacturer="AndroidTest" product="SynthExample">
                                                  <input-port name="input" />
                                         </device>
 */
public class MidiOutputPortConnectionSelector extends MidiPortSelector {

    private MidiPortConnector mSynthConnector;
    private MidiDeviceInfo mDestinationDeviceInfo;//需要连接的目标设备
    private int mDestinationPortIndex;//需要连接的设备的目标端口  此处端口号0
    private MidiPortConnector.OnPortsConnectedListener mConnectedListener;

    /**
     * @param midiManager
     * @param activity
     * @param button
     * @param
     */
    public MidiOutputPortConnectionSelector(MidiManager midiManager,
            Activity activity,
            MidiDeviceInfo destinationDeviceInfo, int destinationPortIndex) {
        super(midiManager, activity,
                MidiDeviceInfo.PortInfo.TYPE_OUTPUT);
        mDestinationDeviceInfo = destinationDeviceInfo;
        mDestinationPortIndex = destinationPortIndex;
        onPortSelected(MidiPortSelector.mCurrentWrapper);
    }

    @Override
    public void onPortSelected(final MidiPortWrapper wrapper) {
        Log.i(MidiConstants.TAG, "connectPortToSynth: " + wrapper);
        onClose();
        if (wrapper.getDeviceInfo() != null) {
            //wrapper包含当前连接的设备的信息
            mSynthConnector = new MidiPortConnector(mMidiManager);//新建一个MidiPortConnector
            //将两个设备进行连接
            mSynthConnector.connectToDevicePort(wrapper.getDeviceInfo(),
                    wrapper.getPortIndex(), mDestinationDeviceInfo,
                    mDestinationPortIndex,
                    // not safe on UI thread
                    mConnectedListener, null);
        }
    }

    @Override
    public void onClose() {
        try {
            if (mSynthConnector != null) {
                mSynthConnector.close();
                mSynthConnector = null;
            }
        } catch (IOException e) {
            Log.e(MidiConstants.TAG, "Exception in closeSynthResources()", e);
        }
    }

    /**
     * @param 用于判断设备是否连接成功
     *
     *
     *
     */
    public void setConnectedListener(
            MidiPortConnector.OnPortsConnectedListener connectedListener) {
        mConnectedListener = connectedListener;
    }
}
