package io.agora.openlive.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.hezy.live.R;
import com.hezy.live.entity.Agroa;
import com.hezy.live.entity.Lesson;

import io.agora.openlive.model.ConstantApp;
import io.agora.rtc.Constants;

public class MainActivity extends BaseActivity {

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            forwardToLiveRoom(Constants.CLIENT_ROLE_BROADCASTER);
            finish();
        }
    };

    private String userId;
    private Lesson lesson;
    private Agroa agroa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userId = getIntent().getStringExtra("user_id");
        lesson = getIntent().getParcelableExtra("lesson");
        agroa = getIntent().getParcelableExtra("agroa");

    }

    @Override
    protected void initUIandEvent() {
        handler.sendEmptyMessageDelayed(0, 2000);
    }

    @Override
    protected void deInitUIandEvent() {
    }

    public void forwardToLiveRoom(int cRole) {
        Intent i = new Intent(MainActivity.this, LiveRoomActivity.class);
        i.putExtra(ConstantApp.ACTION_KEY_CROLE, cRole);
        i.putExtra(ConstantApp.ACTION_KEY_ROOM_NAME, lesson.getId());
        i.putExtra("agroa", agroa);
        i.putExtra("lesson", lesson);
        i.putExtra("user_id", userId);
        startActivity(i);
    }

}
