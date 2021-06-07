package com.example.store.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    @SerializedName("uuid")
    @Expose
    private UUID uuid;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("price")
    @Expose
    private double price;

    @SerializedName("categoryId")
    @Expose
    private UUID categoryId;

    @SerializedName("rating")
    @Expose
    private float rating;

    @SerializedName("createdBy")
    @Expose
    private UUID createdBy;

    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;


}
