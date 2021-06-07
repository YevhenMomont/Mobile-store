package com.example.store.web;

import com.example.store.product.Product;
import com.example.store.user.User;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductApi {

    @GET(value = ProductUrl.RESOURCE_NAME)
    Call<List<Product>> getProducts();

    @GET(value = ProductUrl.GET_BY_CATEGORY)
    Call<List<Product>> getProductsByCategoryId(@Path("uuid") UUID uuid);

    @GET(value = ProductUrl.GET_BY_COSINE_SIMILARITY)
    Call<List<Product>> getProductsByCosineSimilarity(@Header("Authorization") String authHeader, @Path("uuid") UUID uuid);

    @GET(value = ProductUrl.GET_BY_MSE)
    Call<List<Product>> getProductsByMSE(@Header("Authorization") String authHeader, @Path("uuid") UUID uuid);

    @GET(value = ProductUrl.GET_BY_PEARSON_CORRELATION)
    Call<List<Product>> getProductsByPearsonCorrelation(@Header("Authorization") String authHeader, @Path("uuid") UUID uuid);

    @POST(value = ProductUrl.RESOURCE_NAME)
    Call<Product> postProduct(@Header("Authorization") String authHeader, @Body Product product);

    @PUT(ProductUrl.UPDATE)
    Call<Product> updateProduct(@Header("Authorization") String authHeader, @Body Product product);

}
