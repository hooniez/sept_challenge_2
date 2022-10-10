package com.example.account.controller;

import com.example.account.dao.AccountRepository;
import com.example.account.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/account")
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        return ResponseEntity.accepted().body(accountRepository.save(account));
    }

    @GetMapping(path="/account{accountNumber}")
    public ResponseEntity<?> findAccountByAccountNumber(@PathVariable(
            "accountNumber") Long accountNumber) {
        return ResponseEntity.accepted().body(accountRepository.findByAccountNumber(accountNumber).get());
    }

    @GetMapping(path="/accounts")
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    @PutMapping("/account")
    public ResponseEntity<?> updateAccount(@RequestBody Account account) {
        return ResponseEntity.accepted().body(accountRepository.save(account));
    }

    @Transactional
    @DeleteMapping("/account{accountNumber}")
    public void deleteAccount(@PathVariable("accountNumber") Long accountNumber) {
        accountRepository.deleteByAccountNumber(accountNumber);
    }

}
