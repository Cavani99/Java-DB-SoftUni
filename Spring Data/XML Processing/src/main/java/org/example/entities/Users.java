package org.example.entities;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Set;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @XmlAttribute(name = "age")
    @Column(name = "age")
    private int age;

    @XmlAttribute(name = "first-name")
    @Column(name = "first_name")
    private String firstName;

    @XmlAttribute(name = "last-name")
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
