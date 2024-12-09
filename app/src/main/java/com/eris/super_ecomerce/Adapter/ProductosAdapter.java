package com.eris.super_ecomerce.Adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eris.super_ecomerce.Activity.ProductoActivity;
import com.eris.super_ecomerce.MainActivity;
import com.eris.super_ecomerce.R;
import com.eris.super_ecomerce.model.ProductoModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ProductoViewHolder> {

    private List<ProductoModel> productos;
    private static final String IMAGE_BASE_URL = "http://192.168.1.100:3000/images/";

    // Etiqueta para los Logs
    private static final String TAG = "ProductoAdapter";

    public ProductosAdapter(List<ProductoModel> productos) {
        this.productos = productos;
    }

    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        return new ProductoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductoViewHolder holder, int position) {
        ProductoModel producto = productos.get(position);
        holder.productName.setText(producto.getNombre());
        holder.productPrice.setText("Bs. " + producto.getPrecio());
        holder.productStock.setText("Cnt: " + producto.getStock());
        holder.productCategory.setText("Desc: " + producto.getDescuento() + "%");

        holder.btnProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProductoActivity.class);
                intent.putExtra("producto", producto); // Enviar el objeto
                v.getContext().startActivity(intent);
            }
        });

        // Agregar Log para verificar si la imagen está siendo correctamente procesada
        String imageName = producto.getImagen();
        Log.d(TAG, "Procesando imagen para el producto: " + producto.getNombre() + " con nombre de imagen: " + imageName);

        if (imageName != null && !imageName.isEmpty()) {
            String imageUrl = IMAGE_BASE_URL + imageName;
            Log.d(TAG, "URL de la imagen: " + imageUrl);

            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_charge_image)
                    .error(R.drawable.ic_empty_image) // Imagen de error si la carga falla
                    .into(holder.productImage, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            Log.d(TAG, "La imagen se cargó correctamente.");
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.e(TAG, "Error al cargar la imagen: " + e.getMessage(), e);
                        }
                    });
        } else {
            Log.w(TAG, "El nombre de la imagen es nulo o vacío para el producto: " + producto.getNombre());
        }
    }
    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        public TextView productName, productPrice, productStock, productCategory;
        public Button btnProducto;
        public ImageView productImage;

        public ProductoViewHolder(View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productImage = itemView.findViewById(R.id.productImage);
            productStock = itemView.findViewById(R.id.productStock);
            productCategory = itemView.findViewById(R.id.productCategory);
            btnProducto = itemView.findViewById(R.id.productBtnVer);
        }
    }
}
