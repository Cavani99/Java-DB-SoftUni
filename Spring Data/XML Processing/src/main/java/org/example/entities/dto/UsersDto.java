package org.example.entities.dto;


import java.io.Serializable;
import java.util.Set;

public class UsersDto implements Serializable {


    private String firstName;


    private String lastName;

    private Set<soldProductsDto> soldProducts;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Set<soldProductsDto> getSoldProducts() {
        return soldProducts;
    }

    public UsersDto(String firstName, String lastName, Set<soldProductsDto> products) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.soldProducts = products;
    }
}
