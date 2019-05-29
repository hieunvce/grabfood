package com.example.cefood.Services;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIServiceHistory {
    @POST("")
    @FormUrlEncoded
    Call<User> getHistory(@Field("id") String id,
                     @Field("username") String username);

}
