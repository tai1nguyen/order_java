package org.nguyen.orderjava.services;

import static org.nguyen.orderjava.literals.Services.ORDER_SERVICE;

import java.util.List;

import org.nguyen.orderjava.exceptions.OrderNotFoundException;
import org.nguyen.orderjava.models.OrderData;
import org.nguyen.orderjava.models.OrderUpdateData;
import org.nguyen.orderjava.models.jpa.InventoryEntry;
import org.nguyen.orderjava.models.jpa.OrderEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(ORDER_SERVICE)
public class OrderService {

    private final OrderRepoService orderRepoService;
    private final InventoryRepoService inventoryRepoService;
    private final OrderMapperService orderMapperService;

    @Autowired
    OrderService(
        OrderRepoService orderRepoService,
        InventoryRepoService inventoryRepoService,
        OrderMapperService orderMapperService
    ) {
        this.orderRepoService = orderRepoService;
        this.inventoryRepoService = inventoryRepoService;
        this.orderMapperService = orderMapperService;
    }

    @Transactional
    public OrderData getOrderById(String id) throws OrderNotFoundException {
        OrderEntry orderEntry = orderRepoService.findOrderById(id);

        if (orderEntry != null) {
            List<InventoryEntry> beanData = inventoryRepoService.findAllEntries();

            return orderMapperService.mapOrderEntryToOrderData(id, orderEntry, beanData);
        }
        else {
            throw new OrderNotFoundException(id);
        }
    }

    @Transactional
    public String updateOrder(String id, OrderUpdateData update) throws OrderNotFoundException {
        OrderEntry orderEntry = orderRepoService.findOrderById(id);

        if (orderEntry != null) {
            OrderEntry updatedOrder = orderMapperService.updateOrderEntry(orderEntry, update);

            return orderRepoService.saveOrder(updatedOrder).getId();
        }
        else {
            throw new OrderNotFoundException(id);
        }
    }

    @Transactional
    public void deleteOrder(String id) throws OrderNotFoundException {
        try {
            orderRepoService.deleteOrderById(id);
        }
        catch (EmptyResultDataAccessException ex) {
            throw new OrderNotFoundException(id);
        }
    }

    @Transactional
    public String saveOrder(OrderData orderData) {
        OrderEntry orderEntry = orderMapperService.mapOrderDataToOrderEntry(orderData);

        return orderRepoService.saveOrder(orderEntry).getId();
    }
}
