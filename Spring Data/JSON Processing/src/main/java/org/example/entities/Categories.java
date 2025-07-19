package org.example.entities;

import com.google.gson.annotations.Expose;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "categories")
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Products> getProducts() {
        return products;
    }

    public void setProducts(Set<Products> products) {
        this.products = products;
    }

    @Expose
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Products> products;

    public Categories() {
    }
}
