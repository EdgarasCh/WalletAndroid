package com.example.edgaras.btcwallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class NewCoinActivity extends AppCompatActivity {

    public Button btnNewCoinRegister;
    EditText etID, etName, etAbbrevition;
    private RadioGroup rbGroupCap;
    private RadioButton rbChekedCap;
    private CheckBox cbBithumb, cbBittrex, cbPoloniex, cbBinance, cbOthers;
    Spinner spinner;
    Coin coinToAdd;

    String spinnerItems[] = {"$0-$0.01", "$0.01-$1", "$1-$100", "$100-$1000",">$1000"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_coin);

        btnNewCoinRegister = (Button)findViewById(R.id.new_coin_btn_create);
        etID = (EditText) findViewById(R.id.new_coin_Id);
        etName= (EditText) findViewById(R.id.new_coin_name);
        etAbbrevition = (EditText) findViewById(R.id.new_coin_abbr);

        rbGroupCap = (RadioGroup) findViewById(R.id.new_rb_group_cap);

        cbBithumb = (CheckBox) findViewById(R.id.new_cb_bithumb);
        cbBittrex= (CheckBox) findViewById(R.id.new_cb_bittrex);
        cbPoloniex = (CheckBox) findViewById(R.id.new_ch_Poloniex);
        cbBinance = (CheckBox) findViewById(R.id.new_ch_Binance);
        cbOthers = (CheckBox) findViewById(R.id.new_cb_other);

        spinner = (Spinner) findViewById(R.id.new_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);


        btnNewCoinRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int id = Integer.parseInt(etID.getText().toString());
                String name = etName.getText().toString();
                String abbreviation = etAbbrevition.getText().toString();
                String rbCap= "";
                String cbMarket = "";
                String spinnerTextPrice = "";

                // get selected radio button from radioGroup
                int selectedId = rbGroupCap.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                rbChekedCap = (RadioButton) findViewById(selectedId);
                rbCap = rbChekedCap.getText().toString();

                StringBuffer result = new StringBuffer();
                result.append("Bithumb").append(cbBithumb.isChecked());
                result.append("\nBittrex").append(cbBittrex.isChecked());
                result.append("\nPoloniex").append(cbPoloniex.isChecked());
                result.append("\nBinance").append(cbBinance.isChecked());
                result.append("\nKt.").append(cbOthers.isChecked());
                cbMarket = result.toString();

                spinnerTextPrice = spinner.getSelectedItem().toString();

                coinToAdd = new Coin();
                coinToAdd.setId(id);
                coinToAdd.setName(name);
                coinToAdd.setAbbreviation(abbreviation);
                coinToAdd.setPriceRange(spinnerTextPrice);
                coinToAdd.setMarket(cbMarket);
                coinToAdd.setCap(rbCap);


                toastMessage ("Id:" + id +"\n"+
                        " name: " + name +"\n"+
                        " abbreviation: " + abbreviation +"\n"+
                        " cap:" + rbCap +"\n"+
                        " market: " + cbMarket +"\n"+
                        " priceRange: " + spinnerTextPrice);

                Intent LoginToRegister = new Intent(NewCoinActivity.this, SearchActivity.class);
                startActivity(LoginToRegister);


            }
        });

    }
    public void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
