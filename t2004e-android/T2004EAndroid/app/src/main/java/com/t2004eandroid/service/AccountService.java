package com.t2004eandroid.service;

import com.t2004eandroid.dto.CredentialDto;
import com.t2004eandroid.dto.LoginDto;
import com.t2004eandroid.entity.Account;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface AccountService {
    @POST("/api/v1/accounts")
    Call<Account> register(@Body Account account);

    @POST("/api/v1/accounts/authentication")
    Call<CredentialDto> login(@Body LoginDto login);
}
