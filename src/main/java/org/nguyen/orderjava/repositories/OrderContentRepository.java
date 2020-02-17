package org.nguyen.orderjava.repositories;

import org.nguyen.orderjava.models.jpa.OrderContentEntry;
import org.springframework.data.repository.CrudRepository;

public interface OrderContentRepository extends CrudRepository<OrderContentEntry, String> {}
