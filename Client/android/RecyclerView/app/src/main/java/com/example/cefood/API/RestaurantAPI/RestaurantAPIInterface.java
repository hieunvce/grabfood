package com.example.cefood.API.RestaurantAPI;

import com.example.cefood.Model.Restaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestaurantAPIInterface {
    @GET("restaurants")
    Call<List<Restaurant>> getRestaurants();
}
