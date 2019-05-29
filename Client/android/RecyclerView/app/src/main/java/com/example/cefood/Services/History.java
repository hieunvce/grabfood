package com.example.cefood.Services;

import com.google.gson.annotations.SerializedName;

public final class History {
    @SerializedName("username")
    private String username;
    @SerializedName("foodname")
    private String foodname;
    @SerializedName("price")
    private String price;
    @SerializedName("date")
    private String date;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Post{" +
                "username='" + username + '\'' +
                ", foodname='" + foodname + '\'' +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}