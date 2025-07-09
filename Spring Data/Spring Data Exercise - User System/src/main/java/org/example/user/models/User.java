package org.example.user.models;


import jakarta.persistence.*;
import org.example.user.Annotations.Email;
import org.example.user.Annotations.Password;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Password(minLength = 6, maxLength = 50,
            containsLowercase = true, containsUppercase = true,
            containsDigit = true, containsSpecialSymbol = true)
    private String password;

    @Email
    private String email;

    @Column(name = "registered_on")
    private LocalDateTime registered_on;

    @Column(name = "last_time_logged_in")
    private LocalDateTime lastTimeLogged;

    @Column(name = "age")
    private int age;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getRegistered_on() {
        return registered_on;
    }

    public void setRegistered_on(LocalDateTime registered_on) {
        this.registered_on = registered_on;
    }

    public LocalDateTime getLast_time_logged_in() {
        return lastTimeLogged;
    }

    public void setLast_time_logged_in(LocalDateTime last_time_logged_in) {
        this.lastTimeLogged = last_time_logged_in;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    @Column(name = "is_deleted")
    private boolean is_deleted;

    @OneToOne(optional = false)
    @JoinColumn(name = "born_town_id",
            referencedColumnName = "id")
    private Town bornTown;

    @OneToOne(optional = false)
    @JoinColumn(name = "current_town_id",
            referencedColumnName = "id")
    private Town currentTown;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    public Town getBornTown() {
        return bornTown;
    }

    public void setBornTown(Town bornTown) {
        this.bornTown = bornTown;
    }

    public Town getCurrentTown() {
        return currentTown;
    }

    public void setCurrentTown(Town currentTown) {
        this.currentTown = currentTown;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFullName() {
        return getFirst_name() + " " + getLast_name();
    }

    public void setFullName(String fullName) {
        String[] name = fullName.split("\\s+");

        this.fullName = fullName;
        this.first_name = name[0];
        this.last_name = name[1] != null ? name[1] : "";
    }

    @Transient
    private String fullName;

    @ManyToMany
    @JoinTable(name = "users_friends",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
    private Set<User> friends;

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    @OneToMany(mappedBy = "user")
    private Set<Album> albums;

    public User() {
    }
}
