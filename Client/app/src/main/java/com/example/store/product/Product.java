package com.example.store.product;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    private UUID id;

    private String name;

    private String description;

    private double price;

    private UUID categoryId;

    private UUID createdBy;

}
