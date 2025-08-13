package com.kd.Store.controller;

import com.kd.Store.dto.CustomerDTO;
import com.kd.Store.entity.Customer;
import com.kd.Store.entity.Order;
import com.kd.Store.service.CustomerService;
import jakarta.persistence.OneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/getCustomer")
    public List<CustomerDTO> getCustomer(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/getCustomer/{id}")
    public CustomerDTO getCustomerById(@PathVariable int id){
        return customerService.getCustomerById(id);
    }

    @PostMapping("/addCustomer")
    public CustomerDTO addCustomer(@RequestBody CustomerDTO newCustomerDTO){
        return customerService.addCustomer(newCustomerDTO);
    }

    @PutMapping("/updateCustomer")
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO newCustomerDTO){
        return customerService.updateCustomer(newCustomerDTO);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable int id){
        return customerService.deleteCustomer(id);
    }

}
