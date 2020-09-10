package org.nguyen.orderjava.repositories;

import org.nguyen.orderjava.models.jpa.OrderEntryJpa;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntryJpa, String> {

    public void deleteById(Id id);
}
