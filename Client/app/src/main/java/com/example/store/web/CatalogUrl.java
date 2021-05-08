package com.example.store.web;

public interface CatalogUrl {

    String RESOURCE_NAME = "/CATALOGs";

    String CATALOG_ID = "/{uuid}";

    String GET = RESOURCE_NAME + CATALOG_ID;

    String CREATE = RESOURCE_NAME;

    String UPDATE = RESOURCE_NAME + CATALOG_ID;

    String DELETE = RESOURCE_NAME + CATALOG_ID;

}
