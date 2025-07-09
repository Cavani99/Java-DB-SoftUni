package org.example.user;

import org.example.user.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Main implements CommandLineRunner {

    private UserService userService;

    public Main(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.findUsersByEmailProvider("gmail.com");
    }
}