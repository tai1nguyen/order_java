package org.nguyen.orderjava.controllers;

import java.util.HashMap;
import java.util.Map;

import org.nguyen.orderjava.exceptions.OrderNotFoundException;
import org.nguyen.orderjava.models.OrderData;
import org.nguyen.orderjava.models.OrderUpdateData;
import org.nguyen.orderjava.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public OrderData getOrderById(@RequestParam String id) throws OrderNotFoundException {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public Map<String, String> createOrder(@RequestBody OrderData data) {
        String orderId = orderService.saveOrder(data);

        return getResponseJson("id", orderId);
    }

    @PatchMapping("/{id}")
    public Map<String, String> updateOrder(
        @PathVariable String id,
        @RequestBody OrderUpdateData update
    ) throws OrderNotFoundException {
        String orderId = orderService.updateOrder(id, update);

        return getResponseJson("id", orderId);
    }

    private Map<String, String> getResponseJson(String key, String value) {
        Map<String, String> response = new HashMap<>();
        response.put(key, value);

        return response;
    }
}
