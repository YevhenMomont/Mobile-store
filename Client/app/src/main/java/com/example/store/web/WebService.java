package com.example.store.web;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebService {

    private static WebService service;
    private static final String BASE_URL = "http://10.0.2.2:8080";

    private Retrofit retrofit;

    private WebService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static WebService getInstance() {
        if (service == null) {
            service = new WebService();
        }
        return service;
    }

    public UserApi getUserApi() {
        return retrofit.create(UserApi.class);
    }

    public ProductApi getProductApi() {return retrofit.create(ProductApi.class);}

    public CatalogApi getCatalogApi() {return retrofit.create(CatalogApi.class);}

}
