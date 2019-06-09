package com.example.cefood.API.ProductAPI;

import com.example.cefood.Model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetProductsByRestaurantIdInterface {

    @GET("products")
    Call<List<Product>> getProductsByRestaurantId(@Query("restaurantId") String restaurantId);

}
