package org.nguyen.orderjava.controllers;

import java.util.List;

import org.nguyen.orderjava.models.BeanTypeEnum;
import org.nguyen.orderjava.models.jpa.InventoryEntryJpa;
import org.nguyen.orderjava.services.InventoryRepoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryRepoService inventoryRepoService;

    InventoryController(InventoryRepoService inventoryRepoService) {
        this.inventoryRepoService = inventoryRepoService;
    }

    @GetMapping("/bean")
    public InventoryEntryJpa getInventoryDataForBeanType(@RequestParam String type) {
        InventoryEntryJpa entry = inventoryRepoService.findEntryByType(BeanTypeEnum.getType(type));

        return entry;
    }

    @GetMapping("/beans")
    public List<InventoryEntryJpa> getInventory() {
        return inventoryRepoService.findAllEntries();
    }
}
