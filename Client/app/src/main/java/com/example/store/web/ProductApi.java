package com.example.store.web;

import com.example.store.product.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProductApi {

    @GET(value = ProductUrl.GET)
    Call<List<Product>> getProducts();

    @POST(value = ProductUrl.CREATE)
    Call<Product> postProduct(@Body Product product);

}
