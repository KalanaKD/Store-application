package com.kd.Store.controller;

import com.kd.Store.dto.ItemDTO;
import com.kd.Store.dto.paginated.ItemPagenateDTO;
import com.kd.Store.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v2/")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/getItems")
    public List<ItemDTO> getItems(){
        return itemService.getAllItems();
    }

    @GetMapping("/getItem/{id}")
    public ItemDTO getItemById(@PathVariable int id){
        return itemService.getItemById(id);
    }

    @GetMapping("/getAllItemsByState/{state}/{page}/{pageSize}")
    public ItemPagenateDTO getAllItemsByState(
            @PathVariable boolean state,
            @PathVariable int page,
            @PathVariable int pageSize){
        return itemService.getItemByItemAvailability(state, page, pageSize);
    }

    @PostMapping("/addItem")
    public ItemDTO addItem(@RequestBody ItemDTO newItemDTO){
        return itemService.addItem(newItemDTO);
    }

    @PutMapping("/updateItem")
    public ItemDTO updateItem(@RequestBody ItemDTO newItemDTO){
        return itemService.updateItem(newItemDTO);
    }

    @DeleteMapping("/deleteItem/{id}")
    public String deleteItem(@PathVariable int id){
        return itemService.deleteItem(id);
    }

}
