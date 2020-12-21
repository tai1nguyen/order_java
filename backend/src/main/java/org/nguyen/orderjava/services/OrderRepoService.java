package org.nguyen.orderjava.services;

import static org.nguyen.orderjava.literals.Services.ORDER_REPO_SERVICE;

import java.util.Optional;

import org.nguyen.orderjava.models.jpa.OrderEntryJpa;
import org.nguyen.orderjava.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(ORDER_REPO_SERVICE)
public class OrderRepoService {

    private final OrderRepository orderRepo;

    @Autowired
    OrderRepoService(OrderRepository orderRepository) {
        this.orderRepo = orderRepository;
    }

    public OrderEntryJpa findOrderById(String id) {
        OrderEntryJpa orderEntry = null;
        Optional<OrderEntryJpa> entry = orderRepo.findById(id);

        if (entry.isPresent()) {
            orderEntry = entry.get();
        }

        return orderEntry;
    }

    public void deleteOrderById(String id) {
        orderRepo.deleteById(id);
    }

    public OrderEntryJpa saveOrder(OrderEntryJpa orderEntry) {
        OrderEntryJpa savedEntity = orderRepo.save(orderEntry);

        return savedEntity;
    }
}
