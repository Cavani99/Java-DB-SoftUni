package com.example.SpringDataIntro.services;

import com.example.SpringDataIntro.models.Account;
import com.example.SpringDataIntro.models.User;
import com.example.SpringDataIntro.repositories.AccountRepository;
import com.example.SpringDataIntro.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void registerUser(User user) {
        Optional<User> findUser = userRepository.getByUsername(user.getUsername());

        if (findUser.isEmpty()) {
            userRepository.save(user);

            for (Account account : user.getAccounts()) {
                accountRepository.save(account);
            }
        }
    }
}
