package com.example.shaur.nimblenavigationdrawer;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;



import java.util.ArrayList;
import java.util.List;


class PlayServiceAgent {

    private static PlayServiceAgent INSTANCE = new PlayServiceAgent();

    static PlayServiceAgent getInstance() {
        return INSTANCE;
    }

    private PlayServiceAgent() {
    }

    private static final String TAG = "PlayServiceAgent";

    private boolean mConnected;
    private volatile boolean mReady;
    private com.example.shaur.nimblenavigationdrawer.IPlayServiceInterface mService;
    private String mPlayingDir;
    private String mPlayingFile;
    private int mPlayState;


    private List<PlayingStatusListener> mListeners = new ArrayList<>();

    interface PlayingStatusListener {
        void onPlayStatusChange(String fileName, int state);
    }

    void addPlayingStatusListener(PlayingStatusListener listener) {
        mListeners.add(listener);
    }

    @SuppressWarnings("unused")
    void removePlayingStatusListener(PlayingStatusListener listener) {
        mListeners.remove(listener);
    }

    private void notifyListeners(String fileName, int playingState) {
        for (PlayingStatusListener listener : mListeners) {
            if (listener != null) {
                listener.onPlayStatusChange(fileName, playingState);
            }
        }
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mPlayingFile = intent.getStringExtra(com.example.shaur.nimblenavigationdrawer.Constants.STATUS_CHANGE_FILE);
            mPlayState = intent.getIntExtra(com.example.shaur.nimblenavigationdrawer.Constants.STATUS_CHANGE_PLAYING, 0);
            Log.d(TAG, "onReceive: filename=" + mPlayingFile + ", state=" + mPlayState);
            notifyListeners(mPlayingFile, mPlayState);
        }
    };

    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = com.example.shaur.nimblenavigationdrawer.IPlayServiceInterface.Stub.asInterface(service);
            mConnected = true;
            setPlayingDir(mPlayingDir);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
            mConnected = false;
            mReady = false;
        }
    };

    void init(Context context) {
        Intent i = new Intent(context, PlayService.class);
        boolean result = context.bindService(i, mConn, Service.BIND_AUTO_CREATE);
        Log.d(TAG, "service bind result:" + result);
        //
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(com.example.shaur.nimblenavigationdrawer.Constants.SERVICE_PLAY_STATUS_CHANGE);
        context.registerReceiver(mReceiver, intentFilter);
        mPlayingDir = com.example.shaur.nimblenavigationdrawer.Utils.getRecordDir(context);
        mPlayingFile = null;
        mPlayState = com.example.shaur.nimblenavigationdrawer.Constants.PLAY_STATUS_STOP;
    }

    void disconnect(Context context) {
        try {
            if (mConnected) {
                context.unbindService(mConn);
                mService = null;
            }
        } catch (Exception e) {
            Log.e(TAG, "disconnect: " + e.getMessage());
        }
    }


    void setPlayingDir(String dir) {
        mReady = false;
        if (!mConnected || mService == null) {
            return;
        }
        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {
                try {
                    if (mConnected && mService != null) {
                        mService.setPlayingDir(params[0]);
                        mReady = true;
                        Log.d(TAG, "play service refresh ok");
                    }
                } catch (Exception e) {
                    mReady = false;
                }
                return null;
            }
        }.execute(dir);
    }

    void playFile(String file) {
        if (mConnected && mReady) {
            try {
                mService.playFile(file);
            } catch (RemoteException e) {
                Log.e(TAG, "playFile: ", e);
            }
        }
    }

    void start() {
        if (mConnected && mReady) {
            try {
                mService.start();
            } catch (RemoteException e) {
                Log.e(TAG, "start: ", e);
            }
        }
    }

    void pause() {
        if (mConnected && mReady) {
            try {
                mService.pause();
            } catch (RemoteException e) {
                Log.e(TAG, "pause: ", e);
            }
        }
    }

    void resume() {
        if (mConnected && mReady) {
            try {
                mService.resume();
            } catch (RemoteException e) {
                Log.e(TAG, "resume: ", e);
            }
        }
    }

    String getPlayingFile() {
        return mPlayingFile;
    }

    int getPlayState() {
        return mPlayState;
    }
}
