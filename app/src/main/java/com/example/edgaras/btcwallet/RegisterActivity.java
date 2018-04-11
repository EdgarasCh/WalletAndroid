package com.example.edgaras.btcwallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    public Button btnRegsiterOnRegisterAct;

    EditText username;
    EditText password;
    EditText email;

    @Override

        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle(R.string.register_label);

        username = (EditText) findViewById(R.id.register_name);
        password = (EditText) findViewById(R.id.register_password);
        email    = (EditText) findViewById(R.id.register_email);

        btnRegsiterOnRegisterAct = (Button)findViewById(R.id.register_btn_register);

        btnRegsiterOnRegisterAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!Validation.isValidCredentials(username.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Netinkamas vartotojo vardas arba slaptažodis",
                            Toast.LENGTH_LONG).show();
                } else if (!Validation.isValidCredentials(password.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Netinkamas vartotojo vardas arba slaptažodis",
                            Toast.LENGTH_LONG).show();
                } else if (!Validation.isValidEmail(email.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Netinkami įvestas el. paštas",
                            Toast.LENGTH_LONG).show();
                } else {
                    DatabaseSQLite databaseSQLite = new DatabaseSQLite (getApplicationContext());
                    User userToSQLite = new User ("1",username.getText().toString(),
                                                    password.getText().toString(),
                                                    email.getText().toString());

                    databaseSQLite.addUser(userToSQLite);
                    List<User> usersList = databaseSQLite.getAllUsers();
                    for (User user:usersList){
                        Toast.makeText(getApplicationContext(), user.toString(), Toast.LENGTH_LONG).show();
                    }


                }

                Intent LoginToRegister = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(LoginToRegister);
            }
        });

    }
}

