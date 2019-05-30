package com.example.cefood.API.ProductAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetProductsByRestaurantIdInterface {

    @GET("products")
    Call<ProductsResponseFromAPI> getProductsByRestaurantId(@Query("restaurantId") String restaurantId);

}
