package com.kd.Store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderSaveRequestDTO {

    private int customerId;
    private double orderTotal;
    private List<OrderDetailsRequestDTO> saveRequestDTOList;
}
