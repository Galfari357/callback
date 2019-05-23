package com.ari.app;

import android.content.Context;

public class DataConfig {

    public static String secretKey;
    public static String apiKey;
    public static String communityId;
    public static String certificateKey;
    public static Context context;
    public static Context mActivity;

    public static String getSecretKey() {
        return secretKey;
    }

    public static void setSecretKey(String secretKey) {
        DataConfig.secretKey = secretKey;
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static void setApiKey(String apiKey) {
        DataConfig.apiKey = apiKey;
    }

    public static String getCertificateKey() {
        return DataConfig.certificateKey;
    }

    public static void setCertificateKey(String certificateKey) {
        DataConfig.certificateKey = certificateKey;
    }

    public static String getCommunityId() {
        return communityId;
    }

    public static void setCommunityId(String communityId) {
        DataConfig.communityId = communityId;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        DataConfig.context = context;
    }

    public static Context getmActivity() {
        return mActivity;
    }

    public static void setmActivity(Context activity) {
        mActivity = activity;
    }

}
