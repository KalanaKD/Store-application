package com.kd.Store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDTO {
    private int id;
    private String itemName;
    private String itemDescription;
    private BigDecimal itemPrice;
    private int itemQuantity;
    private boolean itemAvailable;
}
