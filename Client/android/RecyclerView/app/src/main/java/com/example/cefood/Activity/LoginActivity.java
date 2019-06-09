package com.example.cefood.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cefood.API.UserAPI.AccessToken;
import com.example.cefood.API.UserAPI.LoginForm;
import com.example.cefood.API.UserAPI.UserAPIInterface;
import com.example.cefood.API.UserAPI.UserResponseFromAPI;
import com.example.cefood.R;
import com.example.cefood.Services.UserNetworkProvider;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends AppCompatActivity {

    UserNetworkProvider userNetworkProvider = new UserNetworkProvider();

    // UI Views
    EditText ed_user_name, ed_password;
    Button btn_login, btn_signup;

    private String username, password;
    public static boolean login_status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", 0);
        String accessToken = sharedPreferences.getString("accessToken", null);
        if (!TextUtils.isEmpty(accessToken)) {
            Log.d("Login", "Login Success. Access Token: " + accessToken);
            if (!accessToken.isEmpty() && accessToken.length() > 0) {
                // Go to MainActivity
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }
        setContentView(R.layout.activity_login);
        BindIdFromLayout();
    }

    void BindIdFromLayout() {
        ed_user_name = findViewById(R.id.ed_username);
        ed_password = findViewById(R.id.ed_password);
        btn_login = findViewById(R.id.btn_login);
        btn_signup = findViewById(R.id.btn_signup);

        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getUserInput();
                boolean isValidUserInput = checkUserInput();
                if (isValidUserInput) {
                    logIn();
                }
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    void getUserInput() {
        this.username = ed_user_name.getText().toString().trim();
        this.password = ed_password.getText().toString().trim();
    }

    boolean checkUserInput() {
        if (this.username.length() > 0 && this.password.length() > 0) {
            if (!this.username.contains("@")) {
                Toast.makeText(LoginActivity.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (this.password.length() < 4) {
                Toast.makeText(LoginActivity.this, "Password has minimum 4 characters", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        } else {
            Toast.makeText(LoginActivity.this, "Please enter email & password", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    void logIn() {
        String baseUrl = "https://cefood.herokuapp.com";

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();


        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        final UserAPIInterface apiService = retrofit.create(UserAPIInterface.class);
        LoginForm loginForm = new LoginForm("local", this.username, this.password);
        Log.d("Login", "Login Form: " + loginForm.getStrategy() + loginForm.getEmail() + loginForm.getPassword());
        Call<AccessToken> call = apiService.logIn(loginForm);
        call.enqueue(new Callback<AccessToken>() {

            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                Log.d("Login", "Login Response Code: " + response.code());
                if (response.code() == 201) {
                    AccessToken accessToken = response.body();
                    String jwtToken = accessToken.getAccessToken();
                    Log.d("Login", "Login Success. Access Token: " + jwtToken);

                    // Save JWT to SharedPreferences
                    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("accessToken", jwtToken);
                    editor.commit();
                    // Go to MainActivity
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Log.d("Login", "Login Error.");
                    Toast.makeText(LoginActivity.this, "Login failed!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                Log.e("Login", "LoginFailure: " + t.getMessage());
                Toast.makeText(LoginActivity.this, "Login failed!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
