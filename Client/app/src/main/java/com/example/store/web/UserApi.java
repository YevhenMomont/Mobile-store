package com.example.store.web;

import com.example.store.user.User;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi {

    @GET(UserUrl.RESOURCE_NAME)
    Call<List<User>> getUsers();

    @GET(UserUrl.GET_BY_ID)
    Call<User> getUser(@Path("id") UUID id);

    @GET(UserUrl.GET_BY_EMAIL)
    Call<User> getUserByEmail(@Header("Authorization") String authHeader);

    @POST(UserUrl.CREATE)
    Call<User> postUser(@Body User user);

}
