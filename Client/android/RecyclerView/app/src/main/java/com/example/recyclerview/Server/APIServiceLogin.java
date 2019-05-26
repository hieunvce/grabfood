package com.example.recyclerview.Server;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIServiceLogin {

    @POST("/authentication")
    @FormUrlEncoded
    Call<User> Login(@Field("strategy") String strategy,
                     @Field("email") String username,
                     @Field("password") String password);
}