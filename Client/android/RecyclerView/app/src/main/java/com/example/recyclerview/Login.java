package com.example.recyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;



public class Login extends AppCompatActivity {

    UserNetworkProvider userNetworkProvider = new UserNetworkProvider();
    EditText ed_user_name,ed_password;
    CheckBox cb_rememberme;
    TextView tv_forget_pass;
    Button   btn_login, btn_signup;
    String username,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        BindIdFromLayout();
    }

    void BindIdFromLayout(){
        ed_user_name = findViewById(R.id.ed_username);
        ed_password = findViewById(R.id.ed_password);
        cb_rememberme = findViewById(R.id.cb_rememberme);
        tv_forget_pass = findViewById(R.id.tv_forget_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_signup = findViewById(R.id.btn_signup);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetUserInfo();
                userNetworkProvider.start(username,password);
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    void GetUserInfo(){
        username = ed_user_name.getText().toString();
        password = ed_password.getText().toString();
    }
}