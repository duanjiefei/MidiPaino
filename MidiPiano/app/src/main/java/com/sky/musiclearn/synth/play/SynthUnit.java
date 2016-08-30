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

package com.sky.musiclearn.synth.play;


/**
 * 振幅决定声音的大小，振动的频率决定音高
 */

public abstract class SynthUnit {

    private static final double CONCERT_A_PITCH = 69.0;//音乐会  音调/音高
    private static final double CONCERT_A_FREQUENCY = 440.0;//音乐会  频率

    /**
     * @param pitch
     *            MIDI pitch in semitones
     * @return frequency
     *
     * 我们知道，音符的音高是由频率决定的，那么具体是怎样的对应关系，如何量化，本文会解释清楚这些问题。

    预备知识
    声音是物体的振动产生的，振幅决定声音的大小，振动的频率决定音高。至于音色则比较复杂，比如钢琴和吉他弹奏同一个音符，频率一样，但是我们还是能够听出差别，这是因为这里的频率一样只是基音频率一样，还有各种泛音，钢琴和吉他的泛音频率不一样，导致音色不一样。所以本质上还是频率决定了音色。
    人耳能够听到的声音的频率范围是20 - 20KHz。
    人耳对音高的感受，或者说对频率的感受不是线性的，是对数关系。比如听到三个音符：do，高八度的do，再高八度的do，这三个音符的频率不是线性的等差数列，而是等比数列。所以每高一个八度，频率翻倍。
    规定A4音高的频率是440Hz。我们常说的中央C是C4，A4就是中央C的do对应的la
     *
     *
     * 音高和频率之间转化
     */
    public static double pitchToFrequency(double pitch) {
        double semitones = pitch - CONCERT_A_PITCH;
        return CONCERT_A_FREQUENCY * Math.pow(2.0, semitones / 12.0);
    }

    public abstract float render();
}
