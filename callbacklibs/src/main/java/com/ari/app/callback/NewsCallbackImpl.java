package com.ari.app.callback;

import android.content.Context;
import android.support.annotation.Nullable;

import com.ari.app.cryp.FTAes;
import com.ari.app.helper.DeviceHelper;
import com.ari.app.model.artikel.ListData;
import com.ari.app.network.CallbackInterface;
import com.ari.app.network.RequestManager;
import com.google.gson.Gson;

import static com.ari.app.helper.SecretKeyHelper.community_key;
import static com.ari.app.helper.SecretKeyHelper.encrypKey_key;

public class NewsCallbackImpl implements CallbackInterface, NewsUseCase {

    private NewsCallback listener;
    public String communityId;
    private Context context;
    public String deviceId ;

    public NewsCallbackImpl(Context context, NewsCallback listener) {
        this.context = context;
        this.listener = listener;
        deviceId = DeviceHelper.getDeviceID(context);
        communityId = community_key();
    }

    @Override
    public void onRequestSuccess(int requestId, @Nullable String rawData) {

        Gson gson = new Gson();
        ListData listData = gson.fromJson(rawData, ListData.class);
        if (listData != null) {
            listener.onNewsSuccess(listData);
        }

    }

    @Override
    public void onRequestNotFound(int requestId) {

    }

    @Override
    public void onRequestFailure(int requestId, RequestManager.RequestFailureType failureType, String errorCode, String message) {
//        listener.onAddCardFailure(message);
    }

    @Override
    public void executeGetNewsAll(String keyword, String email, int page, int page_size, String type){
        String jsonData = "{\n" +
                " \"page\": \"" + page + "\",\n" +
                " \"page_size\": \"" + page_size + "\",\n" +
                " \"device_id\": \"" + deviceId + "\",\n" +
                " \"community_id\": \"" + communityId + "\",\n" +
                " \"type\": \"" + type + "\",\n" +
                " \"email\": \"" + email + "\",\n" +
                " \"keyword\": \"" + keyword + "\"\n" +
                "}";

        String data = FTAes.encrypt(jsonData, encrypKey_key());
        RequestManager.getNews(context, new NewsCallbackImpl(context, listener), deviceId, communityId, data, 0);
    }
}
