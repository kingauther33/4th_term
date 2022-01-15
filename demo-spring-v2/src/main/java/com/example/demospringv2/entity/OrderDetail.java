package com.example.demospringv2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_details")
public class OrderDetail {

    // Cần đặt EmbeddedId
    @EmbeddedId
    private OrderDetailId id;

    private int quantity;
    private double unitPrice;

    @ManyToOne()
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    @MapsId("orderId")
    private Order order;

    @ManyToOne()
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    @MapsId("productId")
    private Product product;
}
