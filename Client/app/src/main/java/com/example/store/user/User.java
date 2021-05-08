package com.example.store.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @SerializedName("id")
    @Expose
    private UUID id;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("firstName")
    @Expose
    private String firstName;

    @SerializedName("lastName")
    @Expose
    private String lastName;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("roles")
    @Expose
    private Set<Role> roles = new HashSet<>();

    public boolean isUser() {return roles.contains(Role.USER);}

    public boolean isAdmin() {return roles.contains(Role.ADMIN);}


}
