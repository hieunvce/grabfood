package com.example.cefood.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cefood.API.RestaurantAPI.RestaurantAPIInterface;
import com.example.cefood.API.RestaurantAPI.RestaurantsResponseFromAPI;
import com.example.cefood.CustomAdapter.RestaurantAdapter;
import com.example.cefood.Model.Restaurant;
import com.example.cefood.R;
import com.example.cefood.Activity.location;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomePageFragment extends Fragment {

    ArrayList<Restaurant> arrayListRestaurants = new ArrayList<>();

    RecyclerView RecyclerViewHorizontal;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        View view = inflater.inflate(R.layout.home_page_fragment, parent, false);
        RecyclerViewHorizontal = view.findViewById(R.id.RView_RestaurantNearYou);
        GetRestaurantsData();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    }

    public  void initRecyclerViewHorizontal(){
        RecyclerViewHorizontal.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        RecyclerViewHorizontal.setLayoutManager(layoutManager);
        RestaurantAdapter restaurantAdapter = new RestaurantAdapter(arrayListRestaurants,getActivity().getApplicationContext());
        RecyclerViewHorizontal.setAdapter(restaurantAdapter);
    }


    public void GetRestaurantsData() {
        String baseUrl = "https://grabfood-api.herokuapp.com";

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();


        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        RestaurantAPIInterface apiService = retrofit.create(RestaurantAPIInterface.class);
        Call<RestaurantsResponseFromAPI> call = apiService.getRestaurants();
        call.enqueue(new Callback<RestaurantsResponseFromAPI>() {

            @Override
            public void onResponse(Call<RestaurantsResponseFromAPI> call, Response<RestaurantsResponseFromAPI> response) {
                Log.d("Get restaurants", "Get restaurant Response Code: " + response.code());
                if (response.code() == 200) {
                    RestaurantsResponseFromAPI restaurantsResponseFromAPI = response.body();
                    List<Restaurant> restaurants = restaurantsResponseFromAPI.getData();
                    arrayListRestaurants = (ArrayList) restaurants;
                    for (Restaurant restaurant:arrayListRestaurants) {
                        Log.d("Get Restaurant",restaurant.getName());
                    }
                    initRecyclerViewHorizontal();
                } else {
                    Log.d("Get restaurants", "Get restaurants Error.");
                }
            }

            @Override
            public void onFailure(Call<RestaurantsResponseFromAPI> call, Throwable t) {
                Log.e("Get restaurants", "Get restaurants error: " + t.getMessage());
            }
        });
    }
}
