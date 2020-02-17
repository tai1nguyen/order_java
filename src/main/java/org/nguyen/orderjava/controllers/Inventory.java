package org.nguyen.orderjava.controllers;

import java.util.List;

import org.nguyen.orderjava.models.Bean.BeanType;
import org.nguyen.orderjava.models.jpa.BeanEntry;
import org.nguyen.orderjava.services.InventoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class Inventory {

    private final InventoryService inventoryService;

    Inventory(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/bean")
    public BeanEntry getInventoryDataForBeanType(@RequestParam String type) {
        return inventoryService.getBeanByType(BeanType.getType(type));
    }

    @GetMapping("beans")
    public List<BeanEntry> getInventory() {
        return inventoryService.getAllBeans();
    }
}
