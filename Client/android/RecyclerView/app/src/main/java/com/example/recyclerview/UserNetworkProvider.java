package com.example.recyclerview;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserNetworkProvider {

    void UserNetworkProvider(){

    }
    static final String BASE_URL = "http://13.70.4.174";

    public void start(String username,String password) {
        Log.d("TQTTestUserDATA",username);
        Log.d("TQTTestUserDATA",password);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIService apiService = retrofit.create(APIService.class);
        apiService.Login("local","peter@gmail.com", "peter")
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        String res = response.body().toString();
                        Log.d("TQTTestRespond",res);
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("TQTTestRespond","Fail!");
                    }
                });
    }
}