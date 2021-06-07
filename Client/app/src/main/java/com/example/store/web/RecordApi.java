package com.example.store.web;

import com.example.store.order.Record;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RecordApi {

    @GET(value = RecordUrl.RESOURCE_NAME)
    Call<List<Record>> getRecords(@Header("Authorization") String authHeader);

    @POST(value = RecordUrl.RESOURCE_NAME)
    Call<Record> postRecord(@Header("Authorization") String authHeader, @Body Record Record);

}
