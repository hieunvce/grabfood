package com.example.cefood.API.RestaurantAPI;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface RestaurantAPIInterface {
    @GET("restaurants")
    Call<RestaurantsResponseFromAPI> getRestaurants();
}
