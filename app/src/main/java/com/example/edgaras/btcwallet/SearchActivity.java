package com.example.edgaras.btcwallet;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertController;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.view.Menu;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Collection;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    public Button btnNewOnSearchAct;
    SearchView searchView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setTitle(R.string.search_label);


        btnNewOnSearchAct = (Button) findViewById(R.id.search_btn_new_coin);

        btnNewOnSearchAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SearchToNewCoin = new Intent(SearchActivity.this, NewCoinActivity.class);
                startActivity(SearchToNewCoin);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        //adds item to actionbar
        getMenuInflater().inflate(R.menu.search, menu);
        //get search item from actionbar and get search service
        MenuItem searchItem = menu.findItem(R.id.actionSearch);
        SearchManager searchManager = (SearchManager) SearchActivity.this.getSystemService(Context.SEARCH_SERVICE);

        if (searchItem!=null){
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView!=null){
            searchView.setSearchableInfo(searchManager.getSearchableInfo(SearchActivity.this.getComponentName()));
            searchView.setIconified(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        return super.onOptionsItemSelected(item);
    }

    @Override
    //every time when you press search button an actvity is recreated which in turn
    //calls this function
    protected void onNewIntent (Intent intent){
        //get search query and create object of class AsyncFetch
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
           String query = intent.getStringExtra(SearchManager.QUERY);
           if (searchView != null) {
                searchView.clearFocus();
           }
           new AsyncFetch(query).execute();
        }
    }

    class AsyncFetch extends AsyncTask<String, String, String>{
        ProgressDialog progressDialog = new ProgressDialog(SearchActivity.this);
        String searchQuery;
        List<Coin> coinai = Collection.emptyList();

        public AsyncFetch(String searchQuery){
            this.searchQuery = searchQuery;
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog.setMessage("Prašome palaukti");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            DatabaseSQLite db = new DatabaseSQLite(SearchActivity.this);

            coinai = db.getAllCoins();
            if (coinai.isEmpty()){
                return "norows";
            } else {
                return "rows";
            }
        }

        @Override
        protected  void onPostExecute (String result){
            progressDialog.dismiss();
            if(result.equals("norows")){
                Toast.makeText(SearchActivity.this, "Pagal paiešką nerasta duomenų", Toast.LENGTH_SHORT).show();
            } else {
                //setup and hand over list coins to recyclerview
                RecyclerView rvCoins = (RecyclerView) findViewById(R.id.search_coin_list);
                CoinAdapter coinAdapter = new CoinAdapter (SearchActivity.this, coinai);
                rvCoins.setAdapter(coinAdapter);
                rvCoins.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
            }
        }
    }

  //Activity end
}











