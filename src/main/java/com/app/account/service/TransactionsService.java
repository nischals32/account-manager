package com.app.account.service;

import com.app.account.models.OperationType;
import com.app.account.models.Transactions;
import com.app.account.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionsService {
    private TransactionsRepository repository;
    public TransactionsService(@Autowired TransactionsRepository repository) {
        this.repository = repository;
    }

    public Transactions create(Transactions tx) {
        double newBalance = computeNewBalance(tx.getAccount().getBalance(),tx.getOperationType(),tx.getAmount());
        tx.getAccount().setBalance(newBalance);
        repository.save(tx);
        return tx;
    }

    private double computeNewBalance(double balance, OperationType operationType, double amount) {
        switch(operationType.getDescription()) {
            case("WITHDRAWAL"):
                return balance - amount;
            case("PAYMENT"):
                return balance + amount;
            default:
                return balance;
        }
    }
    public Optional<Transactions> read(long id) {
        return repository.findById(id);
    }

    public List<Transactions> readAll() {
        return repository.findAll();
    }
}
