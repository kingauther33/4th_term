package com.example.javaspringdemo.controller;

import com.example.javaspringdemo.dto.AccountDto;
import com.example.javaspringdemo.entity.Account;
import com.example.javaspringdemo.entity.AccountInformation;
import com.example.javaspringdemo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/accounts")
public class AccountController {

    @Autowired
    AccountRepository accountRepository; // phải có thằng service

    @RequestMapping(method = RequestMethod.POST)
    public Account save(@RequestBody AccountDto accountDto) {
        // tạo account
        Account account = new Account();
        account.setId(accountDto.getId());
        account.setUsername(accountDto.getUsername());
        account.setPassword(accountDto.getPassword());

        // tạo account information từ dto truyền lên (dữ liệu)
        AccountInformation accountInformation = new AccountInformation();
        accountInformation.setId(accountDto.getId()); // lấy trùng với id của account
        accountInformation.setAddress(accountDto.getAddress());
        accountInformation.setPhone(accountDto.getPhone());

        // set mối quan hệ
        account.setAccountInformation(accountInformation); // set mối quan hệ
        accountInformation.setAccount(account);

        return accountRepository.save(account);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Account getDetail(@PathVariable int id) {
        return accountRepository.findById(id).orElse(null);
    }
}
