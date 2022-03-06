package com.aoop.lmwn_coinrankingapp.model.service;

import com.aoop.lmwn_coinrankingapp.model.coinData.Data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CoinsService {
    @GET("coins")
    Call<Data> getData(@Query("api_key")String apiKey);
}
