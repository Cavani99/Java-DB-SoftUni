package org.example;

import org.example.services.GameService;
import org.example.services.OrderService;
import org.example.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Main implements CommandLineRunner {

    private UserService userService;
    private OrderService orderService;
    private GameService gameService;

    public Main(UserService userService, OrderService orderService, GameService gameService) {
        this.userService = userService;
        this.orderService = orderService;
        this.gameService = gameService;
    }


    @Override
    public void run(String... args) throws Exception {

        //userService.RegisterUser("ivan@ivan.com", "Ivan12", "Ivan12", "Ivan");
        userService.LoginUser("ivan@ivan.com", "Ivan12");
    }
}