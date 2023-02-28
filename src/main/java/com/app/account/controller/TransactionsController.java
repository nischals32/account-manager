package com.app.account.controller;

import com.app.account.models.Account;
import com.app.account.models.OperationType;
import com.app.account.models.Transactions;
import com.app.account.service.AccountService;
import com.app.account.service.OperationTypeService;
import com.app.account.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {
    @Autowired
    private TransactionsService transactionsService;
    @Autowired
    private AccountService accountService;

    @Autowired
    private OperationTypeService operationTypeService;

    @GetMapping("/")
    public List<Transactions> read() {
        return transactionsService.readAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transactions> read(@PathVariable("id") Long id) {
        Optional<Transactions> foundTx = transactionsService.read(id);
        return foundTx.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity create(@RequestBody TransactionsDTO transactionsDto)
            throws URISyntaxException {
        Optional<Account> account = accountService.read(transactionsDto.getAccount_id());
        if(account.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account with ID " +
                            transactionsDto.getAccount_id() + " not found in system");


        Optional<OperationType> operationType = operationTypeService.read(transactionsDto.getOperation_type_id());
        if(operationType.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Operation type with ID " +
                    transactionsDto.getOperation_type_id() + " not found in system");

        Transactions inputTx = new Transactions(account.get(), operationType.get(), transactionsDto.getAmount());
        Transactions createdTx = transactionsService.create(inputTx);
        if (createdTx == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdTx.getId())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(createdTx);
        }
    }
}
