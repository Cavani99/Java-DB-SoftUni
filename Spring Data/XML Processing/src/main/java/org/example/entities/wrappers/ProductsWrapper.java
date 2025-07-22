package org.example.entities.wrappers;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.entities.Products;
import org.example.entities.dto.ProductsDto;

import java.util.List;

@XmlRootElement(name = "products")
public class ProductsWrapper {

    private List<Products> products;


    @XmlElement(name = "product")
    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public ProductsWrapper() {
    }

    public ProductsWrapper(List<Products> products) {
        this.products = products;
    }
}
