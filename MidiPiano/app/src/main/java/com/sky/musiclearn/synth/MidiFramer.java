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

import android.media.midi.MidiReceiver;

import com.sky.musiclearn.utils.Debugger;

import java.io.IOException;

/**
 * Convert stream of arbitrary MIDI bytes into discrete messages.
 *
 * Parses the incoming bytes and then posts individual messages to the receiver
 * specified in the constructor. Short messages of 1-3 bytes will be complete.
 * System Exclusive messages may be posted in pieces.
 *
 * Resolves Running Status and interleaved System Real-Time messages.
 */
public class MidiFramer extends MidiReceiver {
    private MidiReceiver mReceiver;
    private byte[] mBuffer = new byte[3];
    private int mCount;
    private byte mRunningStatus;
    private int mNeeded;
    private boolean mInSysEx;

    public MidiFramer(MidiReceiver receiver) {
        mReceiver = receiver;
    }

    /*
     * @see android.midi.MidiReceiver#onSend(byte[], int, int, long)
     *
     * 1 时间差是可变常量（variable length quantity），含义是将要发生的事件与前一事件之间的时间差值
     * 2 MIDI事件也叫MIDI events，常见的有音符事件、控制器事件和系统信息事件
     *   事件组成：种类+参数。
         种类用状态字节来区分，总是大于等于80H。
         参数用数据字节来区分，总是小于80H
         在状态字节中，用数据的低4为表示通道号，高4位表示不同的命令
     * 3 非MIDI事件也叫meta-event（元事件），是MIDI文件中的非MIDI信息
     *   所有的meta-event都以0xFF开头，接着是事件种类（总小于128）、
     *   数据的长度值length（用长度变量表示，无标志位）、数据。如果没有数据，那么长度为0
     * 4 系统码事件（sysex event）
     *
     *
     *
     *
     *
     *
     */
    @Override
    public void onSend(byte[] data, int offset, int count, long timestamp)
            throws IOException {
        int sysExStartOffset = (mInSysEx ? offset : -1);

        byte command = (byte) (data[0] & MidiConstants.STATUS_COMMAND_MASK);
        int channel = (byte) (data[0] & MidiConstants.STATUS_CHANNEL_MASK);

        Debugger.i("MidiFramer" , "command == "+command);
        Debugger.i("MidiFramer" , "channel == "+channel);


      int keyNum = data[1];     //代表那个按键被按下
        int keyStatus = data[2];  //代表按键被按下的状态  127代表按键被按下   0 表示按键抬起

        Debugger.i("MidiFramer","keynum == "+keyNum);
        Debugger.i("MidiFramer","keyStatus == "+keyStatus);
       /*if(keyStatus == 127){
           //按键被按下
           switch (keyNum){
               case 21 :
                   MidiPianoLayout.getImageButton(keyNum).setImageResource(R.drawable.whitekey_left_pressed);
                   Debugger.i(MidiConstants.TAG,"key 21  is pressed == true");
                   break;
               case 22 :
                   MidiPianoLayout.getImageButton(keyNum).setImageResource(R.drawable.blackkey_pressed);
                   Debugger.i(MidiConstants.TAG,"key 22  is pressed == true");
                   break;
               case 23 :
                   MidiPianoLayout.getImageButton(keyNum).setImageResource(R.drawable.whitekey_right_pressed);
                   Debugger.i(MidiConstants.TAG,"key 23  is pressed == true");
                   break;
               case 108 :
                   MidiPianoLayout.getImageButton(keyNum).setPressed(true);
                   MidiPianoLayout.getImageButton(keyNum).setBackgroundResource(R.drawable.whitekey_pressed);
                   Debugger.i(MidiConstants.TAG,"key is pressed == true");
                   break;

               default:
                   break;
           }
       }else if (keyStatus == 0){
           //按键被抬起
           switch (keyNum){

               case 21 :
                   MidiPianoLayout.getImageButton(keyNum).setImageResource(R.drawable.whitekey_left_unpressed);
                   Debugger.i(MidiConstants.TAG,"key 21  is pressed == false");
                   break;
               case 22 :
                   MidiPianoLayout.getImageButton(keyNum).setImageResource(R.drawable.blackkey_unpressed);
                   Debugger.i(MidiConstants.TAG,"key 22  is pressed == false");
                   break;
               case 23 :
                   MidiPianoLayout.getImageButton(keyNum).setImageResource(R.drawable.whitekey_right_unpressed);
                   Debugger.i(MidiConstants.TAG,"key 23  is pressed == false");
                   break;
               case 108 :
                   MidiPianoLayout.getImageButton(keyNum).setPressed(false);
                   MidiPianoLayout.getImageButton(keyNum).setBackgroundResource(R.drawable.whitekey_unpressed);
                   Debugger.i(MidiConstants.TAG,"key is pressed == false");
                   break;

               default:
                   break;
           }
       }*/

        for (int i = 0; i < count; i++) {
            final byte currentByte = data[offset];
            final int currentInt = currentByte & 0xFF;


            //第一个字节   currentByte == -112 currentInt == 144
            //第二个字节   currentByte == 80 currentInt == 80//跟具体的按键有关系
            //第二个字节   currentByte == 127 currentInt == 127//跟按下的状态有关系，127表示按下

            //第一个字节   currentByte == -128 currentInt == 128
            //第二个字节   currentByte == 80 currentInt == 80//跟具体的按键有关系
            //第二个字节   currentByte == 0 currentInt == 0//跟按下的状态有关系，0表示按键抬起


            if (currentInt >= 0x80) { // status byte?
                if (currentInt < 0xF0) { // channel message?
                    mRunningStatus = currentByte;
                    mCount = 1;
                    mNeeded = MidiConstants.getBytesPerMessage(currentByte) - 1;
                } else if (currentInt < 0xF8) { // system common?
                    if (currentInt == 0xF0 /* SysEx Start */) {
                        // Log.i(TAG, "SysEx Start");
                        mInSysEx = true;
                        sysExStartOffset = offset;
                    } else if (currentInt == 0xF7 /* SysEx End */) {
                        // Log.i(TAG, "SysEx End");
                        if (mInSysEx) {
                            mReceiver.send(data, sysExStartOffset,
                                offset - sysExStartOffset + 1, timestamp);
                            mInSysEx = false;
                            sysExStartOffset = -1;
                        }
                    } else {
                        mBuffer[0] = currentByte;
                        mRunningStatus = 0;
                        mCount = 1;
                        mNeeded = MidiConstants.getBytesPerMessage(currentByte) - 1;
                    }
                } else { // real-time?
                    // Single byte message interleaved with other data.
                    if (mInSysEx) {
                        mReceiver.send(data, sysExStartOffset,
                                offset - sysExStartOffset, timestamp);
                        sysExStartOffset = offset + 1;
                    }
                    mReceiver.send(data, offset, 1, timestamp);
                }
            } else { // data byte
                if (!mInSysEx) {
                    mBuffer[mCount++] = currentByte;
                    if (--mNeeded == 0) {
                        if (mRunningStatus != 0) {
                            mBuffer[0] = mRunningStatus;
                        }
                        mReceiver.send(mBuffer, 0, mCount, timestamp);
                        mNeeded = MidiConstants.getBytesPerMessage(mBuffer[0]) - 1;
                        mCount = 1;
                    }
                }
            }
            ++offset;
        }

        // send any accumulatedSysEx data
        if (sysExStartOffset >= 0 && sysExStartOffset < offset) {
            mReceiver.send(data, sysExStartOffset,
                    offset - sysExStartOffset, timestamp);
        }
    }

}
