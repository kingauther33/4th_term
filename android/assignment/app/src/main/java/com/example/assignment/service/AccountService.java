package com.example.assignment.service;

import com.example.assignment.dto.CredentialDto;
import com.example.assignment.dto.LoginDto;
import com.example.assignment.entity.Account;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AccountService {
    @POST("/api/v1/accounts")
    Call<Account> register(@Body Account account);

    @POST("/api/v1/accounts/authentication")
    Call<CredentialDto> login(@Body LoginDto login);
}
