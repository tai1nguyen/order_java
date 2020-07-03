package org.nguyen.orderjava.services;

import static org.nguyen.orderjava.literals.Services.ORDER_MAPPER_SERVICE;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.nguyen.orderjava.models.BeanType;
import org.nguyen.orderjava.models.OrderContentData;
import org.nguyen.orderjava.models.OrderData;
import org.nguyen.orderjava.models.OrderUpdateData;
import org.nguyen.orderjava.models.jpa.InventoryEntry;
import org.nguyen.orderjava.models.jpa.OrderContentEntry;
import org.nguyen.orderjava.models.jpa.OrderEntry;
import org.springframework.stereotype.Service;

@Service(ORDER_MAPPER_SERVICE)
public class OrderMapperService {

    /**
     * Maps an order entry from the database into an object
     * that can be consumed by the client.
     * @param id
     * @param orderEntry
     * @param inventoryEntries
     * @return OrderData
     */
    public OrderData mapOrderEntryToOrderData(String id, OrderEntry orderEntry, List<InventoryEntry> inventoryEntries) {
        List<OrderContentEntry> orderContentEntry = orderEntry.getBeans();
        List<OrderContentData> orderContentData = buildOrderContents(orderContentEntry);
        BigDecimal price = getTotalPrice(orderContentData, inventoryEntries);

        OrderData orderData = new OrderData();

        orderData.setOrderedBy(orderEntry.getOrderedBy());
        orderData.setBeans(orderContentData);
        orderData.setPrice(price);
        orderData.setId(id);

        return orderData;
    }

    /**
     * Takes order data from the client and prepares it
     * for insertion into the database by converting it into
     * an object that can be consumed by the JPA layer.
     * @param orderData
     * @param inventoryEntries
     * @return OrderEntry
     */
    public OrderEntry mapOrderDataToOrderEntry(OrderData orderData) {
        List<OrderContentData> beans = orderData.getBeans();

        // The IDs of the order content entry and the order entry will be set by the JPA layer.
        List<OrderContentEntry> contentEntryList = buildOrderContentEntries(beans);
        OrderEntry orderEntry = buildOrderEntry(orderData, contentEntryList);
        
        return orderEntry;
    }

    /**
     * Takes an order entry and updates it with the provided order update data.
     * @param orderEntry
     * @param update
     * @return OrderEntry
     */
    public OrderEntry updateOrderEntry(OrderEntry orderEntry, OrderUpdateData update) {
        List<OrderContentEntry> beanAdditions = buildOrderContentEntries(update.getBeanAdditions());
        List<OrderContentEntry> beanUpdates = buildOrderContentEntries(update.getBeanUpdates());

        // Add beans.
        for (OrderContentEntry beanAddition : beanAdditions) {
            orderEntry.addBean(beanAddition);
        }

        // Remove beans.
        for (OrderContentData beanDeletion : update.getBeanDeletions()) {
            orderEntry.removeBean(beanDeletion.getBeanType());
        }

        // Update beans.
        for (OrderContentEntry bean : orderEntry.getBeans()) {
            OrderContentEntry beanUpdate = findOrderContentByBeanType(
                BeanType.getType(bean.getBeanType()),
                beanUpdates
            );

            if (beanUpdate != null) {
                bean.setQuantity(beanUpdate.getQuantity());
            }
        }

        return orderEntry;
    }

    private List<OrderContentData> buildOrderContents(List<OrderContentEntry> contentEntries) {
        List<OrderContentData> orderContents = new ArrayList<>();
        
        for (OrderContentEntry content : contentEntries) {
            BeanType type = BeanType.getType(content.getBeanType());
            OrderContentData bean = new OrderContentData();

            bean.setBeanType(type);
            bean.setQuantity(Integer.valueOf(content.getQuantity()));
            orderContents.add(bean);
        }

        return orderContents;
    }

    private BigDecimal getTotalPrice(List<OrderContentData> orderContent, List<InventoryEntry> inventory) {
        BigDecimal totalPrice = null;

        for (OrderContentData bean : orderContent) {
            InventoryEntry beanData = findBeanDataByType(inventory, bean.getBeanType());
            BigDecimal units = new BigDecimal(bean.getQuantity());
            BigDecimal pricePerUnit = new BigDecimal(beanData.getPricePerUnit());
            BigDecimal result = pricePerUnit.multiply(units);

            if (totalPrice == null) {
                totalPrice = result;
            }
            else  {
                totalPrice = totalPrice.add(result);
            }
        }

        return totalPrice;
    }

    private List<OrderContentEntry> buildOrderContentEntries(List<OrderContentData> beans) {
        List<OrderContentEntry> contentList = new ArrayList<>();

        if (beans != null) {
            for (OrderContentData bean : beans) {
                BeanType type = bean.getBeanType();
                OrderContentEntry contentEntry = new OrderContentEntry();
    
                contentEntry.setBeanType(type.getName());
                contentEntry.setQuantity(bean.getQuantity().toString());
                contentList.add(contentEntry);
            }
        }

        return contentList;
    }

    private OrderEntry buildOrderEntry(OrderData orderData, List<OrderContentEntry> orderContent) {
        OrderEntry orderEntry = new OrderEntry();

        if (orderContent != null) {
            for (OrderContentEntry content : orderContent) {
                orderEntry.addBean(content);
            }
        }

        orderEntry.setOrderedBy(orderData.getOrderedBy());

        return orderEntry;
    }

    private OrderContentEntry findOrderContentByBeanType(BeanType type, List<OrderContentEntry> orderContent) {
        return orderContent
                .stream()
                .filter(suspect -> type.equals(BeanType.getType(suspect.getBeanType())))
                .findAny()
                .orElse(null);
    }

    private InventoryEntry findBeanDataByType(List<InventoryEntry> inventory, BeanType type) {
        return inventory
                .stream()
                .filter(suspect -> type.equals(suspect.getBeanType()))
                .findAny()
                .orElse(null);
    }
}
