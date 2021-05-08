package com.example.store.web;

public interface UserUrl {

    String RESOURCE_NAME = "/users";

    String USER_ID = "/{uuid}";

    String USER_LOGIN = "/current";

    String GET_BY_ID = RESOURCE_NAME + USER_ID;

    String GET_BY_EMAIL = RESOURCE_NAME + USER_LOGIN;

    String CREATE = RESOURCE_NAME;

    String UPDATE = RESOURCE_NAME + USER_ID;

    String DELETE = RESOURCE_NAME + USER_ID;

}
