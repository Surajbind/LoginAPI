package com.sjb.loginapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    EditText loginEmail,loginPassword;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginEmail = findViewById(R.id.login_emialET);
        loginPassword = findViewById(R.id.login_passwordET);
        loginBtn = findViewById(R.id.login_BTN);

    }

    public void loginUser(View view) {
        String password = loginPassword.getText().toString();
        String email = loginEmail.getText().toString();

        Call<loginResponse> call = RetrofitClient.getInstance()
                .getApi()
                .login(email,password);


        call.enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                loginResponse loginResponse = response.body();

                if(response.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<loginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Failed While Login", Toast.LENGTH_SHORT).show();
            }
        });

    }
}