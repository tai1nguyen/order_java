package org.nguyen.orderjava.repositories;

import org.nguyen.orderjava.models.jpa.InventoryEntryJpa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends CrudRepository<InventoryEntryJpa, String> {}
