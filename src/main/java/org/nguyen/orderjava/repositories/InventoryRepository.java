package org.nguyen.orderjava.repositories;

import org.nguyen.orderjava.models.jpa.BeanEntry;
import org.springframework.data.repository.CrudRepository;

public interface InventoryRepository extends CrudRepository<BeanEntry, String> {}
