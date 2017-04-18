package com.hezy.live.callback;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hezy.live.BaseException;
import com.hezy.live.entity.Build;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by whatisjava on 15/11/11.
 */
public class BuildCallback extends BaseCallback {

    protected void onSuccess(Build build) {
    }

    protected void onFailure(BaseException exception) {
    }

    @Override
    protected void onSuccess(JSONObject jsonObject) {
        if (jsonObject.containsKey("error")) {
            int code = jsonObject.getIntValue("error");
            if (code == 0) {
                onSuccess(JSON.parseObject(jsonObject.getJSONObject("build").toString(), Build.class));
            } else {
                Error error = JSON.parseObject(jsonObject.toString(), Error.class);
//                onFailure(new BaseException(error.message, error.error));
            }
        } else {
            onFailure(new BaseException("未知返回" + jsonObject.toString(), -1));
        }
    }

    @Override
    protected void onSuccess(JSONArray jsonArray) {
    }

    @Override
    protected void onFail(BaseException exception) {
        onFailure(exception);
    }
}

