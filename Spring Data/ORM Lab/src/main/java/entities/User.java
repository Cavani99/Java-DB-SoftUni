package entities;

import orm.EntityManager;

import java.time.LocalDate;

@EntityManager.Table(name = "users")
public class User {
    private long id;
    private String username;
    private int age;
    private LocalDate registration;

    public User(String username, int age, LocalDate registration) {
        this.username = username;
        this.age = age;
        this.registration = registration;
    }
}
