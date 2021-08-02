package com.sjb.loginapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {


    EditText regisEmail,regisPass,regisName;
    Button regisBtn;
    TextView signIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        regisName = findViewById(R.id.regis_nameET);
        regisEmail = findViewById(R.id.regis_emialET);
        regisPass = findViewById(R.id.regis_passwordET);
        regisBtn = findViewById(R.id.regisIn_BTN);
        signIn = findViewById(R.id.SignIn);


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    
    public void registerUser(View view) {

        String username = regisName.getText().toString();
        String password = regisPass.getText().toString();
        String email = regisEmail.getText().toString();

        Call<registerResponse> call = RetrofitClient.getInstance()
                .getApi()
                .register(username,email,password);

        call.enqueue(new Callback<registerResponse>() {
            @Override
            public void onResponse(Call<registerResponse> call, Response<registerResponse> response) {
                registerResponse registerResponse=response.body();
                if(response.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegisterActivity.this, "Register Fail", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<registerResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Failure in Register", Toast.LENGTH_SHORT).show();
            }
        });
    }
}