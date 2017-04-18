package com.hezy.live.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.hezy.live.R;
import com.hezy.live.entity.Encounter;

public class CourseraActivity extends BaseActivity {

    private Encounter encounter;

    private TextView courseraCountText, audienceText, courseraNameText;
    private ImageView courseraImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursera);
        initToolBar();

        encounter = getIntent().getParcelableExtra("encounter");

        if (encounter != null) {
            init();
        }
    }

    private void init() {
        courseraCountText = (TextView) findViewById(R.id.coursera_count);
        courseraCountText.setText(encounter.registerCode = "t1");
        audienceText = (TextView) findViewById(R.id.audience_count);
        audienceText.setText(encounter.registerCode + "人报名");
        courseraNameText = (TextView) findViewById(R.id.coursera_name);
        courseraNameText.setText(encounter.registerCode+"t2");
        courseraImage = (ImageView) findViewById(R.id.coursera_cover);
//        Picasso.with(this).load(encounter.patient.head).into(courseraImage);
    }

}
