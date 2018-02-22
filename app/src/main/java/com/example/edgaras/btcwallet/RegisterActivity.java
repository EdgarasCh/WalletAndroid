package com.example.edgaras.btcwallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {
    public Button btnRegsiterOnRegisterAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegsiterOnRegisterAct = (Button)findViewById(R.id.register_btn_register);

        btnRegsiterOnRegisterAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginToRegister = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(LoginToRegister);
            }
        });

    }
}
