package com.example.store.web;


import com.example.store.catalog.Catalog;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CatalogApi {

    @GET(value = CatalogUrl.GET)
    Call<List<Catalog>> getCatalogs();

    @POST(value = CatalogUrl.CREATE)
    Call<Catalog> postCatalog(@Body Catalog catalog);

}
