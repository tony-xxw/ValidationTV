package com.aispeech.validationtv.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;

/**
 * 保活服务
 */
public class KeepAliveReceiver extends BroadcastReceiver {
    private static final String TAG = "KeepAliveReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive" + intent.getAction());
        if (Intent.ACTION_MEDIA_BUTTON.equals(intent.getAction())) {
            KeyEvent keyEvent = intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
            if (keyEvent != null && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_SEARCH) {
                    Log.d(TAG, "VOICE DOWN");
                }
            }
        }
    }
}