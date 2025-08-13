package com.kd.Store.pagenation;

import com.kd.Store.dto.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaginatedResponseItemID {
    List<ItemDTO> items;
    private int dataCount;
}
