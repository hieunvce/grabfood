package com.example.recyclerview.Server;

import android.util.Log;

import com.example.recyclerview.DTO.UserHistory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.recyclerview.Fragment.HistoryPageFragment.userHistories;
import static com.example.recyclerview.Login.login_status;

public class UserNetworkProvider {

    UserHistory userHistory;
    void UserNetworkProvider(){

    }
    static final String BASE_URL = "http://13.70.4.174/";

    public void startcheckLogin(String username,String password) {
        Log.d("TQTTestUserDATA",username);
        Log.d("TQTTestUserDATA",password);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIServiceLogin apiServiceLogin = retrofit.create(APIServiceLogin.class);
        apiServiceLogin.Login("local","peter@gmail.com", "peter")
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        String res = response.body().toString();
                        if(res == "true")
                        {
                            login_status = true;
                        }else {
                            login_status = false;
                        }
                        Log.d("TQTTestRespond",res);
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("TQTTestRespond","Fail!");
                        login_status = false;
                    }
                });
    }

    public void startgetHistory(String id,String username) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIServiceHistory apiServiceHistory = retrofit.create(APIServiceHistory.class);
        apiServiceHistory.getHistory(id,username)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                        JSONObject jsonResponse = null;
                        ArrayList<String> temp = new ArrayList<String>();
                        try {
                            jsonResponse = new JSONObject(String.valueOf(response));
                            JSONArray responseJSONArray = jsonResponse.getJSONArray("");
                            for(int i=0;i<responseJSONArray.length();i++){
                                JSONObject historyObject = responseJSONArray.getJSONObject(i);
                                // Lấy thông tin history từ chuỗi JSON gán váo 1 object data
                                userHistory.setFullName(historyObject.getString("username"));
                                userHistory.setFood(historyObject.getString("foodname"));
                                userHistory.setPrice(historyObject.getString("price"));
                                userHistory.setDate(historyObject.getString("date"));
                                userHistories.add(userHistory); // add data vào array hiển thị listview
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //String res = response.body().toString();             // History trả về từ server
                        //Log.d("TQTTestRespond",userHistory);
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("TQTTestRespond","Fail!");
                        login_status = false;
                    }
                });
    }
}