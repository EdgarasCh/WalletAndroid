package com.example.edgaras.btcwallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLoginInLoginAct;
        Button btnRegisterInLoginAct;
        btnLoginInLoginAct = (Button) findViewById(R.id.login_btn_login);
        btnRegisterInLoginAct = (Button)findViewById(R.id.login_btn_register);

        final EditText etUsernameOnButton = (EditText) findViewById(R.id.login_name);
        final EditText etPasswordOnButton = (EditText) findViewById(R.id.login_password);
        etUsernameOnButton.setError(null);
        etPasswordOnButton.setError(null);

        final CheckBox cbRememberMe = (CheckBox) findViewById(R.id.login_cb_remember);
        final User user = new User(LoginActivity.this);
        cbRememberMe.setChecked(user.isRememberedForLogin()); //galima sitaip naudoti, bet nebutina

        if(user.isRememberedForLogin()){
            etUsernameOnButton.setText(user.getUsernameForLogin(), TextView.BufferType.EDITABLE);
            etPasswordOnButton.setText(user.getPasswordForLogin(), TextView.BufferType.EDITABLE);
        } else {
            etUsernameOnButton.setText("", TextView.BufferType.EDITABLE);
            etPasswordOnButton.setText("", TextView.BufferType.EDITABLE);
        }


         btnRegisterInLoginAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginToRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(LoginToRegister);
            }
        });


         btnLoginInLoginAct.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 boolean cancel = false;
                 if (!Validation.isValidCredentials(etUsernameOnButton.getText().toString())) {
                     cancel = true;
                     etUsernameOnButton.requestFocus();
                     etUsernameOnButton.setError(getResources().getString(R.string.login_invalid));
                     //Toast.makeText(LoginActivity.this, getResources().getString(R.string.login_invalid_username),
                     //Toast.LENGTH_SHORT).show();
                 } else if (!Validation.isValidCredentials(etPasswordOnButton.getText().toString())) {
                     cancel = true;
                     etPasswordOnButton.requestFocus();
                     etPasswordOnButton.setError(getResources().getString(R.string.login_invalid));
                     // Toast.makeText(LoginActivity.this, getResources().getString(R.string.login_invalid_password),
                     // Toast.LENGTH_SHORT).show();
                 }
                 if (!cancel){
                    Toast.makeText(LoginActivity.this,
                            "Prisijungimo vardas:"+etUsernameOnButton.getText().toString()+"\n"+
                            "Prisijungimo slaptažodis"+etPasswordOnButton.getText().toString(),
                            Toast.LENGTH_SHORT).show();

                    user.setUsernameForLogin(etUsernameOnButton.getText().toString());
                    user.setPasswordForLogin(etPasswordOnButton.getText().toString());
                    if(cbRememberMe.isChecked()){
                       user.setRememberMeKeyForLogin(true);
                    } else {
                        user.setRememberMeKeyForLogin(false);
                    }


                    Intent LoginToSearch = new Intent(LoginActivity.this, SearchActivity.class);
                    startActivity(LoginToSearch);
                 }


             }
         });


    }
}
