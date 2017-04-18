package com.hezy.live.persistence;


import android.content.SharedPreferences;
import android.util.Log;

public class Preferences {

    private static final String tag = Preferences.class.getSimpleName();

    private static final String PREFERENCE_TOKEN = "token";

    private static final String PREFERENCE_USER_ID = "uid";
    private static final String PREFERENCE_USER_NAME = "name";
    private static final String PREFERENCE_USER_AVATAR = "avatar";
    private static final String PREFERENCE_USER_GENDER = "gender";
    private static final String PREFERENCE_USER_MOBILE = "mobile";
    private static final String PREFERENCE_USER_SIGN = "sign";
    private static final String PREFERENCE_USER_TYPE = "type";

    private static final String PREFERENCE_IMAGE_DOMAIN = "image";
    private static final String PREFERENCE_VIDEO_DOMAIN = "video";
    private static final String PREFERENCE_DOWNLOAD_DOMAIN = "download";
    private static final String PREFERENCE_COOPERATION_DOMAIN = "cooperation";



    public static String getCooperationDomain(SharedPreferences prefs) {
        return prefs.getString(PREFERENCE_COOPERATION_DOMAIN, null);
    }

    public static void setCooperationDomain(SharedPreferences prefs, String cooperation) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREFERENCE_COOPERATION_DOMAIN, cooperation);
        if (!editor.commit()) {
            Log.d(tag, "cooperation save failure");
        } else {
            Log.d(tag, "cooperation save success");
        }
    }

    public static String getDownloadDomain(SharedPreferences prefs) {
        return prefs.getString(PREFERENCE_DOWNLOAD_DOMAIN, null);
    }

    public static void setDownloadDomain(SharedPreferences prefs, String download) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREFERENCE_DOWNLOAD_DOMAIN, download);
        if (!editor.commit()) {
            Log.d(tag, "download save failure");
        } else {
            Log.d(tag, "download save success");
        }
    }

    public static String getVideoDomain(SharedPreferences prefs) {
        return prefs.getString(PREFERENCE_VIDEO_DOMAIN, null);
    }

    public static void setVideoDomain(SharedPreferences prefs, String video) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREFERENCE_VIDEO_DOMAIN, video);
        if (!editor.commit()) {
            Log.d(tag, "video save failure");
        } else {
            Log.d(tag, "video save success");
        }
    }

    public static String getImageDomain(SharedPreferences prefs) {
        return prefs.getString(PREFERENCE_IMAGE_DOMAIN, null);
    }

    public static void setImageDomain(SharedPreferences prefs, String image) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREFERENCE_IMAGE_DOMAIN, image);
        if (!editor.commit()) {
            Log.d(tag, "image save failure");
        } else {
            Log.d(tag, "image save success");
        }
    }

    public static String getUserAvatar(SharedPreferences prefs) {
        return prefs.getString(PREFERENCE_USER_AVATAR, null);
    }

    public static void setUserAvatar(SharedPreferences prefs, String avatar) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREFERENCE_USER_AVATAR, avatar);
        if (!editor.commit()) {
            Log.d(tag, "User avatar save failure");
        } else {
            Log.d(tag, "User avatar save success");
        }
    }

    public static String getUserMobile(SharedPreferences prefs) {
        return prefs.getString(PREFERENCE_USER_MOBILE, null);
    }

    public static void setUserMobile(SharedPreferences prefs, String mobile) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREFERENCE_USER_MOBILE, mobile);
        if (!editor.commit()) {
            Log.d(tag, "User mobile save failure");
        } else {
            Log.d(tag, "User mobile save success");
        }
    }

    public static String getUserSign(SharedPreferences prefs) {
        return prefs.getString(PREFERENCE_USER_SIGN, null);
    }

    public static void setUserSign(SharedPreferences prefs, String userName) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREFERENCE_USER_SIGN, userName);
        if (!editor.commit()) {
            Log.d(tag, "User sign save failure");
        } else {
            Log.d(tag, "User sign save success");
        }
    }

    public static String getUserGender(SharedPreferences prefs) {
        return prefs.getString(PREFERENCE_USER_GENDER, null);
    }

    public static void setUserGender(SharedPreferences prefs, String userGender) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREFERENCE_USER_GENDER, userGender);
        if (!editor.commit()) {
            Log.d(tag, "User gender save failure");
        } else {
            Log.d(tag, "User gender save success");
        }
    }

    public static String getUserName(SharedPreferences prefs) {
        return prefs.getInt(PREFERENCE_USER_NAME, 0) + "".replace("0", "");
//        return prefs.getString(PREFERENCE_USER_NAME, "");
    }

    public static void setUserName(SharedPreferences prefs, String userName) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREFERENCE_USER_NAME, userName);
        if (!editor.commit()) {
            Log.d(tag, "User name save failure");
        } else {
            Log.d(tag, "User name save success");
        }
    }

    public static String getUserId(SharedPreferences prefs) {
        return prefs.getString(PREFERENCE_USER_ID, null);
    }

    public static void setUserId(SharedPreferences prefs, String userId) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREFERENCE_USER_ID, userId);
        if (!editor.commit()) {
            Log.d(tag, "User id save failure");
        } else {
            Log.d(tag, "User id save success");
        }
    }

    public static String getToken(SharedPreferences prefs) {
        return prefs.getString(PREFERENCE_TOKEN, null);
    }

    public static void setToken(SharedPreferences prefs, String token) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREFERENCE_TOKEN, token);
        if (!editor.commit()) {
            Log.d(tag, "Token save failure");
        } else {
            Log.d(tag, "Token save success");
        }
    }

    public static void clear(SharedPreferences prefs) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear().apply();
    }

    public static int getUserType(SharedPreferences prefs) {
        return prefs.getInt(PREFERENCE_USER_TYPE, 1);
    }

    public static void setUserType(SharedPreferences prefs, int type) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(PREFERENCE_USER_NAME, type);
        if (!editor.commit()) {
            Log.d(tag, "User type save failure");
        } else {
            Log.d(tag, "User type save success");
        }
    }


}
