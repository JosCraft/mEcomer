package com.eris.super_ecomerce.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.eris.super_ecomerce.R;
import com.eris.super_ecomerce.model.LoginRequest;
import com.eris.super_ecomerce.utils.dataUser;
import com.eris.super_ecomerce.Controller.LoginController;

public class LoginActivity extends AppCompatActivity {

    EditText loginTextInputEmail;
    EditText loginTextInputPassword;
    TextView loginTextViewRegister;
    Button loginButtonLogin;
    Switch typeUser; // Switch para seleccionar entre cliente y delivery

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Inicializar el controlador
        LoginController login = new LoginController(new dataUser(LoginActivity.this));

        // Vincular vistas con sus IDs
        loginTextViewRegister = findViewById(R.id.registerText);
        loginTextInputEmail = findViewById(R.id.emailEditText);
        loginTextInputPassword = findViewById(R.id.passwordEditText);
        loginButtonLogin = findViewById(R.id.loginButton);
        typeUser = findViewById(R.id.deliverySwitch); // Asegúrate de que el ID coincida con el Switch en tu XML

        // Evento click para el botón de login
        loginButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores ingresados en los campos
                String email = loginTextInputEmail.getText().toString();
                String password = loginTextInputPassword.getText().toString();

                // Validar que los campos no estén vacíos
                if (email.isEmpty() || password.isEmpty()) {
                    loginTextViewRegister.setText("Por favor, completa todos los campos.");
                    return;
                }

                // Crear la solicitud de login
                LoginRequest loginRequest = new LoginRequest(email, password);

                // Verificar el estado del Switch y llamar a la función correspondiente
                if (typeUser.isChecked()) {
                    // Si el switch está activado: Login para delivery
                    login.login_delivery(loginRequest, LoginActivity.this);
                } else {
                    // Si el switch está desactivado: Login para cliente
                    login.login_cliente(loginRequest, LoginActivity.this);
                }
            }
        });

        // Manejo de insets para adaptarse a la pantalla
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
