package com.eris.super_ecomerce.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.eris.super_ecomerce.MainActivity;
import com.eris.super_ecomerce.R;
import com.eris.super_ecomerce.model.Cliente;
import com.eris.super_ecomerce.utils.dataUser;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class PerfilActivity extends AppCompatActivity {

    TextView name, addres, phone, email;
    dataUser userData;
    Button btnHome, btnSearch, btnProfile, btnSalir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil);

        // Inicialización de elementos de UI
        name = findViewById(R.id.profile_name_text);
        phone = findViewById(R.id.profile_phone_text);
        addres = findViewById(R.id.profile_address_text);
        email = findViewById(R.id.profile_email_text);


        // Configurar los datos del usuario
        userData = new dataUser(PerfilActivity.this);
        Cliente cliente = userData.getUserData();

        if (cliente != null) {
            name.setText(cliente.getNombre());
            phone.setText(String.valueOf(cliente.getTelefono())); // Asegúrate de convertir a String si es necesario
            addres.setText(cliente.getDireccion());
            email.setText(cliente.getEmail());
        } else {
            // Mostrar un mensaje de error o redirigir al usuario a la página de inicio de sesión, por ejemplo.
            Toast.makeText(this, "Datos del usuario no encontrados", Toast.LENGTH_SHORT).show();
        }

        // Inicialización de botones
        btnHome = findViewById(R.id.btn_home);
        btnSearch = findViewById(R.id.btn_search);
        btnProfile = findViewById(R.id.btn_profile);
        btnSalir = findViewById(R.id.btn_close);

        // Configurar el comportamiento de los botones
        btnHome.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
        });

        btnSearch.setOnClickListener(v -> {
            startActivity(new Intent(this, PedidosActivity.class));
        });

        btnProfile.setOnClickListener(v -> {
            startActivity(new Intent(this, PerfilActivity.class));
        });

        btnSalir.setOnClickListener(v ->{
            userData.clearUserData();
            startActivity(new Intent(this, LoginActivity.class));
        });


        // Ajustar los márgenes de la ventana en la actividad para compatibilidad con el sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
