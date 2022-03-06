package com.aoop.lmwn_coinrankingapp.model.coinData;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CoinLists {

	@SerializedName("coins")
	private List<Coin> coins;

	public void setCoins(List<Coin> coins){
		this.coins = coins;
	}

	public List<Coin> getCoins(){
		return coins;
	}
}