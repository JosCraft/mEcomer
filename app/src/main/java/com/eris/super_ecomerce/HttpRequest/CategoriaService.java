package com.eris.super_ecomerce.HttpRequest;

import com.eris.super_ecomerce.model.Categoria;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
public interface CategoriaService
{
    @GET("categoria")
    Call<List<CategoriaService>> getCategoria();
    @GET("categoria/{id}")
    Call<CategoriaService> getCategoria(@Path("id") int id);

    @POST("categoria")
    Call<CategoriaService> createCategoria(@Body Categoria categoria);

    @PUT("categoria/{id}")
    Call<CategoriaService> updateCategoria(@Path("id") int id, @Body Categoria categoria);

    @DELETE("categoria/{id}")
    Call<Void> deleteCategoria(@Path("id") int id);
}
