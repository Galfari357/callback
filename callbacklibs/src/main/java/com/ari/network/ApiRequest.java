package com.ari.network;

import android.content.Context;
import android.util.Log;

import com.ari.cryp.FTAes;
import com.ari.model.Basev2;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ari.helper.SecretKeyHelper.encrypKey_key;
import static com.ari.network.ApiConfig.SUCCESS_CODE;
import static com.ari.network.ApiConfig.SUCCESS_CODE2;
import static com.ari.network.ApiConfig.SUCCESS_CODE3;

public class ApiRequest implements Callback<Basev2> {

    private String successStatusCode;
    private Context context;
    private CallbackInterface callback;
    private int requestId;

    ApiRequest(Context context, String successStatusCode, CallbackInterface callback, int requestId) {
        this.context = context;
        this.successStatusCode = successStatusCode;
        this.callback = callback;
        this.requestId = requestId;
    }

    @Override
    public void onResponse(Call<Basev2> call, Response<Basev2> response) {
        if (response.code() == Integer.parseInt(successStatusCode)) {
            if (response.body().getData() != null) {
                if (response.body().getStatus().equals(SUCCESS_CODE) || response.body().getStatus().equals(SUCCESS_CODE2) || response.body().getStatus().equals(SUCCESS_CODE3)) {
                    String data = FTAes.decrypt(response.body().getData().toString(), encrypKey_key());
                    onRequestSuccess(call, data);
                } else{
                    onRequestFailure(RequestManager.RequestFailureType.SERVICE_FAILURE, call, response.body().getStatus(), response.body().getMessage()+"");
                }

            } else {
                if (response.body().getStatus().equals(SUCCESS_CODE) || response.body().getStatus().equals(SUCCESS_CODE2) || response.body().getStatus().equals(SUCCESS_CODE3)) {
                    onRequestSuccess(call, response.body().getMessage()+"");
                } else{
                    onRequestFailure(RequestManager.RequestFailureType.SERVICE_FAILURE, call, response.body().getStatus(), response.body().getMessage()+"");
                }

            }

        } else {
            String message = "Server Error";
            onRequestFailure(RequestManager.RequestFailureType.HTTP_FAILURE, call, response.code() + "", message+"");
        }
    }

    @Override
    public void onFailure(Call<Basev2> call, Throwable t) {
        String message = "No Connection";
        if (requestId != 96) {
            onRequestFailure(RequestManager.RequestFailureType.NETWORK_FAILURE, call, "", message+"");
        }
    }

    private void onRequestCanceled() {
        Log.i("INFO", "request is canceled.");
    }

    private void onRequestFailure(RequestManager.RequestFailureType type, Call<Basev2> call, String errorCode, String errorMessage) {
        if (call.isCanceled()) {
            onRequestCanceled();
        } else {
            callback.onRequestFailure(requestId, type, errorCode, errorMessage+"");
        }
    }

    private void onRequestSuccess(Call<Basev2> call, String response) {

        if (call.isCanceled()) {
            onRequestCanceled();
        } else {
            callback.onRequestSuccess(requestId, response+"");
        }
    }
}
