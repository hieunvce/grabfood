package com.example.cefood.API;

import android.util.Log;

import com.example.cefood.API.UserAPI.AccessToken;
import com.example.cefood.API.UserAPI.LoginForm;
import com.example.cefood.API.UserAPI.UserAPIInterface;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {

    private String baseUrl = "https://grabfood-api.herokuapp.com";

    final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build();

    public void login(String email, String password) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(this.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        UserAPIInterface apiService = retrofit.create(UserAPIInterface.class);
        LoginForm loginForm = new LoginForm("local",email,password);
        Call<AccessToken> call = apiService.logIn(loginForm);
        call.enqueue(new Callback<AccessToken>() {

            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                if (response.code() == 200) {
                    AccessToken accessToken = response.body();
                    String jwtToken = accessToken.getAccessToken();
                    Log.d("LoginActivity", "LoginActivity Success. Access Token: "+jwtToken);
                } else {
                    Log.d("LoginActivity", "LoginActivity Error.");
                }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                Log.e("LoginActivity", "LoginFailure: " + t.getMessage());
            }
        });
    }
}
