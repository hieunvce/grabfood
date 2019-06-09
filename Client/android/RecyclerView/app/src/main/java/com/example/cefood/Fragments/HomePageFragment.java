package com.example.cefood.Fragments;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.MenuItem;
import android.widget.Toast;

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
    ArrayList<Restaurant> arrayListRestaurantsSearch = new ArrayList<>();
    RecyclerView RecyclerViewHorizontal;
    RestaurantAdapter restaurantAdapter;
    TextView user_address;
    Button btnSearch, btncancelSearch;
    EditText search;
    private SearchView searchView;
    SwipeRefreshLayout swipeLayout;

    private static final int REQUEST_CODE = 0x01;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        View view = inflater.inflate(R.layout.home_page_fragment, parent, false);
        RecyclerViewHorizontal = view.findViewById(R.id.RView_RestaurantNearYou);
        user_address = view.findViewById(R.id.tv_user_address);
        setHasOptionsMenu(true);
        GetRestaurantsData();

        swipeLayout = view.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                GetRestaurantsData();
                Toast.makeText(getContext(), "Refresh!", Toast.LENGTH_LONG).show();
                // To keep animation for 4 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        // Stop animation (This will be after 3 seconds)
                        swipeLayout.setRefreshing(false);
                    }
                }, 1000); // Delay in millis
            }
        });

        // Scheme colors for animation
        swipeLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );


           return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                restaurantAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                restaurantAdapter.getFilter().filter(query);
                return false;
            }
        });
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                final String result = data.getStringExtra("address");

                if (!result.equals("")) {
                    user_address.setText(result);
                }
            } else {
            }
        }
    }

    public  void initRecyclerViewHorizontal(ArrayList<Restaurant> arrayList){
        RecyclerViewHorizontal.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        RecyclerViewHorizontal.setLayoutManager(layoutManager);
        restaurantAdapter = new RestaurantAdapter(arrayList,getActivity().getApplicationContext());
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
                    initRecyclerViewHorizontal(arrayListRestaurants);
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
