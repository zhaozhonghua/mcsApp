package com.hezy.live;

import android.util.Log;

import com.hezy.live.util.OkHttpUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class ApiClient {

    private static final String tag = ApiClient.class.getSimpleName();

    private static final String API_DOMAIN_NAME = "apitest.haierzhongyou.com";
    private static final String API_DOMAIN = "kindertest.haierzhongyou.com";


    private static final String URL_API_USER = "/liveapp/user";
    private static final String URL_API_TEACHER = "/liveapp/msjt/teacher";
    private static final String URL_API_TARGET = "/liveapp/msjt/target";
    private static final String URL_API_COURSERA = "/liveapp/msjt/curriculum";
    private static final String URL_API_CONFIG = "/liveapp/config";

    private static final String URL_API_AGROA = "/agora/key";
    private static final String URL_API_RESOURCE = "/resource";

    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Token";

    private final static OkHttpClient M_OK_HTTP_CLIENT = new OkHttpClient().newBuilder()
            .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .retryOnConnectionFailure(true)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build();

    private final OkHttpUtil httpUtil;

    private Response execute(Request request) throws IOException {
        return M_OK_HTTP_CLIENT.newCall(request).execute();
    }

    private void enqueue(Request request, Callback responseCallback) {
        M_OK_HTTP_CLIENT.newCall(request).enqueue(responseCallback);
    }

    private static String jointParam(String url, String name, String value) {
        return url + "?" + name + "=" + value;
    }

    private static String jointParam(String url, Map<String, String> values) {
        StringBuffer result = new StringBuffer();
        result.append(url).append("?");
        Set<String> keys = values.keySet();
        for (String key : keys) {
            result.append(key).append("=").append(values.get(key)).append("&");
        }
        return result.toString().substring(0, result.toString().length() - 1);
    }

    private static class SingletonHolder {
        private static ApiClient instance = new ApiClient();
    }

    public static ApiClient getInstance() {
        return SingletonHolder.instance;
    }

    private ApiClient() {
        httpUtil = OkHttpUtil.getInstance();
    }

    private String jointBaseUrl(String apiName) {
        return "http://" + API_DOMAIN_NAME + "/dz" + apiName;
    }

    private Request.Builder addHeader(Request.Builder builder, String token){
        return builder.addHeader(AUTHORIZATION_PROPERTY, token == null ? "" : AUTHENTICATION_SCHEME + " " + token);
    }

    private Request get(String url, String token) {
        return addHeader(new Request.Builder().url(url).get(), token).build();
    }

    private Request post(RequestBody requestBody, String url, String token) {
        return addHeader(new Request.Builder().url(url).post(requestBody), token).build();
    }

    private Request put(RequestBody requestBody, String url, String token) {
        return addHeader(new Request.Builder().url(url).put(requestBody), token).build();
    }

    private Request delete(RequestBody requestBody, String url, String token) {
        return addHeader(new Request.Builder().url(url).delete(requestBody), token).build();
    }

    //  登录
    public void signIn(RequestBody requestBody, Callback responseCallback) {
        Log.d(tag, API_DOMAIN + "/user/login");
        Request request = post(requestBody, "http://" + API_DOMAIN + "/user/login", null);
//        Request request = post(requestBody, jointBaseUrl(URL_API_USER) + "/login", null);
        enqueue(request, responseCallback);
    }

    public void encounters(int status, String token, Callback responseCallback){
        Log.d(tag, API_DOMAIN + "/encounter");
        Request request = get("http://" + API_DOMAIN + "/encounter?status="+status, token);
        enqueue(request, responseCallback);
    }

    public void patientRegister(RequestBody requestBody, String userId, String token, Callback responseCallback){
        Log.d(tag, API_DOMAIN + "/patient/" + userId);
        Request request = post(requestBody, "http://" + API_DOMAIN + "/encounter/patient/" + userId, token);
        enqueue(request, responseCallback);
    }

    public void doctorUpdateEncounter(RequestBody requestBody, String id, String doctorId, String token, Callback responseCallback){
        Log.d(tag, API_DOMAIN + "/"+id+"/doctor/" + doctorId);
        Request request = put(requestBody, "http://" + API_DOMAIN + "/encounter/"+id+"/doctor/" + doctorId, token);
        enqueue(request, responseCallback);
    }

    //  修改密码
    public void passwordModify(RequestBody requestBody, String userId, String token, Callback responseCallback) {
        Request request = put(requestBody, jointBaseUrl(URL_API_USER) + "/" + userId + "/password", token);
        enqueue(request, responseCallback);
    }

    //  修改个人信息
    public void profileModify(RequestBody requestBody, String userId, String token, Callback responseCallback) {
        Request request = put(requestBody, jointBaseUrl(URL_API_USER) + "/" + userId, token);
        enqueue(request, responseCallback);
    }

    //  获取某天老师直播課時列表
    public void liveLessons(long date, String teacherId, String token, Callback responseCallback) {
        Request request = get(jointBaseUrl(URL_API_TEACHER) + "/" + teacherId + "/livelesson/day/" + date, token);
        enqueue(request, responseCallback);
    }

    //  创建快速直播课程
    public void courseraCreate(RequestBody requestBody, String teacherId, String token, Callback responseCallback) {
        Request request = post(requestBody, jointBaseUrl(URL_API_TEACHER) + "/" + teacherId + "/quicklivelesson", token);
        enqueue(request, responseCallback);
    }

    public void qiniuToken(String type, String token, Callback responseCallback) {
        Request request = get(jointBaseUrl(URL_API_RESOURCE) + "/uploadtoken/" + type, token);
        enqueue(request, responseCallback);
    }

    public void targetTheme(String targetId, String token, Callback responseCallback) {
        Request request = get(jointBaseUrl(URL_API_TARGET) + "/" + targetId + "/theme", token);
        enqueue(request, responseCallback);
    }

    //  获取声网密钥
    public void agora(HashMap<String, String> values, String token, Callback responseCallback) {
        Request request = get(jointParam(jointBaseUrl(URL_API_AGROA), values), token);
        enqueue(request, responseCallback);
    }

}
