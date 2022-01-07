package com.example.javaspringdemo.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "foods")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    @Column(name = "price")
    private double price;
    @Column(name = "status")
    private int status;
}
