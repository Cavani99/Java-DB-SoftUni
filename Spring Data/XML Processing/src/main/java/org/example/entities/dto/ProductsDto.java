package org.example.entities.dto;


import jakarta.xml.bind.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsDto implements Serializable {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "price")
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

    @XmlAttribute(name = "seller")
    private String seller;  // full name

    public ProductsDto(String name, BigDecimal price, String seller) {
        this.name = name;
        this.price = price;
        this.seller = seller;
    }

    public ProductsDto() {
    }
}
