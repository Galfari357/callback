package com.ari.network;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

import com.ari.app.BuildConfig;
import com.ari.app.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Cache;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ari.helper.SecretKeyHelper.default_key;

public class ApiClient {
    private static Retrofit retrofit = null;
    private static OkHttpClient okHttpClient = null;

    public static Retrofit getClient() {

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

//    public static Retrofit getClientUser(Context context) {
//        return retrofit(okhttpBuilder(context), ApiConfig.BASE_URL);
//    }

    public static ApiInterface createRequest(Context context) {
        return ApiClient.getClient(context, true).create(ApiInterface.class);
    }

    public static Retrofit getClient(Context context, boolean withheader) {
        return retrofit(okhttpBuilderWithHeader(context, withheader), ApiConfig.BASE_URL);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static SSLContext getSSLConfig(Context context) throws CertificateException, IOException,
            KeyStoreException, NoSuchAlgorithmException, KeyManagementException {

        // Loading CAs from an InputStream
        CertificateFactory cf = null;
        cf = CertificateFactory.getInstance("X.509");

        Certificate ca;
        // I'm using Java7. If you used Java6 close it manually with finally.
        try (InputStream cert = context.getResources().openRawResource(R.raw.wildcard)) {
            ca = cf.generateCertificate(cert);
        }

        // Creating a KeyStore containing our trusted CAs
        String keyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(null, null);
        keyStore.setCertificateEntry("ca", ca);

        // Creating a TrustManager that trusts the CAs in our KeyStore.
        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
        tmf.init(keyStore);

        // Creating an SSLSocketFactory that uses our TrustManager
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), null);

        return sslContext;
    }


    public static OkHttpClient.Builder okhttpBuilderWithHeader(Context context, final boolean withheader) {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        int cacheSize = 10 * 1024 * 1024; // 10 MB
        Cache cache = new Cache(context.getCacheDir(), cacheSize);
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(1);
        dispatcher.setMaxRequestsPerHost(1);

        okHttpBuilder
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .cache(cache)
                .dispatcher(dispatcher);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpBuilder.addInterceptor(interceptor);
        }

        try {
            okHttpBuilder.sslSocketFactory(getSSLConfig(context).getSocketFactory())
                    .build();

        } catch (KeyManagementException ex) {

        } catch (NoSuchAlgorithmException ex) {

        } catch (KeyStoreException ex) {

        } catch (CertificateException ex) {

        } catch (IOException ex) {

        }

        okHttpBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder ongoing = chain.request().newBuilder();
                if (withheader) {
                    ongoing.addHeader("Content-Type", "application/x-www-form-urlencoded");
                    ongoing.addHeader("api-key", default_key());
                }
                return chain.proceed(ongoing.build());
            }
        });
        return okHttpBuilder;
    }

    public static Retrofit retrofit(OkHttpClient.Builder okhttpBuilder, String baseUrl) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okhttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static RequestBody convert(Map<String, Object> map) {
        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                (new JSONObject(map)).toString());
    }
}
