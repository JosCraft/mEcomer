package com.eris.super_ecomerce.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.eris.super_ecomerce.model.Cliente;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class dataCar {

    private static final String PREFS_NAME = "CarPrefs";
    private static final String CAR_KEY = "car_items";
    private SharedPreferences sharedPreferences;
    private Gson gson;

    Context context;

    public dataCar(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    // Guardar un producto en la lista
    public void saveCar(int producto_id, int cantidad, double subtotal) {
        List<CarItem> carItems = getCarItems();
        carItems.add(new CarItem(producto_id, cantidad, subtotal));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        String carItemsJson = gson.toJson(carItems);
        editor.putString(CAR_KEY, carItemsJson);
        editor.apply();
        Toast.makeText(this.context,"Producto agregado al carrito",Toast.LENGTH_SHORT ).show();
    }

    // Obtener la lista de productos guardados
    public List<CarItem> getCarItems() {
        String carItemsJson = sharedPreferences.getString(CAR_KEY, null);

        if (carItemsJson != null) {
            Type type = new TypeToken<List<CarItem>>() {}.getType();
            return gson.fromJson(carItemsJson, type);
        } else {
            return new ArrayList<>();
        }
    }

    // Limpiar la lista de productos
    public void clearCarItems() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(CAR_KEY);
        editor.apply();
    }

    // Clase interna para representar un ítem en el carrito
    public static class CarItem {
        private int producto_id;
        private int cantidad;
        private double subtotal;

        public CarItem(int producto_id, int cantidad, double subtotal) {
            this.producto_id = producto_id;
            this.cantidad = cantidad;
            this.subtotal = subtotal;
        }

        public int getProducto_id() {
            return producto_id;
        }

        public int getCantidad() {
            return cantidad;
        }

        public double getSubtotal() {
            return subtotal;
        }
    }

}
