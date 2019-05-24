package com.ari.network;


import com.ari.model.Basev2;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST(ApiConfig.NEWS)
    Call<Basev2> get_news(
            @Field("device_id") String device_id,
            @Field("community_id") String community_id,
            @Field("data") String data
    );

    @FormUrlEncoded
    @POST(ApiConfig.HEADLINE)
    Call<Basev2> get_headlines(
            @Field("device_id") String device_id,
            @Field("community_id") String community_id,
            @Field("data") String data
    );
}
