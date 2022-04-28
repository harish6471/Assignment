package com.example.assignment.UI;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment.ApiClient;
import com.example.assignment.LoginRequest;
import com.example.assignment.LoginResponse;
import com.example.assignment.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity{
        private TextView toptext, conditions;
        private EditText email, password;
        private Button login;
        private  Button register;


        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.login);

            email = findViewById(R.id.email);
            password = findViewById(R.id.password);
            login = findViewById(R.id.login);
            register=findViewById(R.id.register);
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                }
            });

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(TextUtils.isEmpty(email.getText().toString())||TextUtils.isEmpty(password.getText().toString())){
                        Toast.makeText(LoginActivity.this,"First Register with some inputs",Toast.LENGTH_LONG).show();
                    }
                   LoginRequest loginRequest=new LoginRequest();
                    loginRequest.setEmail(email.getText().toString());
                    loginRequest.setPassword(email.getText().toString());
                    loginUser(loginRequest);
                }
            });


        }
        public  void loginUser(LoginRequest loginRequest){
            Call<LoginResponse> loginResponseCall= ApiClient.getService().loginUser(loginRequest);
            loginResponseCall.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(LoginActivity.this,"Login successful",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        finish();

                    }else{
                        Toast.makeText(LoginActivity.this,"An error occured",Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this,t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

                }
            });

        }
}