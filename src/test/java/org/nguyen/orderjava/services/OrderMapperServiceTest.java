package org.nguyen.orderjava.services;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.nguyen.orderjava.models.BeanType;
import org.nguyen.orderjava.models.OrderContentData;
import org.nguyen.orderjava.models.OrderData;
import org.nguyen.orderjava.models.OrderUpdateData;
import org.nguyen.orderjava.models.jpa.InventoryEntry;
import org.nguyen.orderjava.models.jpa.OrderContentEntry;
import org.nguyen.orderjava.models.jpa.OrderEntry;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class OrderMapperServiceTest {

    @InjectMocks
    OrderMapperService orderMapperService;

    @Test
    void mapOrderEntryToOrderData_ShouldReturnOrderData_GivenOrderEntryAndBeanData() {
        OrderData expectedOrder = mockOrderData();
        List<OrderContentData> expectedOrderBeans = new ArrayList<>();
        OrderEntry mockOrderEntry = mockOrderEntry("1");

        expectedOrderBeans.add(mockOrderContentData(BeanType.ARABICA, "1"));
        expectedOrder.setBeans(expectedOrderBeans);
        mockOrderEntry.addBean(mockOrderContentEntry(BeanType.ARABICA, "1"));

        OrderData result = orderMapperService.mapOrderEntryToOrderData("1", mockOrderEntry, mockBeans());

        assertEquals(expectedOrder, result);
    }

    @Test
    void mapOrderDataToOrderEntry_ShouldReturnOrderEntry_GivenOrderDataAndBeanData() {
        OrderEntry expectedOrderEntry = mockOrderEntry(null);
        OrderData mockOrderData = mockOrderData();
        List<OrderContentData> mockOrderDataBeans = new ArrayList<>();

        expectedOrderEntry.addBean(mockOrderContentEntry(BeanType.ARABICA, "1"));
        mockOrderDataBeans.add(mockOrderContentData(BeanType.ARABICA, "1"));
        mockOrderData.setBeans(mockOrderDataBeans);

        OrderEntry result = orderMapperService.mapOrderDataToOrderEntry(mockOrderData);

        assertEquals(expectedOrderEntry, result);
    }

    @Test
    void updateOrderEntry_ShouldUpdateAnOrderEntryWithOrderUpdateData_GivenOrderUpdateData() {
        OrderEntry mock = mockOrderEntry("1");
        OrderEntry expected = mockOrderEntry("1");
        OrderUpdateData udpateData = mockOrderUpdateData();

        mock.addBean(mockOrderContentEntry(BeanType.ARABICA, "1"));
        mock.addBean(mockOrderContentEntry(BeanType.EXCELSA, "2"));
        expected.addBean(mockOrderContentEntry(BeanType.EXCELSA, "4"));
        expected.addBean(mockOrderContentEntry(BeanType.LIBERIAN, "3"));

        OrderEntry result = orderMapperService.updateOrderEntry(mock, udpateData);

        assertEquals(expected, result);
    }

    private OrderUpdateData mockOrderUpdateData() {
        OrderUpdateData mock = new OrderUpdateData();
        List<OrderContentData> additions = new ArrayList<>();
        List<OrderContentData> deletions = new ArrayList<>();
        List<OrderContentData> updates = new ArrayList<>();
        
        additions.add(mockOrderContentData(BeanType.LIBERIAN, "3"));
        deletions.add(mockOrderContentData(BeanType.ARABICA, null));
        updates.add(mockOrderContentData(BeanType.EXCELSA, "4"));

        mock.setBeanAdditions(additions);
        mock.setBeanDeletions(deletions);
        mock.setBeanUpdates(updates);

        return mock;
    }

    private OrderContentData mockOrderContentData(BeanType type, String quantity) {
        OrderContentData mock = new OrderContentData();

        mock.setBeanType(type);
        
        if (quantity != null) {
            mock.setQuantity(Integer.parseInt(quantity));
        }
        
        return mock;
    }

    private OrderData mockOrderData() {
        OrderData mock = new OrderData();

        mock.setId("1");
        mock.setPrice(new BigDecimal("1.05"));
        mock.setOrderedBy("foo");

        return mock;
    }

    private OrderEntry mockOrderEntry(String id) {
        OrderEntry mock = new OrderEntry();

        if (id != null) {
            mock.setId(id);
        }

        mock.setOrderedBy("foo");

        return mock;
    }

    private OrderContentEntry mockOrderContentEntry(BeanType type, String quantity) {
        OrderContentEntry mock = new OrderContentEntry();

        mock.setBeanType(type.getName());
        mock.setQuantity(quantity);

        return mock;
    }

    private InventoryEntry mockBeanData() {
        InventoryEntry mock = new InventoryEntry();

        mock.setBeanType(BeanType.ARABICA);
        mock.setPricePerUnit("1.05");
        mock.setQuantity("10");
        mock.setWeightPerUnit("0.05");

        return mock;
    }

    private List<InventoryEntry> mockBeans() {
        List<InventoryEntry> mock = new ArrayList<>();

        mock.add(mockBeanData());

        return mock;
    }
}
