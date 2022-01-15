package com.example.javaspringdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDto {
    private int id;
    private String username;
    private String password;

    // account information
    private String phone;
    private String address;
}
