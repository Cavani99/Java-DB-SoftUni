package org.example;

import org.example.services.GameService;
import org.example.services.OrderService;
import org.example.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        //userService.LoginUser("ivan@ivan.com", "Ivan12");
        //userService.Logout();
        /*
        gameService.AddGame("Overwatch", BigDecimal.valueOf(100.00), 15.5, "https://www.youtube.com/watch?v=FqnKB22pOC0", "https://us.battle.net/" +
                "forums/static/images/social-thumbs/overwatch.png", "Overwatch is a" +
                "team-based multiplayer online first-person shooter video game" +
                "developed and published by Blizzard Entertainment.", LocalDate.of(2016, 5, 24));*/

        /*
        List<String> fields = new ArrayList<>();
        fields.add("price=80.00");
        fields.add("size=12.0");
        gameService.EditGame(2, fields);*/

        gameService.DeleteGame(2);

    }
}