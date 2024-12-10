package com.eris.super_ecomerce.Controller;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.eris.super_ecomerce.Activity.LoginActivity;
import com.eris.super_ecomerce.HttpRequest.ClienteService;
import com.eris.super_ecomerce.MainActivity;
import com.eris.super_ecomerce.model.LoginRequest;
import com.eris.super_ecomerce.utils.Utils;
import com.eris.super_ecomerce.model.Cliente;
import com.eris.super_ecomerce.utils.dataUser;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginController {
    protected static String TAG = "LOGIN";
    private dataUser userData;

    public LoginController(dataUser userData) {
        this.userData = userData;
    }

    public void login_cliente(LoginRequest loginRequest, LoginActivity loginActivity) {
        try {
            ClienteService clienteService = Utils.getRetrofitInstance().create(ClienteService.class);
            Log.d(TAG, "DATOS " + loginRequest.toString());
            Log.d("LOGIN", "Cuerpo de la solicitud: " + loginRequest.getEmail() + "  " + loginRequest.getPassword());

            Call<Cliente> call = clienteService.login(loginRequest.getEmail(), loginRequest.getPassword());

            call.enqueue(new Callback<Cliente>() {
                @Override
                public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                    if (response.isSuccessful()) {
                        Log.d("LOGIN", "Respuesta exitosa: " + response.body());
                        Cliente cliente = response.body();
                        Log.d("LOGIN", "Datos Obtenidos: " + cliente.getNombre());

                        if (cliente != null) {
                            userData.saveUserData(cliente);
                            Intent loginIntent = new Intent(loginActivity, MainActivity.class);
                            loginActivity.startActivity(loginIntent);
                            Toast.makeText(loginActivity,"Ingreso Exitoso",Toast.LENGTH_LONG);
                        }
                    } else {
                        try {
                            String errorBody = response.errorBody() != null ? response.errorBody().string() : "Error sin cuerpo";
                            Log.d("LOGIN", "Error en la respuesta: " + errorBody);
                        } catch (IOException e) {
                            Log.e("LOGIN", "Error al leer el cuerpo de error", e);
                        }
                    }
                }

                @Override
                public void onFailure(Call<Cliente> call, Throwable t) {
                    Log.e("LOGIN", "Error en la llamada: ", t);
                }
            });
        } catch (Exception e) {
            Log.e("LOGIN", "Error al crear la solicitud de inicio de sesión: ", e);
        }
    }


    public void login_delivery  (LoginRequest loginRequest, LoginActivity loginActivity) {
        try {
            ClienteService clienteService = Utils.getRetrofitInstance().create(ClienteService.class);
            Log.d(TAG, "DATOS " + loginRequest.toString());
            Log.d("LOGIN", "Cuerpo de la solicitud: " + loginRequest.getEmail() + "  " + loginRequest.getPassword());

            Call<Cliente> call = clienteService.login(loginRequest.getEmail(), loginRequest.getPassword());

            call.enqueue(new Callback<Cliente>() {
                @Override
                public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                    if (response.isSuccessful()) {
                        Log.d("LOGIN", "Respuesta exitosa: " + response.body());
                        Cliente cliente = response.body();
                        Log.d("LOGIN", "Datos Obtenidos: " + cliente.getNombre());

                        if (cliente != null) {
                            userData.saveUserData(cliente);
                            Intent loginIntent = new Intent(loginActivity, MainActivity.class);
                            loginActivity.startActivity(loginIntent);
                            Toast.makeText(loginActivity,"Ingreso Exitoso",Toast.LENGTH_LONG);
                        }
                    } else {
                        try {
                            String errorBody = response.errorBody() != null ? response.errorBody().string() : "Error sin cuerpo";
                            Log.d("LOGIN", "Error en la respuesta: " + errorBody);
                        } catch (IOException e) {
                            Log.e("LOGIN", "Error al leer el cuerpo de error", e);
                        }
                    }
                }

                @Override
                public void onFailure(Call<Cliente> call, Throwable t) {
                    Log.e("LOGIN", "Error en la llamada: ", t);
                }
            });
        } catch (Exception e) {
            Log.e("LOGIN", "Error al crear la solicitud de inicio de sesión: ", e);
        }
    }


}
