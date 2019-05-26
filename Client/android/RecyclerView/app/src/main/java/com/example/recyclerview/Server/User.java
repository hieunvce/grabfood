package com.example.recyclerview.Server;

import com.google.gson.annotations.SerializedName;

public final class User {
    @SerializedName("strategy")
    private String strategy;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String title) {
        this.username = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    @Override
    public String toString() {
        return "Post{" +
                "strategy='" + strategy + '\'' +
                ", username='" + username + '\'' +
                ", password=" + password +
                '}';
    }

}