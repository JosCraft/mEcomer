package com.eris.super_ecomerce.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.eris.super_ecomerce.model.Cliente;
public class dataUser {
    private static final String PREFS_NAME = "UserPrefs";
    private SharedPreferences sharedPreferences;

    public dataUser(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveUserData(Cliente cliente) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("idCliente", cliente.getIdCliente());
        editor.putString("nombre", cliente.getNombre());
        editor.putString("direccion", cliente.getDireccion());
        editor.putInt("telefono", cliente.getTelefono());
        editor.putString("email", cliente.getEmail());
        editor.putString("password", cliente.getPassword());
        editor.apply();
    }

    public Cliente getUserData() {
        int idCliente = sharedPreferences.getInt("idCliente", -1);
        String nombre = sharedPreferences.getString("nombre", null);
        String direccion = sharedPreferences.getString("direccion", null);
        int telefono = sharedPreferences.getInt("telefono", -1);
        String email = sharedPreferences.getString("email", null);
        String password = sharedPreferences.getString("password", null);

        if (idCliente != -1 && nombre != null && email != null) {
            return new Cliente(idCliente, nombre, direccion, telefono, email, password);
        } else {
            return null;
        }
    }

    public boolean hasUserData() {
        return sharedPreferences.contains("idCliente");
    }


    public void clearUserData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}

