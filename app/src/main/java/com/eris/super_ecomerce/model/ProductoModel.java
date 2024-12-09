package com.eris.super_ecomerce.model;

import com.google.gson.annotations.SerializedName;
import android.os.Parcel;
import android.os.Parcelable;
public class ProductoModel implements Parcelable{

    @SerializedName("idProducto")
    private int idProducto;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("descripcion")
    private String descripcion;

    @SerializedName("precio")
    private double precio;

    @SerializedName("descuento")
    private int descuento;

    // Aquí almacenamos la imagen como un arreglo de bytes
    @SerializedName("imagen")
    private String imagen;

    @SerializedName("stock")
    private int stock;


    protected ProductoModel(Parcel in) {
        idProducto = Integer.parseInt(in.readString());
        nombre = in.readString();
        descripcion = in.readString();
        descuento = Integer.parseInt(in.readString());
        stock = Integer.parseInt(in.readString());
        precio = Double.parseDouble(in.readString());
        imagen = in.readString();
    }

    public static final Creator<ProductoModel> CREATOR = new Creator<ProductoModel>() {
        @Override
        public ProductoModel createFromParcel(Parcel in) {
            return new ProductoModel(in);
        }

        @Override
        public ProductoModel[] newArray(int size) {
            return new ProductoModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(String.valueOf(idProducto));
        dest.writeString(nombre);
        dest.writeString(descripcion);
        dest.writeString(String.valueOf(descuento));
        dest.writeString(String.valueOf(stock));
        dest.writeString(String.valueOf(precio));
        dest.writeString(imagen);
    }

    // Getters y setters

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double  getPrecio() {
        return precio;
    }

    public void setPrecio(double  precio) {
        this.precio = precio;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


}
