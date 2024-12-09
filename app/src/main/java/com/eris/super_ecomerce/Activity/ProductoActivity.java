package com.eris.super_ecomerce.Activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.eris.super_ecomerce.MainActivity;
import com.eris.super_ecomerce.R;
import com.eris.super_ecomerce.model.ProductoModel;
import com.eris.super_ecomerce.utils.dataCar;
import com.squareup.picasso.Picasso;

public class ProductoActivity extends AppCompatActivity {

    private String TAG = "PRODUCTOAC";
    private ProductoModel producto;

    dataCar carDat;
    Button btnBuy, btnBack;
    ImageView prodcutoImage;
    TextView txtCatgoria, txtNombre, txtDescuento, txtPrecio, txtDescripcion, txtStock;

    EditText edtCantidad;
    private static final String IMAGE_BASE_URL = "http://192.168.1.100:3000/images/";
    public void setProducto(ProductoModel producto){
        this.producto = producto;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_producto);
        carDat = new dataCar(ProductoActivity.this);
        // Obtener el producto del Intent
        producto = getIntent().getParcelableExtra("producto");

        if (producto != null) {
            try {
                prodcutoImage = findViewById(R.id.productImage);
                txtCatgoria = findViewById(R.id.txtCategoria);
                txtNombre = findViewById(R.id.txtNombre);
                txtDescuento = findViewById(R.id.txtDescuento);
                txtPrecio = findViewById(R.id.txtPrecio);
                txtDescripcion = findViewById(R.id.txtDescripcion);
                txtStock = findViewById(R.id.txtStock);
                btnBuy = findViewById(R.id.btnRealizarPedido);
                btnBack = findViewById(R.id.backMainP);
                // Asignación de valores
                txtCatgoria.setText(String.valueOf(producto.getIdProducto())); // Convertir a String
                txtNombre.setText(producto.getNombre());
                txtDescripcion.setText(producto.getDescripcion());
                txtDescuento.setText(String.valueOf(producto.getDescuento())); // Convertir a String
                txtStock.setText(String.valueOf(producto.getStock())); // Convertir a String
                txtPrecio.setText("Bs." + producto.getPrecio());

                // Carga de imagen
                String imageName = producto.getImagen();
                if (imageName != null && !imageName.isEmpty()) {
                    String imageUrl = IMAGE_BASE_URL + imageName;
                    Picasso.get()
                            .load(imageUrl)
                            .placeholder(R.drawable.ic_charge_image)
                            .error(R.drawable.ic_empty_image)
                            .into(prodcutoImage);
                } else {
                    Log.w(TAG, "El nombre de la imagen es nulo o vacío para el producto.");
                }

                btnBuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edtCantidad = findViewById(R.id.edtCantidad);
                        double subotal = Integer.parseInt(edtCantidad.getText().toString()) * producto.getPrecio() ;
                        carDat.saveCar(producto.getIdProducto(),Integer.parseInt(edtCantidad.getText().toString()), subotal );
                    }
                });

                btnBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(ProductoActivity.this, MainActivity.class));
                    }
                });

            } catch (Resources.NotFoundException e) {
                Log.e(TAG, "Recurso no encontrado: " + e.getMessage(), e);
            } catch (NullPointerException e) {
                Log.e(TAG, "Un valor es nulo: " + e.getMessage(), e);
            } catch (Exception e) {
                Log.e(TAG, "Error inesperado: " + e.getMessage(), e);
            }
        } else {
            Log.e(TAG, "Producto es nulo");
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}