package com.app.account.service;

import com.app.account.models.Account;
import com.app.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository repository;

    public Account create(Account acct) {
        repository.save(acct);
        return acct;
    }

    public Optional<Account> read(long id) {
        return repository.findById(id);
    }

    public List<Account> readAll() {
        return repository.findAll();
    }
}
