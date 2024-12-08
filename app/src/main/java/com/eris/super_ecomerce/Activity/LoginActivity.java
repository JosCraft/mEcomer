package com.eris.super_ecomerce.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.eris.super_ecomerce.R;
import com.eris.super_ecomerce.model.LoginRequest;
import com.eris.super_ecomerce.utils.dataUser;
import com.google.android.material.textfield.TextInputEditText;

import com.eris.super_ecomerce.Controller.LoginController;

public class LoginActivity extends AppCompatActivity {

    EditText loginTextInputEmail;
    EditText loginTextInputPassword;
    TextView loginTextViewRegister;
    Button loginButtonLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        LoginController login = new LoginController(new dataUser(LoginActivity.this));
        loginTextViewRegister = findViewById(R.id.registerText);
        loginTextInputEmail = findViewById(R.id.emailEditText);
        loginTextInputPassword = findViewById(R.id.passwordEditText);
        loginButtonLogin = findViewById(R.id.loginButton);

        loginButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginRequest loginRequest = new LoginRequest(loginTextInputEmail.getText().toString(),loginTextInputPassword.getText().toString());
                login.login_cliente(loginRequest, LoginActivity.this);

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


}