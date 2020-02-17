package org.nguyen.orderjava.repositories;

import org.nguyen.orderjava.models.jpa.OrderEntry;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntry, String> {}
