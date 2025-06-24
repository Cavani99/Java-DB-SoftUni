package entities;

import orm.EntityManager;

import java.time.LocalDate;

@EntityManager.Table(name = "users")
public class User {

    @EntityManager.Id
    @EntityManager.Column(name = "id")
    private long id;

    @EntityManager.Column(name = "username")
    private String username;

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }

    @EntityManager.Column(name = "age")
    private int age;
    @EntityManager.Column(name = "registration")
    private LocalDate registration;

    public String getPassword() {
        return password;
    }

    @EntityManager.Column(name = "password")
    private String password;

    public User() {
    }

    public User(Long id, String username, int age, LocalDate registration) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.registration = registration;
    }

    public User(String username, int age, LocalDate registration) {
        this.username = username;
        this.age = age;
        this.registration = registration;
    }

    public User(Long id, String username, int age, LocalDate registration, String password) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.registration = registration;
        this.password = password;
    }

    public LocalDate getRegistration() {
        return registration;
    }
}
