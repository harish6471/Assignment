package com.example.assignment;

import com.example.assignment.Modal.Root;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @POST("api/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @POST("api/register")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);

    @POST("api/users")
    Call<UserResponse> showUser(@Body UserRequest userRequest);


    @GET("api/unknown")
    Call<Root> getData();

}
