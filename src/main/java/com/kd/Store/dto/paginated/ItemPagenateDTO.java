package com.kd.Store.dto.paginated;

import com.kd.Store.dto.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPagenateDTO {
    List<ItemDTO> items;
    private long dataCount;
}
