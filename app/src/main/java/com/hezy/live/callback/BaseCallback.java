package com.hezy.live.callback;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hezy.live.BaseException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public abstract class BaseCallback implements Callback {

    private static final String TAG = BaseCallback.class.getName();

    public static final String UTF8_BOM = "\uFEFF";

    abstract protected void onSuccess(final JSONObject jsonObject);

    abstract protected void onSuccess(final JSONArray jsonArray);

    abstract protected void onFail(final BaseException exception);

    @Override
    public void onFailure(final Call call, final IOException e) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                onFail(new BaseException(e));
            }
        });
    }

    @Override
    public void onResponse(final Call call, final Response response) {
//        if (!response.isSuccessful() || response.body() == null) {
        if (response.body() == null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    onFail(new BaseException(response.message(), response.code()));
                }
            });
            return;
        }
        try {
            String responseStr = response.body().string().trim();
            Log.d(TAG, responseStr);
            if (responseStr.startsWith(UTF8_BOM)) {
                responseStr = responseStr.substring(1);
            }
            final Object object = JSON.parse(responseStr);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (object instanceof JSONObject) {
                        onSuccess((JSONObject) object);
                    } else if (object instanceof JSONArray) {
                        onSuccess((JSONArray) object);
                    } else {
                        onFail(new BaseException("unexpect response : " + object.toString(), -1));
                    }
                }
            });
        } catch (Exception e) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    onFail(new BaseException(e.getMessage(), -1));
                }
            });
        }
    }

    private void runOnUiThread(Runnable task) {
        new Handler(Looper.getMainLooper()).post(task);
    }

}
