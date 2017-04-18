package com.hezy.live.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hezy.live.BaseException;
import com.hezy.live.R;
import com.hezy.live.callback.RespStatusCallback;
import com.hezy.live.entity.RespStatus;
import com.hezy.live.persistence.Preferences;
import com.hezy.live.util.Md5Util;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class PwdModifyActivity extends BaseActivity {

    private InputMethodManager inputMethodManager;

    private EditText oldPwdEdit, newPwdEdit, newPwdRepeatEdit;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd_modify);
        initToolBar();

        inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        init();
    }

    private void init() {
        submitButton = (Button) findViewById(R.id.submit);

        oldPwdEdit = (EditText) findViewById(R.id.old_pwd);
        newPwdEdit = (EditText) findViewById(R.id.new_pwd);
        newPwdRepeatEdit = (EditText) findViewById(R.id.new_pwd_repeat);

        oldPwdEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s) && !TextUtils.isEmpty(newPwdEdit.getText()) && !TextUtils.isEmpty(newPwdRepeatEdit.getText())) {
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

        newPwdEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s) && !TextUtils.isEmpty(oldPwdEdit.getText()) && !TextUtils.isEmpty(newPwdRepeatEdit.getText())) {
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

        newPwdRepeatEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s) && !TextUtils.isEmpty(oldPwdEdit.getText()) && !TextUtils.isEmpty(newPwdEdit.getText())) {
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
                if (!TextUtils.isEmpty(oldPwdEdit.getText())) {
                    if (!TextUtils.isEmpty(newPwdEdit.getText())) {
                        if (!TextUtils.isEmpty(newPwdRepeatEdit.getText())) {
                            try {
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("password", Md5Util.getMD5(oldPwdEdit.getText().toString()));
                                jsonObject.put("newPassword", Md5Util.getMD5(newPwdEdit.getText().toString()));
                                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
                                client.passwordModify(requestBody, Preferences.getUserId(prefs), Preferences.getToken(prefs), userCallback);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            newPwdRepeatEdit.setError(getResources().getString(R.string.new_pwd_repeat_hint));
                        }
                    } else {
                        newPwdEdit.setError(getResources().getString(R.string.new_pwd_hint));
                    }
                } else {
                    oldPwdEdit.setError(getResources().getString(R.string.old_pwd_hint));
                }
            }
        });

    }

    private RespStatusCallback userCallback = new RespStatusCallback() {

        @Override
        protected void onSuccess(RespStatus respStatus) {
            Toast.makeText(PwdModifyActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
            inputMethodManager.hideSoftInputFromWindow(newPwdRepeatEdit.getWindowToken(), 0);
            finish();
        }

        @Override
        protected void onFailure(BaseException exception) {
            Toast.makeText(PwdModifyActivity.this, exception.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }

    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        inputMethodManager.hideSoftInputFromWindow(newPwdRepeatEdit.getWindowToken(), 0);
        finish();
    }
}
