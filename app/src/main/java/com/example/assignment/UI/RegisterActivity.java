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
import com.example.assignment.R;
import com.example.assignment.RegisterRequest;
import com.example.assignment.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private TextView toptext;
    private Button signup;
    private EditText fullname,email,password,cnfpassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.signup);
        toptext = findViewById(R.id.toptext);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(email.getText().toString())||TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(RegisterActivity.this,"First Register with some inputs",Toast.LENGTH_LONG).show();
                }
                RegisterRequest registerRequest=new RegisterRequest();
                registerRequest.setEmail(email.getText().toString());
                registerRequest.setPassword(email.getText().toString());
                registerUser(registerRequest);
            }
        });
    }

    public  void  registerUser(RegisterRequest registerRequest){
        Call<RegisterResponse> registerResponseCall= ApiClient.getService().registerUser(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(RegisterActivity.this,"Registeration is successful"+" "+response.body().getId(),Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    finish();

                }else{
                    Toast.makeText(RegisterActivity.this,"An error occured",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

                Toast.makeText(RegisterActivity.this,t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }
}


