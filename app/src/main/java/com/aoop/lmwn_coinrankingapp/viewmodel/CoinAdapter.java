package com.aoop.lmwn_coinrankingapp.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aoop.lmwn_coinrankingapp.R;
import com.aoop.lmwn_coinrankingapp.model.coinData.CoinLists;
import com.bumptech.glide.Glide;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

public class CoinAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int COIN_NORMAL = 0;
    private static final int COIN_WITH_URL = 1;

    private Context context;
    private CoinLists coinLists;

    public CoinAdapter(Context context, CoinLists coinLists) {
        this.context = context;
        this.coinLists = coinLists;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coin_list_item_left,parent,false);
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.coin_list_item_right,parent,false);
        if(viewType == COIN_NORMAL)
            return new CoinViewHolder(view);
        else if(viewType == COIN_WITH_URL)
            return new CoinViewHolder2(view2);
        else
            return new CoinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==COIN_WITH_URL){
            CoinViewHolder2 holder2= (CoinViewHolder2)holder;
            holder2.coinName.setText(coinLists.getCoins().get(position).getName());
            holder2.coinUrl.setText(coinLists.getCoins().get(position).getCoinrankingUrl());
            String check = coinLists.getCoins().get(position).getIconUrl();
            String uriCoin = coinLists.getCoins().get(position).getCoinrankingUrl();
            if(check.contains(".svg")){
                GlideToVectorYou.init()
                        .with(context)
                        .load(Uri.parse(coinLists.getCoins().get(position).getIconUrl()), holder2.coinIcon);
            }else{
                Glide.with(context)
                        .load(coinLists.getCoins().get(position).getIconUrl())
                        .into(holder2.coinIcon);
            }
            holder2.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(uriCoin));
                    context.startActivity(i);
                }
            });
        }else{
            CoinViewHolder holder1= (CoinViewHolder)holder;
            holder1.coinName.setText(coinLists.getCoins().get(position).getName());
            holder1.coinSymbol.setText(coinLists.getCoins().get(position).getSymbol());
            String check = coinLists.getCoins().get(position).getIconUrl();
            if(check.contains(".svg")) {
                GlideToVectorYou.init()
                        .with(context)
                        .load(Uri.parse(coinLists.getCoins().get(position).getIconUrl()), holder1.coinIcon);
            }else {
                Glide.with(context)
                        .load(coinLists.getCoins().get(position).getIconUrl())
                        .into(holder1.coinIcon);
            }
        }


    }

    @Override
    public int getItemViewType(int position) {
        if (position%5==4)
            return COIN_WITH_URL;
        else
            return COIN_NORMAL;
    }

    @Override
    public int getItemCount() {
        return coinLists.getCoins().size();
    }

    public class CoinViewHolder extends RecyclerView.ViewHolder {
        public TextView coinName, coinSymbol;
        public ImageView coinIcon;
        public CoinViewHolder(View itemView) {
            super(itemView);
            coinIcon = (ImageView) itemView.findViewById(R.id.coinIcon);
            coinName = (TextView) itemView.findViewById(R.id.coinName);
            coinSymbol = (TextView) itemView.findViewById(R.id.coinSymbol);
        }
    }

    public class CoinViewHolder2 extends RecyclerView.ViewHolder {
        public TextView coinName, coinUrl;
        public ImageView coinIcon;
        public CoinViewHolder2(View itemView) {
            super(itemView);
            coinIcon = (ImageView) itemView.findViewById(R.id.coinIcon);
            coinName = (TextView) itemView.findViewById(R.id.coinName);
            coinUrl = (TextView) itemView.findViewById(R.id.coinRankingUrl);
        }
    }


}
