package org.example.services.impl;

import org.example.entities.User;
import org.example.repositories.UserRepo;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void RegisterUser(String email, String pass, String confirmPass, String fullName) {
        if (!email.matches("^[a-zA-Z]+@[a-zA-Z]+.[a-zA-Z]+$")) {
            System.out.println("Incorrect email");
        }

        if (!pass.matches("([a-z])+") || !pass.matches("([A-Z])+") || !pass.matches("(\\d)+")
                || !pass.matches("([\\S\\s]){6,}")) {
            System.out.println("Incorrect password");
        }

        if (!pass.equals(confirmPass)) {
            System.out.println("Confirm password does not match with password");
        }

        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(pass);

        List<User> users = userRepo.findAll();

        user.setAdmin(false);
        if (users.isEmpty()) {
            user.setAdmin(true);
        }

        userRepo.save(user);

        System.out.println(fullName + " was registered");
    }

    @Override
    public void LoginUser(String email, String pass) {
        Optional<User> user = userRepo.findByEmailAndPassword(email, pass);

        if (user.isEmpty()) {
            System.out.println("Incorrect username / password");
        }

        if (user.isPresent()) {
            User user1 = user.get();
            user1.setLogged(true);

            userRepo.save(user1);
            System.out.println("Successfully logged in " + user1.getFullName());
        }
    }
}
