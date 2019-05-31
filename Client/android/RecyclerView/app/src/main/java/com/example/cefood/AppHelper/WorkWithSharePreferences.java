package com.example.cefood.AppHelper;

import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.cefood.Model.OrderDetail;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class WorkWithSharePreferences {
    public ArrayList<OrderDetail> getOrderDetailArrayList(SharedPreferences sharedPreferences){
        ArrayList<OrderDetail> orderDetails =  new ArrayList<OrderDetail>();
        String orderedProductsJson = sharedPreferences.getString("orderDetailArrayList", null);
        if (!TextUtils.isEmpty(orderedProductsJson)) {
            Gson gson = new Gson();
            OrderDetail orderDetailArray[] = gson.fromJson(orderedProductsJson, OrderDetail[].class);
            orderDetails = new ArrayList<OrderDetail>(Arrays.asList(orderDetailArray));
        }
        return orderDetails;
    }

    public void saveOrderDetailArrayList(ArrayList<OrderDetail> orderDetails,SharedPreferences sharedPreferences){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Convert Object to Json
        Gson gson = new Gson();
        String orderDetailsJson = gson.toJson(orderDetails);
        // Save to SharedPreferences
        editor.putString("orderDetailArrayList", orderDetailsJson);
        editor.commit();
    }

    public int getTotalPayment(SharedPreferences sharedPreferences){
        int totalPayment  = sharedPreferences.getInt("totalPayment", 0);
        return totalPayment;
    }

    public void saveTotalPayment(int totalPayment, SharedPreferences sharedPreferences){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("totalPayment",totalPayment);
        editor.commit();
    }

}
