package com.kd.Store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String itemName;
    private String itemDescription;
    private BigDecimal itemPrice;
    private int itemQuantity;
    private boolean itemAvailable;

    @OneToMany(mappedBy = "items")
    private Set<OrderDetails> orderDetails;
}
