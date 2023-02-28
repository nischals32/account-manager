package com.app.account.controller;

import com.app.account.models.Account;
import com.app.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public List<Account> read() {
        return accountService.readAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> read(@PathVariable("id") Long id) {
        Optional<Account> foundStudent = accountService.read(id);
        return foundStudent.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Account> create(@RequestBody Account account)
            throws URISyntaxException {
        Account createdAcct = accountService.create(account);
        if (createdAcct == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdAcct.getId())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(createdAcct);
        }
    }

}
