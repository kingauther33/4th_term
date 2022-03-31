package com.t2004eandroid.util;

import android.app.Application;
import android.media.MediaPlayer;

public class MyGlobalClass extends Application {
    public MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public MediaPlayer getMediaPlayer() {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }
        return mediaPlayer;
    }
}
