package com.example.cefood.API.UserAPI;

public class AccessToken {
    private String accessToken;

    public AccessToken() {
    }

    public AccessToken(String accessToken) {
        super();
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public AccessToken withAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }
}
