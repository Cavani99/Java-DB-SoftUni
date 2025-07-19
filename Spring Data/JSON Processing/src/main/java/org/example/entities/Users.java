package org.example.entities;

import com.google.gson.annotations.Expose;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Expose
    @Column(name = "age")
    private int age;

    @Expose
    @Column(name = "first_name")
    private String firstName;

    @Expose
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "buyer")
    private Set<Products> boughtProducts;

    @OneToMany(mappedBy = "seller")
    private Set<Products> soldProducts;

    @ManyToMany
    @JoinTable(name = "users_friends",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
    private Set<Users> friends;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Products> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(Set<Products> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    public Set<Products> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<Products> soldProducts) {
        this.soldProducts = soldProducts;
    }

    public Set<Users> getFriends() {
        return friends;
    }

    public void setFriends(Set<Users> friends) {
        this.friends = friends;
    }

    public Users() {
    }
}
