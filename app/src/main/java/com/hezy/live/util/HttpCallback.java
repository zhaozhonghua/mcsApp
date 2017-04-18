package com.hezy.live.util;

/**
 * Created by whatisjava on 17-1-3.
 */

import com.google.gson.internal.$Gson$Types;
import com.hezy.live.BaseException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 基本的回调
 */
public abstract class HttpCallback<Entity> {

    /**
     * type用于方便JSON的解析
     */
    public Type mType;

    /**
     * 构造的时候获得type的class
     */
    public HttpCallback() {
        mType = getSuperclassTypeParameter(getClass());
    }

    /**
     * 把type转换成对应的类
     *
     * @param subclass
     * @return
     */
    private static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }

    /**
     * 请求开始前调用
     */
    public abstract void onStart();

    /**
     * 请求成功而且没有错误的时候调用
     *
     * @param entity Entity
     */
    public abstract void onSuccess(Entity entity);

    /**
     * 请求失败调用（网络问题）
     *
     * @param exception BaseException
     */
    public abstract void onFailure(BaseException exception);

    /**
     * 请求成功但是有错误的时候调用，例如json解析错误等
     *
     * @param errorCode int
     * @param exception BaseException
     */
    public abstract void onError(int errorCode, BaseException exception);

    /**
     * 请求结束后调用（无论是否成功）
     */
    public abstract void onFinish();
}