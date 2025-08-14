package com.kd.Store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_detail_id")
    private int orderDetailId;

    @Column(name = "quantity" , nullable = false)
    private int quantity;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "amount", nullable = false)
    private double amount;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item items;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order orders;
}
