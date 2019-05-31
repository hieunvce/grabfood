package com.example.cefood.API.OrderAPI;

import com.example.cefood.API.ProductAPI.ProductsResponseFromAPI;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OrderAPIInterface {
    @GET("orders")
    Call<OrderResponseFromAPI> getOrdersByUserId(@Header("Authorization") String authToken);

    @POST("orders")
    Call<ResponseBody> addOrder(@Header("Authorization") String authToken, @Body OrderForm orderForm);
}
