package com.eris.super_ecomerce.HttpRequest;

import com.eris.super_ecomerce.model.Cliente;
import com.eris.super_ecomerce.model.Delivery;
import com.eris.super_ecomerce.model.LoginRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.FormUrlEncoded;
public interface ClienteService {
    @GET("cliente")
    Call<List<Cliente>> getCliente();

    @GET("cliente/{id}")
    Call<Cliente> getCliente_by_id(@Path("id") int id);

    @POST("cliente")
    Call<Cliente> createCliente(@Body Cliente cliente);

    @FormUrlEncoded
    @POST("login")
    Call<Cliente> login(@Field("email") String email,
                        @Field("password") String password);
    @PUT("cliente/{id}")
    Call<Cliente> updateCliente(@Path("id") int id, @Body Cliente cliente);

    @DELETE("cliente/{id}")
    Call<Void> deleteCliente(@Path("id") int id);

}
