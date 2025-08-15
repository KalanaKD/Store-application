package com.kd.Store.service;

import com.kd.Store.dto.ItemDTO;
import com.kd.Store.dto.paginated.ItemPagenateDTO;
import com.kd.Store.entity.Item;
import com.kd.Store.pagenation.PaginatedResponseItemID;
import com.kd.Store.repo.ItemRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<ItemDTO> getAllItems(){
        List<Item> items = itemRepo.findAll();
        return modelMapper.map(items, new TypeToken<List<ItemDTO>>(){}.getType());
    }

    public ItemDTO getItemById(int id) {
        Item item = itemRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Customer not found"));
        return modelMapper.map(item, ItemDTO.class);
    }

    public ItemDTO addItem(ItemDTO newItemDTO){
        newItemDTO.setId(0);
        if(itemRepo.existsById(newItemDTO.getId())){
            throw new RuntimeException("Item already exists");
        }
        Item itemEntity = modelMapper.map(newItemDTO, Item.class);
        Item savedItem = itemRepo.save(itemEntity);
        return modelMapper.map(savedItem, ItemDTO.class);
    }

    public ItemDTO updateItem(ItemDTO newItemDTO){
        if (itemRepo.existsById(newItemDTO.getId())) {
            Item item = itemRepo.save(modelMapper.map(newItemDTO, Item.class));
            return modelMapper.map(item, ItemDTO.class);
        }
        else{
            throw new RuntimeException("Customer not found");
        }
    }

    public String deleteItem(int id){
        itemRepo.deleteById(id);
        return "Customer deleted";
    }

    public ItemPagenateDTO getItemByItemAvailability(boolean itemAvailability, int page, int pageSize){
        Page<Item> items = itemRepo.findAllByItemAvailable(itemAvailability, PageRequest.of(page,pageSize));
        if(!items.isEmpty()){
            List<ItemDTO> itemDTOs =
                    modelMapper.map(items.getContent(), new TypeToken<List<ItemDTO>>(){}.getType());
            return new ItemPagenateDTO(itemDTOs, items.getTotalElements());
        }
        else{
            throw new RuntimeException("Items not found (paginated error)");
        }
    }

}
