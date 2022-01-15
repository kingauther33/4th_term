package com.example.demospringv2.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    private int id;
    private double totalPrice;
    private String shipName;
    private String shipAddress;
    private String shipPhone;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    // Order thuộc về ai
    private int customerId;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> orderDetails;
}
