package com.aoop.lmwn_coinrankingapp.model.coinData;

import com.google.gson.annotations.SerializedName;

public class Data {

	@SerializedName("data")
	private CoinLists coinLists;

	public void setData(CoinLists data){
		this.coinLists = data;
	}

	public CoinLists getData(){
		return coinLists;
	}

}