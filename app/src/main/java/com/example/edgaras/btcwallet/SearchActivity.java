package com.example.edgaras.btcwallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SearchActivity extends AppCompatActivity {
    public Button btnNewOnSearchAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        btnNewOnSearchAct = (Button) findViewById(R.id.search_btn_new_coin);

        btnNewOnSearchAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SearchToNewCoin = new Intent(SearchActivity.this, NewCoinActivity.class);
                startActivity(SearchToNewCoin);
            }
        });


    }
}
