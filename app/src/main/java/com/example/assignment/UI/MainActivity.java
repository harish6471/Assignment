package com.example.assignment.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.assignment.ApiClient;
import com.example.assignment.Modal.Datum;
import com.example.assignment.Modal.Root;
import com.example.assignment.PostAdapter;
import com.example.assignment.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    List<Datum> data=new ArrayList<>();
    PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton=findViewById(R.id.fb);
        recyclerView=findViewById(R.id.rc);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        postAdapter=new PostAdapter(data);
//        recyclerView.setAdapter(postAdapter);




        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateActivity.class));
                
            }
        });

        display();
    }

    private void display() {
        Call<Root> listdata= ApiClient.getService().getData();
        listdata.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {

                if(response.isSuccessful()){
                    PostAdapter postAdapter=new PostAdapter(response.body().getData());
                    recyclerView.setAdapter(postAdapter);


                }else{
                    Toast.makeText(MainActivity.this,"An error occured",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }
}