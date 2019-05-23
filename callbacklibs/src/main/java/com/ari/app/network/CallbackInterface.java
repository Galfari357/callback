package com.ari.app.network;

import android.support.annotation.Nullable;


public interface CallbackInterface {

    void onRequestSuccess(int requestId, @Nullable String rawData);

    void onRequestNotFound(int requestId);

    void onRequestFailure(int requestId, RequestManager.RequestFailureType failureType, String errorCode, String message);

}