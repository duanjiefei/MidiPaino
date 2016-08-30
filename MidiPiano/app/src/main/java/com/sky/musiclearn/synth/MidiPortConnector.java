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

import android.media.midi.MidiDevice;
import android.media.midi.MidiDevice.MidiConnection;
import android.media.midi.MidiDeviceInfo;
import android.media.midi.MidiInputPort;
import android.media.midi.MidiManager;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;

/**
 * Tool for connecting MIDI ports on two remote devices.
 */
public class MidiPortConnector {
    private final MidiManager mMidiManager;
    private MidiDevice mSourceDevice;
    private MidiDevice mDestinationDevice;
    private MidiConnection mConnection;

    /**
     * @param
     */
    public MidiPortConnector(MidiManager midiManager) {
        mMidiManager = midiManager;
    }

    public void close() throws IOException {
        if (mConnection != null) {
            Log.i(MidiConstants.TAG,
                    "MidiPortConnector closing connection " + mConnection);
            mConnection.close();
            mConnection = null;
        }
        if (mSourceDevice != null) {
            mSourceDevice.close();
            mSourceDevice = null;
        }
        if (mDestinationDevice != null) {
            mDestinationDevice.close();
            mDestinationDevice = null;
        }
    }

    private void safeClose() {
        try {
            close();
        } catch (IOException e) {
            Log.e(MidiConstants.TAG, "could not close resources", e);
        }
    }

    /**
     * Listener class used for receiving the results of
     * {@link #connectToDevicePort}
     */
    public interface OnPortsConnectedListener {
        /**
         * Called to respond to a {@link #connectToDevicePort} request
         *
         * @param connection
         *            a {@link MidiConnection} that represents the connected
         *            ports, or null if connection failed
         */
        abstract public void onPortsConnected(MidiConnection connection);
    }

    /**
     * Open two devices and connect their ports.
     *
     * @param sourceDeviceInfo
     * @param sourcePortIndex
     * @param destinationDeviceInfo
     * @param destinationPortIndex
     */
    public void connectToDevicePort(final MidiDeviceInfo sourceDeviceInfo,
            final int sourcePortIndex,
            final MidiDeviceInfo destinationDeviceInfo,
            final int destinationPortIndex) {
        connectToDevicePort(sourceDeviceInfo, sourcePortIndex,
                destinationDeviceInfo, destinationPortIndex, null, null);
    }

    /**
     * Open two devices and connect their ports.
     *
     *
     * 该方法首先打开目标设备，及其端口，然后调用私有的connectToDevicePort打开源设备，并进行连接
     *
     *
     *  注意 1 MidiDeviceInfo与MidiDevice的区别
     *       2 MidiManager 的public void openDevice(MidiDeviceInfo deviceInfo, OnDeviceOpenedListener listener,
     Handler handler)方法的输入参数为MidiDeviceInfo，通过回调去打开设备，也就是MidiDevice
            3 MidiDevice的public MidiConnection connectPorts(MidiInputPort inputPort, int outputPortNumber)方法才
     是真正的连接设备，注意两个参数的含义  第一参数为目标设备的输入端口，第二个参数为源设备的输出端口号
     *
     * @param sourceDeviceInfo
     * @param sourcePortIndex
     * @param destinationDeviceInfo
     * @param destinationPortIndex
     */
    public void connectToDevicePort(final MidiDeviceInfo sourceDeviceInfo,
            final int sourcePortIndex,
            final MidiDeviceInfo destinationDeviceInfo,
            final int destinationPortIndex,
            final OnPortsConnectedListener listener, final Handler handler) {
        safeClose();
        //1 先打开目标设备
        mMidiManager.openDevice(destinationDeviceInfo,
                new MidiManager.OnDeviceOpenedListener() {
                    @Override
                    public void onDeviceOpened(MidiDevice destinationDevice) {
                        if (destinationDevice == null) {
                            Log.e(MidiConstants.TAG,
                                    "could not open " + destinationDeviceInfo);
                            if (listener != null) {
                                listener.onPortsConnected(null);
                            }
                        } else {
                            mDestinationDevice = destinationDevice;
                            Log.i(MidiConstants.TAG,
                                    "connectToDevicePort opened "
                                            + destinationDeviceInfo);
                            // Destination device was opened so go to next step.
                            //2 打开目标设备的输入端口
                            MidiInputPort destinationInputPort = destinationDevice
                                    .openInputPort(destinationPortIndex);
                            if (destinationInputPort != null) {
                                Log.i(MidiConstants.TAG,
                                        "connectToDevicePort opened port on "
                                                + destinationDeviceInfo);
                                //3 连接设备
                                connectToDevicePort(sourceDeviceInfo,
                                        sourcePortIndex,
                                        destinationInputPort,
                                        listener, handler);
                            } else {
                                Log.e(MidiConstants.TAG,
                                        "could not open port on "
                                                + destinationDeviceInfo);
                                safeClose();
                                if (listener != null) {
                                    listener.onPortsConnected(null);
                                }
                            }
                        }
                    }
                }, handler);
    }


    /**
     * Open a source device and connect its output port to the
     * destinationInputPort.
     *
     * @param sourceDeviceInfo
     * @param sourcePortIndex
     * @param destinationInputPort
     */
    private void connectToDevicePort(final MidiDeviceInfo sourceDeviceInfo,
            final int sourcePortIndex,
            final MidiInputPort destinationInputPort,
            final OnPortsConnectedListener listener, final Handler handler) {
        //3.1 打开源设备
        mMidiManager.openDevice(sourceDeviceInfo,
                new MidiManager.OnDeviceOpenedListener() {
                    @Override
                    public void onDeviceOpened(MidiDevice device) {
                        if (device == null) {
                            Log.e(MidiConstants.TAG,
                                    "could not open " + sourceDeviceInfo);
                            safeClose();
                            if (listener != null) {
                                listener.onPortsConnected(null);
                            }
                        } else {
                            Log.i(MidiConstants.TAG,
                                    "connectToDevicePort opened "
                                            + sourceDeviceInfo);
                            // Device was opened so connect the ports.
                            mSourceDevice = device;
                            //3.2连接端口
                            mConnection = device.connectPorts(
                                    destinationInputPort, sourcePortIndex);//这里才是最终的连接在一起
                            if (mConnection == null) {
                                Log.e(MidiConstants.TAG, "could not connect to "
                                        + sourceDeviceInfo);
                                safeClose();
                            }
                            if (listener != null) {
                                listener.onPortsConnected(mConnection);
                            }
                        }
                    }
                }, handler);
    }

}
