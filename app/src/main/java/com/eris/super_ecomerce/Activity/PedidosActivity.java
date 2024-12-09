package com.eris.super_ecomerce.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eris.super_ecomerce.R;
import com.eris.super_ecomerce.adapter.CarAdapter;
import com.eris.super_ecomerce.utils.dataCar;

import java.util.List;

public class PedidosActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        RecyclerView recyclerView = findViewById(R.id.recicleyViewCar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataCar carManager = new dataCar(this);
        List<dataCar.CarItem> carItems = carManager.getCarItems();

        CarAdapter adapter = new CarAdapter(carItems);
        recyclerView.setAdapter(adapter);
    }
}
