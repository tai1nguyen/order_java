package org.nguyen.orderjava.services;

import java.util.Optional;

import org.nguyen.orderjava.repositories.OrderRepository;
import org.nguyen.orderjava.models.jpa.OrderEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepo;

    @Autowired
    OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepo = orderRepository;
    }

    @Override
    public OrderEntry getOrderById(String id) {
        OrderEntry orderEntry = null;
        Optional<OrderEntry> entry = orderRepo.findById(id);

        if (entry.isPresent()) {
            orderEntry = entry.get();
        }

        return orderEntry;
    }

    @Override
    public void deleteOrderById(String id) {
        // TODO Auto-generated method stub
    }

    @Override
    public void updateOrderById(String id) {
        // TODO Auto-generated method stub
    }
    
}