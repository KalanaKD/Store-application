package com.kd.Store.controller;

import com.kd.Store.dto.OrderDTO;
import com.kd.Store.dto.OrderSaveRequestDTO;
import com.kd.Store.entity.Customer;
import com.kd.Store.service.OrderService;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v3/")
public class OderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/getOrders")
    public List<OrderDTO> getOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/getOrder/{id}")
    public OrderDTO getOrderById(@PathVariable int id){
        return orderService.getOrderById(id);
    }

    @PostMapping("/addOrder")
    public OrderDTO addOrder(@RequestBody OrderDTO newOrderDTO){
        return orderService.addOrder(newOrderDTO);
    }

    @PutMapping("/updateOrder")
    public OrderDTO updateOrder(@RequestBody OrderDTO newOrderDTO){
        return orderService.updateOrder(newOrderDTO);
    }

    @DeleteMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable int id){
        return orderService.deleteOrder(id);
    }

    @PostMapping("/get-order-by")
    public String saveOrder(@RequestBody OrderSaveRequestDTO  newOrderSaveDTO){
        return orderService.saveOrder(newOrderSaveDTO);
    }
}
