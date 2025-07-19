package org.example.entities.dto;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class UsersDto implements Serializable {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private String age;
}
