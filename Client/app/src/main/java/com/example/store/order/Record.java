package com.example.store.order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Record {

    @SerializedName("id")
    @Expose
    UUID id;

    @SerializedName("userId")
    @Expose
    UUID userId;

    @SerializedName("productId")
    @Expose
    UUID productId;

    @SerializedName("ratingByUser")
    @Expose
    float ratingByUser;

}
