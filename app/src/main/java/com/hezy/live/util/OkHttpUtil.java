package com.hezy.live.util;

/**
 * Created by whatisjava on 17-1-3.
 */

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.hezy.live.BaseException;
import com.hezy.live.entity.RespStatus;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUtil {

    private static OkHttpUtil okHttpUtil;
    private static OkHttpClient okHttpClient;
    private Handler mHandler;

    private OkHttpUtil() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        clientBuilder.connectTimeout(10, TimeUnit.SECONDS);
        clientBuilder.readTimeout(10, TimeUnit.SECONDS);
        clientBuilder.writeTimeout(30, TimeUnit.SECONDS);

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(httpLoggingInterceptor);

        okHttpClient = clientBuilder.build();

        mHandler = new Handler(Looper.getMainLooper());
    }

    public static OkHttpUtil getInstance() {
        if (okHttpUtil == null) {
            synchronized (OkHttpUtil.class) {
                if (okHttpUtil == null) {
                    okHttpUtil = new OkHttpUtil();
                }
            }
        }
        return okHttpUtil;
    }

    private void request(final Request request, final HttpCallback callback) {

        callback.onStart();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callbackFailure(request, callback, new BaseException(e.getMessage(), e));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                if (response.isSuccessful() && response.body() != null) {
                if (response.body() != null) {
                    String resString = response.body().string();
                    if (callback.mType == String.class) {
                        callbackSuccess(response, resString, callback);
                    } else {
                        try {
                            Object object = JSON.parseObject(resString, callback.mType);
                            callbackSuccess(response, object, callback);
                        } catch (JsonParseException e) {
                            e.printStackTrace();
                            try {
                                RespStatus respStatus = JSON.parseObject(resString, RespStatus.class);
                                callbackError(response, callback, new BaseException(respStatus.getErrmsg(), respStatus.getErrcode()));
                            } catch (JsonParseException ee) {
                                ee.printStackTrace();
                                callbackError(response, callback, new BaseException(ee.getMessage(), ee));
                            }

                        }
                    }
                } else {
                    callbackError(response, callback, null);
                }
            }
        });
    }

    private void callbackSuccess(final Response response, final Object o, final HttpCallback callback) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(o);
                callback.onFinish();
            }
        });
    }

    private void callbackError(final Response response, final HttpCallback callback, final BaseException e) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                callback.onError(response.code(), e);
                callback.onFinish();
            }
        });
    }

    private void callbackFailure(final Request request, final HttpCallback callback, final BaseException e) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                callback.onFailure(e);
                callback.onFinish();
            }
        });
    }

    private void runOnUiThread(Runnable task) {
        mHandler.post(task);
    }

    public void get(String url, Map<String, String> headers, Map<String, String> params, HttpCallback callback) {
        Request request = buildRequest(jointUrl(url, params), headers, null, HttpMethodType.GET);
        request(request, callback);
    }

    public void post(String url, Map<String, String> headers, Map<String, String> params, HttpCallback callback) {
        Request request = buildRequest(url, headers, params, HttpMethodType.POST);
        request(request, callback);
    }

    private Request buildRequest(String url, Map<String, String> headers, Map<String, String> params, HttpMethodType type) {
        Request.Builder builder = new Request.Builder();
        addHeader(headers, builder);
        builder.url(url);
        if (type == HttpMethodType.GET) {
            builder.get();
        } else if (type == HttpMethodType.POST) {
            builder.post(buildRequestBody(params));
        } else if (type == HttpMethodType.POST_JSON) {
            builder.post(buildRequestBody(JSON.toJSONString(params, true)));
        } else if (type == HttpMethodType.POST_FILE) {
            builder.post(buildMultipartRequestBody(params));
        } else if (type == HttpMethodType.PUT) {
            builder.put(buildRequestBody(params));
        } else if (type == HttpMethodType.PUT_JSON) {
            builder.put(buildRequestBody(JSON.toJSONString(params, true)));
        } else if (type == HttpMethodType.DELETE) {
            builder.delete(buildRequestBody(params));
        } else if (type == HttpMethodType.DELETE_JSON) {
            builder.delete(buildRequestBody(JSON.toJSONString(params, true)));
        }
        return builder.build();
    }

    public static String jointUrl(String url, Map<String, String> params) {
        if (params != null && params.size() > 0) {
            Uri uri = Uri.parse(url);
            Uri.Builder b = uri.buildUpon();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                b.appendQueryParameter(entry.getKey(), entry.getValue());
            }
            return b.build().toString();
        }
        return url;
    }

    private Request.Builder addHeader(Map<String, String> headers, Request.Builder builder) {
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
            return builder;
        }
        return builder;
    }

    /**
     * 通过Map的键值对构建请求对象的body
     *
     * @param params Map
     * @return RequestBody
     */
    private RequestBody buildRequestBody(Map<String, String> params) {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null) {
            for (Map.Entry<String, String> entity : params.entrySet()) {
                builder.add(entity.getKey(), entity.getValue());
            }
        }
        return builder.build();
    }

    /**
     * 通过json字符串创建请求对象的body
     *
     * @param jsonString String
     * @return RequestBody
     */
    private RequestBody buildRequestBody(String jsonString) {
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
    }

    private MultipartBody buildMultipartRequestBody(Map<String, String> params) {
        MultipartBody.Builder multipartBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (params != null) {
            for (Map.Entry<String, String> entity : params.entrySet()) {
                multipartBuilder.addFormDataPart(entity.getKey(), entity.getValue());
            }
        }
        return multipartBuilder.build();
    }

    /**
     * 这个枚举用于指明是哪一种提交方式
     */
    private enum HttpMethodType {
        GET,
        POST,
        POST_JSON,
        POST_FILE,
        PUT,
        PUT_JSON,
        DELETE,
        DELETE_JSON
    }

}
