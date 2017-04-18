package com.hezy.live.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.hezy.live.R;
import com.hezy.live.persistence.Preferences;

public class LauncherActivity extends Activity {

    public SharedPreferences prefs;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (TextUtils.isEmpty(Preferences.getToken(prefs))) {
                startActivity(new Intent(LauncherActivity.this, SignInActivity.class));
            } else {
                startActivity(new Intent(LauncherActivity.this, MainTabActivity.class));
            }

            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_launcher);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        handler.sendEmptyMessageDelayed(0, 3000);
    }



}
