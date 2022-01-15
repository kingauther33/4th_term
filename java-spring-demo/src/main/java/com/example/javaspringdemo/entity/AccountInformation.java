package com.example.javaspringdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class AccountInformation {

    @Id
    @Column(name = "id")
    private int id;
    @OneToOne(mappedBy = "accountInformation", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH}, fetch = FetchType.LAZY)
    private Account account;
    private String phone;
    private String address;
}
