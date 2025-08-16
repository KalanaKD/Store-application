package com.kd.Store.service;

import com.kd.Store.dto.CustomerDTO;
import com.kd.Store.dto.OrderDTO;
import com.kd.Store.dto.OrderSaveRequestDTO;
import com.kd.Store.entity.Customer;
import com.kd.Store.entity.Order;
import com.kd.Store.entity.OrderDetails;
import com.kd.Store.repo.CustomerRepo;
import com.kd.Store.repo.ItemRepo;
import com.kd.Store.repo.OrderDetailsRepo;
import com.kd.Store.repo.OrderRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private OrderDetailsRepo orderDetailsRepo;

    @Autowired
    private ItemRepo itemRepo;

    public List<OrderDTO> getAllOrders(){
        List<Order> orders = orderRepo.findAll();
        return modelMapper.map(orders, new TypeToken<List<OrderDTO>>(){}.getType());
    }

    public OrderDTO getOrderById(int id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Customer not found"));
        return modelMapper.map(order, OrderDTO.class);
    }

    public OrderDTO addOrder(OrderDTO newOrderDTO){
        newOrderDTO.setOrderId(0);
        if(orderRepo.existsById((int) newOrderDTO.getOrderId())){
            throw new RuntimeException("Item already exists");
        }
        Order orderEntity = modelMapper.map(newOrderDTO, Order.class);
        Order savedOrder = orderRepo.save(orderEntity);
        return modelMapper.map(savedOrder, OrderDTO.class);
    }

    public OrderDTO updateOrder(OrderDTO newOrderDTO){
        if (orderRepo.existsById((int)newOrderDTO.getOrderId())) {
            Order order = orderRepo.save(modelMapper.map(newOrderDTO, Order.class));
            return modelMapper.map(order, OrderDTO.class);
        }
        else{
            throw new RuntimeException("Customer not found");
        }
    }

    public String deleteOrder(int id){
        orderRepo.deleteById(id);
        return "Customer deleted";
    }

    @Transactional
    public String saveOrder(OrderSaveRequestDTO orderSaveRequestDTO){
        Customer customerEntity = customerRepo.getReferenceById(orderSaveRequestDTO.getCustomerId());

        Order orders = new Order();
        orders.setCustomer(customerEntity);
        orders.setTotalAmount(orderSaveRequestDTO.getOrderTotal());

        orderRepo.save(orders);

        if (orderRepo.existsById((int) orders.getOrderId())){
            List<OrderDetails> orderDetailsList = modelMapper.map(
                    orderSaveRequestDTO.getSaveRequestDTOList(), new TypeToken<List<OrderDetails>>(){}.getType()
            );

            for (int i=0; i<orderDetailsList.size(); i++){
                orderDetailsList.get(i).setOrders(orders);
                orderDetailsList.get(i).setItems(
                        itemRepo.getReferenceById(orderSaveRequestDTO.getSaveRequestDTOList()
                                .get(i).getItemId())
                );
            }

            if (!orderDetailsList.isEmpty()){
                orderDetailsRepo.saveAll(orderDetailsList);
                return "Order Detail has been saved";
            }
            else{
                throw new RuntimeException("Order not found(order detail error)");
            }
        }

        return null;
    }
}
