package com.kd.Store.service;

import com.kd.Store.dto.CustomerDTO;
import com.kd.Store.entity.Customer;
import com.kd.Store.repo.CustomerRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers = customerRepo.findAll();
        return modelMapper.map(customers, new TypeToken<List<CustomerDTO>>(){}.getType());
    }

    public CustomerDTO getCustomerById(int id) {
        Customer customer = customerRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Customer not found"));
        return modelMapper.map(customer, CustomerDTO.class);
    }

    public CustomerDTO addCustomer(CustomerDTO newCustomerDTO){
        newCustomerDTO.setId(0);
        if(customerRepo.existsById(newCustomerDTO.getId())){
            throw new RuntimeException("Customer already exists");
        }
        Customer customerEntity = modelMapper.map(newCustomerDTO, Customer.class);
        Customer savedCustomer = customerRepo.save(customerEntity);
        return modelMapper.map(savedCustomer, CustomerDTO.class);
    }

    public CustomerDTO updateCustomer(CustomerDTO newCustomerDTO){
        if (customerRepo.existsById(newCustomerDTO.getId())) {
            Customer customer = customerRepo.save(modelMapper.map(newCustomerDTO, Customer.class));
            return modelMapper.map(customer, CustomerDTO.class);
        }
        else{
            throw new RuntimeException("Customer not found");
        }
    }

    public String deleteCustomer(int id){
        customerRepo.deleteById(id);
        return "Customer deleted";
    }
}
