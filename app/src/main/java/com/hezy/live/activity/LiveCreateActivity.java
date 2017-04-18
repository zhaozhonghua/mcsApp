package com.hezy.live.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hezy.live.BaseException;
import com.hezy.live.R;
import com.hezy.live.callback.LessonCallback;
import com.hezy.live.entity.Encounter;
import com.hezy.live.entity.Lesson;
import com.hezy.live.persistence.Preferences;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class LiveCreateActivity extends BaseActivity {

    private TextView codeText, createTimeText, remarkText, doctorText;
    private ImageView avatar;

    private EditText summaryEdit;
    private Button createButton;

    private Encounter encounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_live_create);
        initToolBar();

        encounter = getIntent().getParcelableExtra("encounter");

        if (encounter != null) {
            init();
        }

        init();

    }

    private void init() {

        codeText = (TextView) findViewById(R.id.code);
        codeText.setText("就诊编号：" + encounter.registerCode);

        remarkText = (TextView) findViewById(R.id.remark);
        remarkText.setText("症状描述：" + encounter.remarks);

        createTimeText = (TextView) findViewById(R.id.createTime);
        createTimeText.setText("挂号时间：" + encounter.createDate);

        doctorText = (TextView) findViewById(R.id.doctor);
        doctorText.setText("主治医师：" + Preferences.getUserMobile(prefs));

        avatar = (ImageView) findViewById(R.id.avatar);
        Picasso.with(this).load("https://imgsa.baidu.com/baike/w%3D268/sign=ee8fc6544bfbfbeddc59317940f0f78e/8601a18b87d6277f2a0f655328381f30e924fcb8.jpg").into(avatar);

        summaryEdit = (EditText) findViewById(R.id.coursera_summary);

        createButton = (Button) findViewById(R.id.coursera_begin);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(summaryEdit.getText())) {
                    Log.d("test", "111111");
                    try {
                        JSONObject jsonParam = new JSONObject();
                        jsonParam.put("description", summaryEdit.getText().toString());
                        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParam.toString());
                        client.doctorUpdateEncounter(requestBody, encounter.id, Preferences.getUserId(prefs), Preferences.getToken(prefs), doctorUpdateEncounterCallback);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(LiveCreateActivity.this, "病例简介不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private LessonCallback doctorUpdateEncounterCallback = new LessonCallback() {
        @Override
        protected void onSuccess(Lesson lesson) {
            startActivity(new Intent(LiveCreateActivity.this, MainTabActivity.class));
        }

        @Override
        protected void onFailure(BaseException exception) {
            Toast.makeText(LiveCreateActivity.this, exception.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

}
