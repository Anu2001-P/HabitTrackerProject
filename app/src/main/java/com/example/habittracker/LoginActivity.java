package com.example.habittracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText uname,pwd;
    Button login;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            uname = (EditText) findViewById(R.id.username);
            pwd = (EditText) findViewById(R.id.password);
            login = (Button) findViewById(R.id.loginButton);

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (uname.getText().toString().equals("user") && pwd.getText().toString().equals("1234")) {
                        Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(LoginActivity.this, GoToPage.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(LoginActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }