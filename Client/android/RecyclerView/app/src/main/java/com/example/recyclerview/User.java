package com.example.recyclerview;

import com.google.gson.annotations.SerializedName;

public final class User {
    @SerializedName("strategy")
    private String strategy;
    @SerializedName("email")
    private String username;
    @SerializedName("password")
    private String password;
}