package com.example.assignment.service;

import static org.junit.Assert.*;

import com.example.assignment.dto.CredentialDto;
import com.example.assignment.dto.LoginDto;
import com.example.assignment.entity.Account;
import com.example.assignment.util.RetrofitGenerator;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import retrofit2.Response;

public class AccountServiceTest {

    AccountService accountService;
    @Before
    public void setUp() throws Exception {
        accountService = RetrofitGenerator.createService(AccountService.class);
    }

    @Test
    public void register() {
        Account account = new Account();
        account.setFirstName("Xuan Hung");
        account.setLastName("Dao");
        account.setAvatar("http://avar.png");
        account.setPhone("09123412");
        account.setAddress("09123412");
        account.setGender(1);
        account.setBirthday("2004-10-20");
        account.setEmail("hongluyen0000001@gmail.com");
        account.setPassword("123");
        try {
            Account savedAccount = accountService.register(account).execute().body();
            System.out.println(savedAccount.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void login() {
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("hongluyen0000001@gmail.com");
        loginDto.setPassword("123");
        try {
            Response<CredentialDto> credentialDtoResponse = accountService.login(loginDto).execute();
            System.out.println(credentialDtoResponse.code());
            System.out.println(credentialDtoResponse.isSuccessful());
            System.out.println(credentialDtoResponse);
            System.out.println(new Gson().toJson(credentialDtoResponse));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}