package org.example.entities.dto;

import java.math.BigDecimal;

public class soldProductsDto {
    private String name;
    private BigDecimal price;
    private String buyerFirstName;
    private String buyerLastName;

    public soldProductsDto(String name, BigDecimal price, String buyerFirstName, String buyerLastName) {
        this.name = name;
        this.price = price;
        this.buyerFirstName = buyerFirstName;
        this.buyerLastName = buyerLastName;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getBuyerFirstName() {
        return buyerFirstName;
    }

    public String getBuyerLastName() {
        return buyerLastName;
    }
}