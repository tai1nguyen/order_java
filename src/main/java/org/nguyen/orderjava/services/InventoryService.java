package org.nguyen.orderjava.services;

import java.util.List;

import org.nguyen.orderjava.models.Bean.BeanType;
import org.nguyen.orderjava.models.jpa.BeanEntry;

public interface InventoryService {

    BeanEntry getBeanByType(BeanType beanType);

    List<BeanEntry> getAllBeans();
}
