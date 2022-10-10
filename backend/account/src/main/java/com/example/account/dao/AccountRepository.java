package com.example.account.dao;

import com.example.account.model.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findAll();
    Optional<Account> findByAccountNumber(Long accountNumber);
    void deleteByAccountNumber(Long accountNumber);

}
