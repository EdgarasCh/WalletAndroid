package com.example.edgaras.btcwallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    public Button btnRegsiterOnLoginAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnRegsiterOnLoginAct = (Button)findViewById(R.id.login_btn_register);

        btnRegsiterOnLoginAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginToRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(LoginToRegister);
            }
        });


    }
}
