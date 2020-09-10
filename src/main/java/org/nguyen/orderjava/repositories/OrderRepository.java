package org.nguyen.orderjava.repositories;

import org.nguyen.orderjava.models.jpa.OrderEntry;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntry, String> {

    public void deleteById(Id id);
}
