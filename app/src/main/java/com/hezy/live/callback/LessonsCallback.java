package com.hezy.live.callback;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hezy.live.BaseException;
import com.hezy.live.entity.Courseras;
import com.hezy.live.entity.Lessons;
import com.hezy.live.entity.RespStatus;

/**
 * Created by whatisjava on 15/11/11.
 */
public class LessonsCallback extends BaseCallback {

    protected void onSuccess(Lessons lessons) {
    }

    protected void onFailure(BaseException exception) {
    }

    @Override
    protected void onSuccess(JSONObject jsonObject) {
        if (jsonObject.containsKey("errcode")) {
            int code = jsonObject.getIntValue("errcode");
            if (code == 0) {
                onSuccess(JSON.parseObject(jsonObject.toString(), Lessons.class));
            } else {
                RespStatus respStatus = JSON.parseObject(jsonObject.toString(), RespStatus.class);
                onFailure(new BaseException(respStatus.getErrmsg(), respStatus.getErrcode()));
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

