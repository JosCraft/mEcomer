package com.eris.super_ecomerce.Controller;

import com.eris.super_ecomerce.HttpRequest.ProductoService;
import com.eris.super_ecomerce.model.ProductoModel;
import com.eris.super_ecomerce.utils.Utils;

import java.util.List;

import retrofit2.Call;

public class ProductoController {

    private ProductoService productoService;
    ProductoController(){
        ProductoService productoService = Utils.getRetrofitInstance().create(ProductoService.class);
    }

    public void cargarProductos(){
        Call<List<ProductoModel>> call = productoService.getProductos();
    }


}
