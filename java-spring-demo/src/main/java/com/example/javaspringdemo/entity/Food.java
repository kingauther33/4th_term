package com.example.javaspringdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Food {

    private int id;
    private String name;
    private String description;
    private double price;
    private int status;

}
