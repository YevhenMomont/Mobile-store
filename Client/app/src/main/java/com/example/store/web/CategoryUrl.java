package com.example.store.web;

public interface CategoryUrl {

    String RESOURCE_NAME = "/categories";

    String CATALOG_ID = "/{uuid}";

    String GET = RESOURCE_NAME + CATALOG_ID;

    String CREATE = RESOURCE_NAME;

    String UPDATE = RESOURCE_NAME + CATALOG_ID;

    String DELETE = RESOURCE_NAME + CATALOG_ID;

}
