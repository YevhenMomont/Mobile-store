package com.example.store.catalog;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Catalog {

    private UUID id;

    private String name;

    private String description;

}
