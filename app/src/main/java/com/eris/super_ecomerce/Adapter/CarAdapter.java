package com.eris.super_ecomerce.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eris.super_ecomerce.R;
import com.eris.super_ecomerce.utils.dataCar.CarItem;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private List<CarItem> carItems;

    public CarAdapter(List<CarItem> carItems) {
        this.carItems = carItems;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_car, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        CarItem item = carItems.get(position);
        holder.productId.setText("Producto ID: " + item.getProducto_id());
        holder.cantidad.setText("Cantidad: " + item.getCantidad());
        holder.subtotal.setText("Subtotal: $" + item.getSubtotal());
    }

    @Override
    public int getItemCount() {
        return carItems.size();
    }

    public static class CarViewHolder extends RecyclerView.ViewHolder {
        TextView productId, cantidad, subtotal;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            productId = itemView.findViewById(R.id.product_id);
            cantidad = itemView.findViewById(R.id.cantidad);
            subtotal = itemView.findViewById(R.id.subtotal);
        }
    }
}
