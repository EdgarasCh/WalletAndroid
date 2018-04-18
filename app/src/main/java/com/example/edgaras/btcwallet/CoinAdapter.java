package com.example.edgaras.btcwallet;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Edgaras on 4/18/2018.
 */

public class CoinAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private LayoutInflater inflater;
    private List <Coin> coins = Collections.emtptyList();
    private Coin currentCoin;
    public static final String ENTRY = "com.example.edgaras.btcwallet";

    //konstruktorius susieti esama langą ir perduoti sąrašą coinui is DB

    public CoinAdapter(Context context, List<Coin> coins){
        this.context = context;
        this.coins = coins;
        inflater = LayoutInflater.from(context);
    }

    @Override
    //inflate the layout when viewholder is created
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.container_coin, parent, false);
        MyHolder holder = new MyHolder (view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //get current position of item in
        // recyclerview to bind data and assign value from list
        MyHolder myHolder = (MyHolder) holder;
        currentCoin = coins.get(position);
        myHolder.tvName.setText(currentCoin.getName());
        myHolder.tvAbbr.setText(currentCoin.getAbbreviation());
        myHolder.tvPrice.setText(currentCoin.getPriceRange());
    }

    @Override
    public int getItemCount() {

        return coins.size();
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvName, tvAbbr, tvPrice;



        public MyHolder(View itemView){
            super(itemView);
            tvName = (TextView)itemView.findViewById(R.id.pavadinimas_container);
            tvAbbr = (TextView)itemView.findViewById(R.id.trumpinys_container);
            tvPrice = (TextView)itemView.findViewById(R.id.kaina_container);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int itemPosition = getAdapterPosition();
            int coinID = coins.get(itemPosition).getId();

            // TODO: siųsti pasirinkto pokemono objektą vietoj id
            Coin coinsas = coins.get(itemPosition);

            Intent intent = new Intent (context, NewCoinActivity.class);

            intent.putExtra(ENTRY, coinID);
            context.startActivity(intent);


        }
    }
}













