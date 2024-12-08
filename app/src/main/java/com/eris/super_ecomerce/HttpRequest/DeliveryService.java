package com.eris.super_ecomerce.HttpRequest;

import com.eris.super_ecomerce.model.Delivery;
import com.eris.super_ecomerce.model.LoginRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
public interface DeliveryService {
    @GET("deliveries")
    Call<List<Delivery>> getDelivery();

    @GET("delivery/{id}")
    Call<Delivery> getDelivery(@Path("id") int id);

    @PUT("delivery/{id}")
    Call<Delivery> updateDelivery(@Path("id") int id);

    @POST("delivery/login")
    Call<Delivery> login(@Body LoginRequest loginRequest);



}
