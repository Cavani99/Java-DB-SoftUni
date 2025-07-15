package org.example.services;

public interface UserService {
    void RegisterUser(String email, String pass, String confirmPass, String fullName);

    void LoginUser(String email, String pass);

    void Logout();
}
