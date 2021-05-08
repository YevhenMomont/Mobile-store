package com.example.store.web;

public interface ProductUrl {

    String RESOURCE_NAME = "/products";

    String PRODUCT_ID = "/{uuid}";

    String GET = RESOURCE_NAME + PRODUCT_ID;

    String CREATE = RESOURCE_NAME;

    String UPDATE = RESOURCE_NAME + PRODUCT_ID;

    String DELETE = RESOURCE_NAME + PRODUCT_ID;

}
