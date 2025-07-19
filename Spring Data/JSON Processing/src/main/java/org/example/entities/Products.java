package org.example.entities;

import com.google.gson.annotations.Expose;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Expose
    @Column(name = "name")
    private String name;

    @Expose
    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne()
    @JoinColumn(name = "buyer_id",
            referencedColumnName = "id")
    private Users buyer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "seller_id",
            referencedColumnName = "id")
    private Users seller;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Users getBuyer() {
        return buyer;
    }

    public void setBuyer(Users buyer) {
        this.buyer = buyer;
    }

    public Users getSeller() {
        return seller;
    }

    public void setSeller(Users seller) {
        this.seller = seller;
    }

    public Set<Categories> getCategories() {
        return categories;
    }

    public void setCategories(Set<Categories> categories) {
        this.categories = categories;
    }

    @ManyToMany
    @JoinTable(name = "products_categories",
            joinColumns = @JoinColumn(name = "products_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id", referencedColumnName = "id"))
    private Set<Categories> categories;

    public Products() {
    }
}
