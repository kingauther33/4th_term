package com.example.hellosercurity.api;

import com.example.hellosercurity.dto.AccountDTO;
import com.example.hellosercurity.entity.Account;
import com.example.hellosercurity.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountApi {

    @Autowired
    AccountService accountService;
    @RequestMapping(method = RequestMethod.POST,path = "register")
    public Account register(@RequestBody AccountDTO accountDTO){
        return accountService.create(accountDTO);
    }
}
