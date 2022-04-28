package com.example.assignment.UI;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment.ApiClient;
import com.example.assignment.R;
import com.example.assignment.UserRequest;
import com.example.assignment.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateActivity extends AppCompatActivity {

    EditText name;
    EditText job;
    Button create;
    TextView n1;
    TextView j1;
    TextView id1;
    TextView createdat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        name=findViewById(R.id.user);
        job=findViewById(R.id.job);
        create=findViewById(R.id.create);
        n1=findViewById(R.id.n1);
        j1=findViewById(R.id.j1);
        id1=findViewById(R.id.id1);
        createdat=findViewById(R.id.cretedat);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(job.getText().toString())) {
                    Toast.makeText(CreateActivity.this, "First Register with some inputs", Toast.LENGTH_LONG).show();
                }
                UserRequest userRequest = new UserRequest();
                userRequest.setName(name.getText().toString());
                userRequest.setJob(job.getText().toString());
                showUser(userRequest);
            }
        });




    }

    private void showUser(UserRequest userRequest) {
        Call<UserResponse> userResponseCall= ApiClient.getService().showUser(userRequest);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()){
                    n1.setText(response.body().getName());
                    j1.setText(response.body().getJob());
                    id1.setText(response.body().getId());
                    createdat.setText(response.body().getCreatedAt());


                }else{
                    Toast.makeText(CreateActivity.this,"An error occured",Toast.LENGTH_LONG).show();


                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(CreateActivity.this,t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }


}