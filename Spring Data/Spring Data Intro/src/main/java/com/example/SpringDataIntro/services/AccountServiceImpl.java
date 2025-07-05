package com.example.SpringDataIntro.services;

import com.example.SpringDataIntro.models.Account;
import com.example.SpringDataIntro.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {
        Optional<Account> account = accountRepository.findById(id);

        if (account.isPresent()) {
            BigDecimal newAmount = account.get().getBalance().subtract(money);

            if (newAmount.compareTo(new BigDecimal("0")) < 0) {
                throw new RuntimeException("Insufficient Funds");
            }

            account.get().setBalance(newAmount);

            accountRepository.save(account.get());
        }
    }

    @Override
    public void transferMoney(BigDecimal money, Long id) {
        Optional<Account> account = accountRepository.findById(id);

        if (account.isPresent()) {
            BigDecimal newAmount = account.get().getBalance().add(money);

            account.get().setBalance(newAmount);

            accountRepository.save(account.get());
        }
    }
}
