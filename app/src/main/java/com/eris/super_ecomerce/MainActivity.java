package com.eris.super_ecomerce;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eris.super_ecomerce.Activity.LoginActivity;

import com.eris.super_ecomerce.Activity.PedidosActivity;
import com.eris.super_ecomerce.Activity.PerfilActivity;
import com.eris.super_ecomerce.HttpRequest.ProductoService;
import com.eris.super_ecomerce.model.ProductoModel;
import com.eris.super_ecomerce.utils.Utils;
import com.eris.super_ecomerce.utils.dataUser;

import com.eris.super_ecomerce.Adapter.ProductosAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private dataUser userData;
    private ProductosAdapter productoAdapter;

    private List<ProductoModel> productoList;

    Button btnHome, btnSearch, btnProfile, btnSalir;
    RecyclerView recyclerView;
    private static final String TAG = "Producto";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userData = new dataUser(this);
        if (!userData.hasUserData()) {
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginIntent);
        }

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        // Inicialización de botones
        btnHome = findViewById(R.id.btn_home);
        btnSearch = findViewById(R.id.btn_search);
        btnProfile = findViewById(R.id.btn_profile);
        btnSalir = findViewById(R.id.btn_close);

        // Configurar el comportamiento de los botones
        btnHome.setOnClickListener(v -> {
            if (!(this instanceof MainActivity)) {
                startActivity(new Intent(this, MainActivity.class));
            }
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

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cargarProductos();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void cargarProductos() {
        ProductoService productoService = Utils.getRetrofitInstance().create(ProductoService.class);
        Call<List<ProductoModel>> call = productoService.getProductos();
        Log.d(TAG, "data: " + call);
        call.enqueue(new Callback<List<ProductoModel>>() {
            @Override
            public void onResponse(Call<List<ProductoModel>> call, Response<List<ProductoModel>> response) {
                if (response.isSuccessful()) {
                    productoList = response.body();
                    Log.d(TAG, "Productos cargados: " + productoList);
                    productoAdapter = new ProductosAdapter(productoList);
                    recyclerView.setAdapter(productoAdapter);
                } else {
                    // Agrega más detalles del error
                    Log.e(TAG, "Error en la respuesta: Código de estado: " + response.code() + ", Mensaje: " + response.message());
                    Toast.makeText(MainActivity.this, "Error al cargar los productos", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<List<ProductoModel>> call, Throwable t) {
                Log.e(TAG, "Error en la conexión: ", t);  // Loguea la excepción para más detalles
                Toast.makeText(MainActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }

        });
    }


}
