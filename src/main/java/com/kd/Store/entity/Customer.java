package com.kd.Store.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id" , length = 10, nullable = false)
    private int id;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;

}
