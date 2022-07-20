package com.arpa.wms.hly.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import androidx.annotation.RawRes;

import java.util.HashMap;

/**
 * 本地提示音播放器
 * SoundPool用于播放声音短，文件小的音频，延时短
 */
public class SoundPlayer {
    private static final int MAX_SOUNDS = 3;
    private final Context appContext;
    private final SoundPool soundPool;
    private final HashMap<Integer, Integer> soundMap = new HashMap<>();

    public SoundPlayer(Context appContext) {
        // 版本兼容
        this.appContext = appContext;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundPool = new SoundPool.Builder().setMaxStreams(MAX_SOUNDS).build();
        } else {
            //第一个参数是可以支持的声音数量，第二个是声音类型，第三个是声音品质
            soundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        }
        // 设置音量
        AudioManager am = (AudioManager) appContext.getSystemService(Context.AUDIO_SERVICE);
        am.setStreamVolume(AudioManager.STREAM_RING, am.getStreamMaxVolume(AudioManager.STREAM_RING), AudioManager.FLAG_ALLOW_RINGER_MODES);
    }

    /**
     * 播放音频
     *
     * @param resId      音频文件 R.raw.xxx
     * @param repeatTime 循环模式：0表示循环一次，-1表示一直循环，其他表示数字+1表示当前数字对应的循环次
     */
    public void play(@RawRes int resId, int repeatTime) {
        int soundID = soundPool.load(appContext, resId, 1);
        // 该方法防止sample not ready错误
        soundPool.setOnLoadCompleteListener((soundPool, sampleId, status) -> {
            int streamId = soundPool.play(soundID, 1, 1, 1, repeatTime, 1);
            soundMap.put(resId, streamId);
        });
    }

    /**
     * 播放音频
     *
     * @param resId 音频文件 R.raw.xxx
     */
    public void play(@RawRes int resId) {
        int soundID = soundPool.load(appContext, resId, 1);
        // 该方法防止sample not ready错误
        soundPool.setOnLoadCompleteListener((soundPool, sampleId, status) -> {
            int streamId = soundPool.play(soundID, 1, 1, 1, 0, 1);
            soundMap.put(resId, streamId);
        });
    }

    /**
     * 暂停
     */
    public void pause(@RawRes int resId) {
        if (soundPool != null) {
            Integer mStreamID = soundMap.get(resId);
            if (mStreamID != null) {
                soundPool.pause(mStreamID);
            }
        }
    }

    /**
     * 继续
     */
    public void resume(@RawRes int resId) {
        if (soundPool != null) {
            Integer mStreamID = soundMap.get(resId);
            if (mStreamID != null) {
                soundPool.resume(mStreamID);
            }
        }
    }

    /**
     * 停止
     */
    public void stop(@RawRes int resId) {
        if (soundPool != null) {
            Integer mStreamID = soundMap.get(resId);
            if (mStreamID != null) {
                soundPool.stop(mStreamID);
            }
        }
    }


    /**
     * 资源释放
     */
    public void release() {
        if (soundPool != null) {
            soundPool.autoPause();
            soundPool.release();
        }
    }

}
