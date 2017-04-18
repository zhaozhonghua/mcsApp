package com.hezy.live.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hezy.live.BaseException;
import com.hezy.live.R;
import com.hezy.live.callback.RespStatusCallback;
import com.hezy.live.callback.UserCallback;
import com.hezy.live.entity.RespStatus;
import com.hezy.live.entity.User;
import com.hezy.live.persistence.Preferences;
import com.hezy.live.util.CircleTransform;
import com.hezy.live.util.Md5Util;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ProfileModifyActivity extends BaseActivity {

    private InputMethodManager inputMethodManager;
    private EditText contentEdit;
    private TextView noticeText;

    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_modify);
        initToolBar();

        inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        content = getIntent().getStringExtra("content");

        init();
    }

    private void init() {
        noticeText = (TextView) findViewById(R.id.notice);
        contentEdit = (EditText) findViewById(R.id.modify_content);
        if ("name".equals(content)) {
            noticeText.setText("请输入新名字");
            contentEdit.setHint(Preferences.getUserName(prefs));
        } else if ("sign".equals(content)) {
            contentEdit.setHint(Preferences.getUserSign(prefs));
            noticeText.setText("请输入新签名(最多25个字)");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_profile_save:
                if (!TextUtils.isEmpty(contentEdit.getText())) {
                    try {
                        JSONObject jsonParam = new JSONObject();
                        if ("name".equals(content)) {
                            jsonParam.put("name", contentEdit.getText().toString());
                        } else if ("sign".equals(content)) {
                            jsonParam.put("title", contentEdit.getText().toString());
                        }
                        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParam.toString());
                        client.profileModify(requestBody, Preferences.getUserId(prefs), Preferences.getToken(prefs), userCallback);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {

                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private UserCallback userCallback = new UserCallback() {
        @Override
        protected void onSuccess(User user) {
            if ("name".equals(content)) {
                Preferences.setUserName(prefs, user.name);
            } else if ("sign".equals(content)) {
                Preferences.setUserSign(prefs, user.name);
            }
            inputMethodManager.hideSoftInputFromWindow(contentEdit.getWindowToken(), 0);
            setResult(RESULT_OK);
            finish();
        }

        @Override
        protected void onFailure(BaseException exception) {
            Toast.makeText(getApplicationContext(), exception.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    };

}
