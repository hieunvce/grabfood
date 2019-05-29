package com.example.cefood.API.UserAPI;

public class LoginForm {
    private String strategy;
    private String email;
    private String password;

    public LoginForm() {
    }

    public LoginForm(String strategy, String email, String password) {
        this.strategy = strategy;
        this.email = email;
        this.password = password;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
