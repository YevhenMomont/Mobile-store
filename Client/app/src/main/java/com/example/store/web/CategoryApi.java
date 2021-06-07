package com.example.store.web;


import com.example.store.catalog.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CategoryApi {

    @GET(value = CategoryUrl.RESOURCE_NAME)
    Call<List<Category>> getCategories();

    @POST(value = CategoryUrl.CREATE)
    Call<Category> postCategory(@Header("Authorization") String authHeader, @Body Category category);

}
