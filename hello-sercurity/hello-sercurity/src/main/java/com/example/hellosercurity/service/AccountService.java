package com.example.hellosercurity.service;

import com.example.hellosercurity.dto.AccountDTO;
import com.example.hellosercurity.entity.Account;
import com.example.hellosercurity.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account create(AccountDTO accountDTO){
        Account account = new Account();
        account.setPasswordHash(passwordEncoder.encode(accountDTO.getPassword()));
        account.setUsername(accountDTO.getUsername());
        account.setRole(1);
        account.setStatus(1);
        return accountRepository.save(account);
    }

    public Optional<Account> findByUsername(String username){
        return accountRepository.findFirstByUsername(username);
    }
}
