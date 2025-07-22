package org.example.entities.wrappers;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.entities.dto.ProductsDto;

import java.util.List;

@XmlRootElement(name = "products")
public class ProductsDtoWrapper {

    private List<ProductsDto> products;

    public ProductsDtoWrapper() {
    }

    public ProductsDtoWrapper(List<ProductsDto> products) {
        this.products = products;
    }

    @XmlElement(name = "product")
    public List<ProductsDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsDto> products) {
        this.products = products;
    }
}
