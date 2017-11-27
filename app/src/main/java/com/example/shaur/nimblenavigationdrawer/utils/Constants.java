package com.example.shaur.nimblenavigationdrawer;



import android.media.AudioFormat;

public class Constants {

    public static final int ACTIVITY_REQUEST_CODE_FOR_RECORD = 100;
    public static final int PERMISSION_REQ_CODE_FOR_AUDIO_RECORD = 101;

    public static final int NOTIFICATION_ID = 7797;

    public static final String NOTIF_ACTION_MEDIA_TOGGLE = "com.example.lt.recorder.toggle";
    public static final String NOTIF_ACTION_MEDIA_NEXT = "com.example.lt.recorder.next";

    public static final String SERVICE_PLAY_STATUS_CHANGE = "com.example.lt.recorder.change";
    public static final String STATUS_CHANGE_FILE = "playing_file";
    public static final String STATUS_CHANGE_PLAYING = "play_status";
    public static final int PLAY_STATUS_STOP = 0;
    public static final int PLAY_STATUS_ING = 1;
    public static final int PLAY_STATUS_PAUSED = 2;
    public static final int PLAY_STATUS_ERR = 3;

    public static final int RECORD_SAMPLE_RATE = 44100;
    public static final int RECORD_CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_MONO;
    public static final int RECORD_FORMAT = AudioFormat.ENCODING_PCM_16BIT;

}
