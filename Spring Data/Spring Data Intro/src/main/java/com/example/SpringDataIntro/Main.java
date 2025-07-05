package com.example.SpringDataIntro;

import com.example.SpringDataIntro.models.Account;
import com.example.SpringDataIntro.models.User;
import com.example.SpringDataIntro.repositories.AccountRepository;
import com.example.SpringDataIntro.repositories.UserRepository;
import com.example.SpringDataIntro.services.AccountService;
import com.example.SpringDataIntro.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;

@Component
public class Main implements CommandLineRunner {

    private final UserService userService;
    private final AccountService accountService;

    public Main(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User("Ivan", 25);

        Account account = new Account(new BigDecimal("25000"));
        account.setUser(user);

        user.setAccounts(new HashSet<>() {
            {
                add(account);
            }
        });

        userService.registerUser(user);

        accountService.withdrawMoney(new BigDecimal("20000"), account.getId());
        accountService.transferMoney(new BigDecimal("30000"), account.getId());
    }
}
