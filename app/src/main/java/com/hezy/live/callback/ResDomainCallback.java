package com.hezy.live.callback;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hezy.live.BaseException;
import com.hezy.live.entity.ResDomain;
import com.hezy.live.entity.RespStatus;

public class ResDomainCallback extends BaseCallback {

    protected void onSuccess(ResDomain resDomain) {
    }

    protected void onFailure(BaseException exception) {
    }

    @Override
    protected void onSuccess(JSONObject jsonObject) {
        if (jsonObject.containsKey("errcode")) {
            int code = jsonObject.getIntValue("errcode");
            if (code == 0) {
                onSuccess(JSON.parseObject(jsonObject.getJSONObject("data").getJSONObject("staticRes").toString(), ResDomain.class));
            } else {
                RespStatus respStatus = JSON.parseObject(jsonObject.toString(), RespStatus.class);
                onFailure(new BaseException(respStatus.getErrmsg(), respStatus.getErrcode()));
            }
        } else {
            onFailure(new BaseException("未知数据格式" + jsonObject.toString(), -1));
        }
    }

    @Override
    protected void onSuccess(JSONArray jsonArray) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onFail(BaseException exception) {
        onFailure(exception);
    }

}
