package com.hezy.live.callback;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hezy.live.BaseException;
import com.hezy.live.entity.Courseras;
import com.hezy.live.entity.RespStatus;
import com.hezy.live.entity.User;

/**
 * Created by whatisjava on 15/11/11.
 */
public class CourserasCallback extends BaseCallback {

    protected void onSuccess(Courseras courseras) {
    }

    protected void onFailure(BaseException exception) {
    }

    @Override
    protected void onSuccess(JSONObject jsonObject) {
        if (jsonObject.containsKey("errcode")) {
            int code = jsonObject.getIntValue("errcode");
            if (code == 0) {
                onSuccess(JSON.parseObject(jsonObject.getJSONObject("data").toString(), Courseras.class));
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

