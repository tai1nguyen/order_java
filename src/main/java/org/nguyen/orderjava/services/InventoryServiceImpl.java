package org.nguyen.orderjava.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.nguyen.orderjava.repositories.InventoryRepository;
import org.nguyen.orderjava.models.Bean.BeanType;
import org.nguyen.orderjava.models.jpa.BeanEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepo;

    @Autowired
    InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepo = inventoryRepository;
    }

    @Override
    public BeanEntry getBeanByType(BeanType beanType) {
        BeanEntry result = null;
        Optional<BeanEntry> entry = inventoryRepo.findById(beanType.getName());

        if (entry.isPresent()) {
            result = entry.get();
        }

        return result;
    }

    @Override
    public List<BeanEntry> getAllBeans() {
        List<BeanEntry> inventory = new ArrayList<BeanEntry>();

        Iterable<BeanEntry> iterator = inventoryRepo.findAll();

        for (BeanEntry entry : iterator) {
            inventory.add(entry);
        }

        return inventory;
    }
}
