package com.ari.helper;

public class SecretKeyHelper {

    static {
        System.loadLibrary("ari");
    }

    public static native String getApiKey();

    public static native String getEncrypKey();

    public static native String getComunityId();

    public static native String getbase_url();

    public static String default_key() {
        return getApiKey();
    }

    public static String encrypKey_key() {
        return getEncrypKey();
    }

    public static String community_key() {
        return getComunityId();
    }

    public static String base_url_key() {
        return getbase_url();
    }
}
