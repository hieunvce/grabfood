package com.example.cefood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cefood.API.UserAPI.AccessToken;
import com.example.cefood.API.UserAPI.EditUserForm;
import com.example.cefood.API.UserAPI.LoginForm;
import com.example.cefood.API.UserAPI.UserAPIInterface;
import com.example.cefood.Model.User;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {

    EditText ed_email, ed_password, ed_retype_password;
    Button btn_signup;

    private String email, password, retypePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        BindIdFromLayout();
    }

    private void BindIdFromLayout() {
        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        ed_retype_password = findViewById(R.id.ed_retype_password);
        btn_signup = findViewById(R.id.btn_signup);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValidUserInput = validateUserInput();
                if (isValidUserInput) {
                    signUp();
                }
            }
        });

    }


    private boolean validateUserInput() {
        this.email = ed_email.getText().toString();
        this.password = ed_password.getText().toString();
        this.retypePassword = ed_retype_password.getText().toString();

        boolean isValidUserInput = false;

        if (email.isEmpty() || password.isEmpty() || retypePassword.isEmpty()) {
            isValidUserInput = false;
            Toast.makeText(SignUpActivity.this, "Please enter all 3 field!", Toast.LENGTH_SHORT).show();
        } else if (!email.contains("@") || password.length() < 4 || !retypePassword.equals(password)) {
            Toast.makeText(SignUpActivity.this, "Please enter all 3 field!", Toast.LENGTH_SHORT).show();
            if (!email.contains("@")) {
                Toast.makeText(SignUpActivity.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
            }
            if (password.length() < 4) {
                Toast.makeText(SignUpActivity.this, "Password length must be > 4", Toast.LENGTH_SHORT).show();
            }
            if (!retypePassword.equals(password)) {
                Toast.makeText(SignUpActivity.this, "Retype password not match", Toast.LENGTH_SHORT).show();
            }
            isValidUserInput = false;
        } else {
            isValidUserInput = true;
        }
        return isValidUserInput;
    }

    private void signUp() {
        String baseUrl = "https://grabfood-api.herokuapp.com";

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        UserAPIInterface apiService = retrofit.create(UserAPIInterface.class);
        EditUserForm editUserForm = new EditUserForm(this.email, this.password, "", "", "");
        Log.d("SignUp", "SignUp Form: " + editUserForm.getEmail() + editUserForm.getPassword());
        Call<User> call = apiService.signUp(editUserForm);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("Signup", "Sign Response Code: " + response.code());
                if (response.code() == 201) {
                    User user = response.body();
                    Log.d("Signup", "Signup Success.");
                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Log.d("Signup", "Signup Error.");
                    Toast.makeText(SignUpActivity.this, "Signup failed!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Signup", "Signup Failed: " + t.getMessage());
                Toast.makeText(SignUpActivity.this, "Signup failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
