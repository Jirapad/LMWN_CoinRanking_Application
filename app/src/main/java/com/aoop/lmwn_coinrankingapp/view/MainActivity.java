package com.aoop.lmwn_coinrankingapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.aoop.lmwn_coinrankingapp.R;
import com.aoop.lmwn_coinrankingapp.viewmodel.CoinAdapter;
import com.aoop.lmwn_coinrankingapp.model.coinData.CoinLists;
import com.aoop.lmwn_coinrankingapp.model.coinData.Data;
import com.aoop.lmwn_coinrankingapp.model.service.CoinsService;
import com.aoop.lmwn_coinrankingapp.model.service.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private CoinLists coinLists;
    private RecyclerView recyclerView;
    private CoinAdapter coinAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getCoins();
    }

    private void getCoins() {
        CoinsService coinsService = RetrofitInstance.getService();
        Call<Data> call = coinsService.getData(this.getString(R.string.api_key));
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Data coinsRanking = response.body();

                if(coinsRanking!=null && coinsRanking.getData()!=null){
                    coinLists = (CoinLists) coinsRanking.getData();
                    showOnRecycleView();
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });
    }

    private void showOnRecycleView() {
        recyclerView = (RecyclerView) findViewById(R.id.rvCoinLists);
        coinAdapter = new CoinAdapter(this,coinLists);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(coinAdapter);
    }

}