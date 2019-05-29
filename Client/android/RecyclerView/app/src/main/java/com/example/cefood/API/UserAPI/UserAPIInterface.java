package com.example.cefood.API.UserAPI;

import com.example.cefood.Model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserAPIInterface {
    @POST("authentication")
    Call<AccessToken> logIn(@Body LoginForm loginForm);

    @POST("users")
    Call<User> signUp(@Body EditUserForm editUserForm);

    @GET("users")
    Call<UserAPI> getUserInfo(@Header("Authorization") String authToken, @Body UserAPI userAPI);

    @PUT("users/{user_id}")
    Call<User> updateUserInfo(@Path("user_id") String user_id, @Header("Authorization") String authToken, @Body User user);
}
