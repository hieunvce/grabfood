package com.example.cefood.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.cefood.API.ProductAPI.GetProductsByRestaurantIdInterface;
import com.example.cefood.API.ProductAPI.ProductsResponseFromAPI;
import com.example.cefood.AppHelper.WorkWithSharePreferences;
import com.example.cefood.CustomAdapter.ProductAdapter;
import com.example.cefood.Model.OrderDetail;
import com.example.cefood.Model.Product;
import com.example.cefood.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderFood extends AppCompatActivity {
    ArrayList<Product> productsArray = new ArrayList<Product>();
    WorkWithSharePreferences workWithSharePreferences = new WorkWithSharePreferences();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food);
        Intent intent = getIntent();
        String restaurantId = intent.getStringExtra("restaurantId");
        Log.d("restaurantId", restaurantId);

        GetRestaurantProducts(restaurantId);

        Button GoToCart = (Button) findViewById(R.id.btnGoToCart);

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("custom-message"));

        GoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", 0);
                //ArrayList<OrderDetail> orderedProducts = workWithSharePreferences.getOrderDetailArrayList(sharedPreferences);
                Intent intent = new Intent(OrderFood.this, CartActivity.class);
                //intent.putExtra("productsInCart", (Serializable) orderedProducts);
                startActivity(intent);
            }
        });
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            OrderDetail receivedOrderDetail = (OrderDetail) intent.getSerializableExtra("addToCart");

            Log.d("OrderFood", "onReceive: "+receivedOrderDetail.getProduct().getName() + " " + receivedOrderDetail.getQuantity());
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", 0);

            // Get data from SharedPreferences
            ArrayList<OrderDetail> orderedProducts = workWithSharePreferences.getOrderDetailArrayList(sharedPreferences);

            boolean existedInCart = false;
            int existProductIndex = 0;
            for (int i =0; i< orderedProducts.size();i++) {
                if (orderedProducts.get(i).getProduct().getId().equals(receivedOrderDetail.getProduct().getId())) {
                    existProductIndex = i;
                    existedInCart=true;
                    break;
                }
            }
            if (existedInCart == true) {
                int oldQuantity = orderedProducts.get(existProductIndex).getQuantity();
                Log.d("OrderFood", "oldQuantity"+oldQuantity);
                int newQuantity = oldQuantity + receivedOrderDetail.getQuantity();
                Log.d("OrderFood", "newQuantity"+newQuantity);
                orderedProducts.get(existProductIndex).setQuantity(newQuantity);
            } else {
                orderedProducts.add(receivedOrderDetail);
            }

            workWithSharePreferences.saveOrderDetailArrayList(orderedProducts, sharedPreferences);
        }
    };

    public void initView() {
        Log.d("OrderFood", "Jumped to InitView()");
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RView_OrderFood);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        // Set products data to ListView
        for (Product product : productsArray) {
            Log.d("productsArray: ", product.getName());
        }

        ProductAdapter productAdapter = new ProductAdapter(productsArray, getApplicationContext());
        recyclerView.setAdapter(productAdapter);
    }

    public void GetRestaurantProducts(String restaurantId) {
        String baseUrl = "https://cefood.herokuapp.com";

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();


        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        GetProductsByRestaurantIdInterface apiService = retrofit.create(GetProductsByRestaurantIdInterface.class);
        Call<ProductsResponseFromAPI> call = apiService.getProductsByRestaurantId(restaurantId);
        call.enqueue(new Callback<ProductsResponseFromAPI>() {

            @Override
            public void onResponse(Call<ProductsResponseFromAPI> call, Response<ProductsResponseFromAPI> response) {
                Log.d("Get products", "Get products Response Code: " + response.code());
                if (response.code() == 200) {
                    ProductsResponseFromAPI productsResponseFromAPI = response.body();
                    List<com.example.cefood.Model.Product> products = productsResponseFromAPI.getData();
                    ArrayList<com.example.cefood.Model.Product> arrayListProducts = (ArrayList) products;
                    productsArray = arrayListProducts;
                    for (Product product : arrayListProducts) {
                        Log.d("Get Product", product.getName());
                    }
                    initView();
                } else {
                    Log.d("Get products", "Get products Error.");
                }
            }

            @Override
            public void onFailure(Call<ProductsResponseFromAPI> call, Throwable t) {
                Log.e("Get products", "Get products error: " + t.getMessage());
            }
        });
    }
}
