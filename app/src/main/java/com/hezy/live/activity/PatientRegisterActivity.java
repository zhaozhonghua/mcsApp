package com.hezy.live.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hezy.live.BaseException;
import com.hezy.live.R;
import com.hezy.live.callback.LessonCallback;
import com.hezy.live.entity.Lesson;
import com.hezy.live.persistence.Preferences;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class PatientRegisterActivity extends BaseActivity {

    private EditText symptomDescEdit;
    private Button createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();

    }

    private void init() {

        symptomDescEdit = (EditText) findViewById(R.id.symptom_desc);

        createButton = (Button) findViewById(R.id.iwantregister);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(symptomDescEdit.getText())) {
                    try {
                        JSONObject jsonParam = new JSONObject();
                        jsonParam.put("remark", symptomDescEdit.getText().toString());
                        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParam.toString());
                        client.patientRegister(requestBody, Preferences.getUserId(prefs), Preferences.getToken(prefs), patientRegisterCallback);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(PatientRegisterActivity.this, "症状描述不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private LessonCallback patientRegisterCallback = new LessonCallback() {
        @Override
        protected void onSuccess(Lesson lesson) {
            startActivity(new Intent(PatientRegisterActivity.this, MainTabActivity.class));
        }

        @Override
        protected void onFailure(BaseException exception) {
            Toast.makeText(PatientRegisterActivity.this, exception.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
