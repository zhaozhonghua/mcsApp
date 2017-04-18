package com.hezy.live.activity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hezy.live.BaseException;
import com.hezy.live.R;
import com.hezy.live.callback.UserCallback;
import com.hezy.live.entity.User;
import com.hezy.live.persistence.Preferences;
import com.hezy.live.util.Md5Util;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class SignInActivity extends BaseActivity {

    private static final String tag = SignInActivity.class.getSimpleName();

    private InputMethodManager inputMethodManager;

    private EditText mobileEdit, pwdEdit;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        init();
    }

    private void init() {

        submitButton = (Button) findViewById(R.id.submit);

        mobileEdit = (EditText) findViewById(R.id.mobile);
        pwdEdit = (EditText) findViewById(R.id.pwd);

        mobileEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s) && !TextUtils.isEmpty(pwdEdit.getText())) {
                    submitButton.setEnabled(true);
                } else {
                    submitButton.setEnabled(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        pwdEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s) && !TextUtils.isEmpty(mobileEdit.getText())) {
                    submitButton.setEnabled(true);
                } else {
                    submitButton.setEnabled(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        submitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(mobileEdit.getText())) {
                    if (!TextUtils.isEmpty(pwdEdit.getText())) {
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("username", mobileEdit.getText().toString());
                            jsonObject.put("password", Md5Util.getMD5(pwdEdit.getText().toString()));
                            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
                            client.signIn(requestBody, userCallback);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        pwdEdit.setError(getResources().getString(R.string.password));
                    }
                } else {
                    mobileEdit.setError(getResources().getString(R.string.mobile));
                }
            }
        });

    }

    private UserCallback userCallback = new UserCallback() {

        @Override
        protected void onSuccess(User user) {
            Log.d("user", user.toString());
            Preferences.setToken(prefs, user.token);
            Preferences.setUserId(prefs, user.id);
            Preferences.setUserName(prefs, user.name);
            Preferences.setUserGender(prefs, user.sex + "");
            Preferences.setUserMobile(prefs, user.mobile);
            Preferences.setUserAvatar(prefs, user.head);
            Preferences.setUserType(prefs, user.type);

            inputMethodManager.hideSoftInputFromWindow(pwdEdit.getWindowToken(), 0);
            startActivity(new Intent(SignInActivity.this, MainTabActivity.class));
            finish();
        }

        @Override
        protected void onFailure(BaseException exception) {
            Log.d(tag, exception.getCause().getMessage());
            Toast.makeText(SignInActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
        }

    };

}
