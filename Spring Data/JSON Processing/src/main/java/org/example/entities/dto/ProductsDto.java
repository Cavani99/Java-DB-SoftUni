package org.example.entities.dto;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductsDto implements Serializable {
    @Expose
    private String name;

    @Expose
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public String getSeller() {
        return seller;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Expose
    private String seller;  // full name

    public ProductsDto(String name, BigDecimal price, String seller) {
        this.name = name;
        this.price = price;
        this.seller = seller;
    }
}
