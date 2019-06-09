package com.example.cefood.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.cefood.API.OrderAPI.OrderAPIInterface;
import com.example.cefood.API.OrderAPI.OrderDataFromAPI;
import com.example.cefood.API.OrderAPI.OrderResponseFromAPI;
import com.example.cefood.CustomAdapter.UserHistoryCustomAdapter;
import com.example.cefood.R;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoryPageFragment extends Fragment {
    private ListView lv_user_history;
    View view;
    public List<OrderDataFromAPI> userHistories;
    private UserHistoryCustomAdapter userHistoryCustomAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.history_page_fragment, parent, false);
        getOrderHistory();
        bind_id();
        return view;
    }

    private void bind_id(){
        lv_user_history = view.findViewById(R.id.lv_user_history);
    }

    public void getOrderHistory(){
        String baseUrl = "https://cefood.herokuapp.com";

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();


        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        OrderAPIInterface apiService = retrofit.create(OrderAPIInterface.class);

        final SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("MyPref", 0);
        String accessToken = sharedPreferences.getString("accessToken", null);
        if (accessToken != null) {

            Call<List<OrderDataFromAPI>> call = apiService.getOrdersByUserId(accessToken);
            call.enqueue(new Callback<List<OrderDataFromAPI>>() {
                @Override
                public void onResponse(Call<List<OrderDataFromAPI>> call, Response<List<OrderDataFromAPI>> response) {
                    Log.d("Get order", "Get order Response Code: " + response.code());
                    if (response.code() == 200) {
                        Log.d("Get order", "Get order success: " + response.code());
                        //OrderResponseFromAPI orderResponseFromAPIS = response.body();
                        //userHistories = orderResponseFromAPIS.getData();
                        userHistories = response.body();
                        userHistoryCustomAdapter = new UserHistoryCustomAdapter(getActivity(), 1, userHistories);
                        lv_user_history.setAdapter(userHistoryCustomAdapter);
                    } else {
                        Log.d("Get order", "Get order Error.");
                    }
                }

                @Override
                public void onFailure(Call<List<OrderDataFromAPI>> call, Throwable t) {
                    Log.e("Get order", "Get order failed: " + t.getMessage());
                }
            });
        }
    }
}
