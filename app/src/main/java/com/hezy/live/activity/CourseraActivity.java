package com.hezy.live.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.hezy.live.R;
import com.hezy.live.entity.Encounter;
import com.hezy.live.persistence.Preferences;
import com.squareup.picasso.Picasso;

import java.util.Date;

public class CourseraActivity extends BaseActivity {

    protected SharedPreferences prefs;

    private Encounter encounter;

    private TextView codeText, descText, createTimeText, remarkText, updateTimeText, doctorText;
    private ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = PreferenceManager.getDefaultSharedPreferences(CourseraActivity.this);

        setContentView(R.layout.activity_coursera);
        initToolBar();

        encounter = getIntent().getParcelableExtra("encounter");

        if (encounter != null) {
            init();
        }
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

        descText = (TextView) findViewById(R.id.desc);
        descText.setText("病情描述：" + encounter.description);

        updateTimeText = (TextView) findViewById(R.id.updateTime);
        updateTimeText.setText("治疗时间：" + encounter.updateDate);

        avatar = (ImageView) findViewById(R.id.avatar);
        Picasso.with(this).load("https://imgsa.baidu.com/baike/w%3D268/sign=ee8fc6544bfbfbeddc59317940f0f78e/8601a18b87d6277f2a0f655328381f30e924fcb8.jpg").into(avatar);
    }

}
