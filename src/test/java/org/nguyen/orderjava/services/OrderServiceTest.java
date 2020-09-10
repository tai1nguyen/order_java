package org.nguyen.orderjava.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.nguyen.orderjava.exceptions.OrderNotFoundException;
import org.nguyen.orderjava.models.BeanType;
import org.nguyen.orderjava.models.OrderData;
import org.nguyen.orderjava.models.OrderUpdateData;
import org.nguyen.orderjava.models.jpa.InventoryEntry;
import org.nguyen.orderjava.models.jpa.OrderEntry;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepoService orderRepoService;

    @Mock
    private InventoryRepoService inventoryRepoService;

    @Mock
    private OrderMapperService orderMapperService;

    @Test
    void getOrderById_ShouldCallOrderRepoForData_GivenOrderId() throws OrderNotFoundException {
        when(orderRepoService.findOrderById("test")).thenReturn(mockOrderEntry());
        when(inventoryRepoService.findAllEntries()).thenReturn(mockInventoryList());

        orderService.getOrderById("test");

        verify(orderRepoService, times(1)).findOrderById("test");
    }

    @Test
    void getOrderById_ShouldCallInventoryRepoForData_GivenOrderId() throws OrderNotFoundException {
        when(orderRepoService.findOrderById("test")).thenReturn(mockOrderEntry());
        when(inventoryRepoService.findAllEntries()).thenReturn(mockInventoryList());

        orderService.getOrderById("test");

        verify(inventoryRepoService, times(1)).findAllEntries();
    }

    @Test
    void getOrderById_ShouldCallMapperServiceWithBeanAndOrderData_GivenOrderId() throws OrderNotFoundException {
        OrderEntry mockOrderEntry = mockOrderEntry();
        List<InventoryEntry> mockInventoryList = mockInventoryList();

        when(orderRepoService.findOrderById("test")).thenReturn(mockOrderEntry);
        when(inventoryRepoService.findAllEntries()).thenReturn(mockInventoryList);

        orderService.getOrderById("test");

        verify(orderMapperService, times(1)).mapOrderEntryToOrderData("test", mockOrderEntry, mockInventoryList);
    }

    @Test
    void getOrderById_ShouldThrowException_GivenNoOrderWasFound() throws OrderNotFoundException {
        Exception error = null;

        when(orderRepoService.findOrderById("test")).thenReturn(null);

        try {
            orderService.getOrderById("test");
        } catch (OrderNotFoundException ex) {
            error = ex;
        }

        verify(orderMapperService, times(0)).mapOrderEntryToOrderData(any(), any(), any());
        verify(inventoryRepoService, times(0)).findAllEntries();

        assertNotNull(error);
        assertTrue(error instanceof OrderNotFoundException);
    }

    @Test
    void saveOrder_ShouldReturnAnId_GivenSaveOperationSucceeded() {
        OrderEntry mock = mockOrderEntry();

        when(orderRepoService.saveOrder(any())).thenReturn(mock);
        when(inventoryRepoService.findAllEntries()).thenReturn(mockInventoryList());
        when(orderMapperService.mapOrderDataToOrderEntry(any())).thenReturn(mock);

        String result = orderService.saveOrder(new OrderData());

        assertEquals("test", result);
    }

    @Test
    void updateOrder_ShouldUpdateAnExistingOrderEntry_GivenOrderUpdateDataExists() throws OrderNotFoundException {
        OrderEntry mock = mockOrderEntry();
        OrderUpdateData mockUpdateData = new OrderUpdateData();

        when(orderRepoService.findOrderById("test")).thenReturn(mock);
        when(orderMapperService.updateOrderEntry(any(), any())).thenReturn(mock);
        when(orderRepoService.saveOrder(any())).thenReturn(mock);

        orderService.updateOrder("test", mockUpdateData);

        verify(orderMapperService, times(1)).updateOrderEntry(mock, mockUpdateData);
    }

    @Test
    void updateOrder_ShouldReturnAnId_GivenSaveOperationSucceeded() throws OrderNotFoundException {
        OrderEntry mock = mockOrderEntry();
        OrderUpdateData mockUpdateData = new OrderUpdateData();

        when(orderRepoService.findOrderById("test")).thenReturn(mock);
        when(orderMapperService.updateOrderEntry(any(), any())).thenReturn(mock);
        when(orderRepoService.saveOrder(any())).thenReturn(mock);

        String result = orderService.updateOrder("test", mockUpdateData);

        assertEquals("test", result);
    }

    @Test
    void updateOrder_ShouldThrowAnException_GivenNoOrderEntryExists() {
        OrderNotFoundException error = null;
        OrderUpdateData mockUpdateData = new OrderUpdateData();

        when(orderRepoService.findOrderById("test")).thenReturn(null);

        try {
            orderService.updateOrder("test", mockUpdateData);
        } catch (OrderNotFoundException ex) {
            error = ex;
        }

        assertEquals(new OrderNotFoundException("test").getMessage(), error.getMessage());
    }

    @Test
    void deleteById_ShouldThrowAnException_GivenNoOrderEntryExistsForTheId() {
        OrderNotFoundException error = null;

        try {
            doThrow(new EmptyResultDataAccessException(0)).when(orderRepoService).deleteOrderById("1");

            orderService.deleteOrder("1");
        }
        catch (OrderNotFoundException ex) {
            error = ex;
        }

        assertNotNull(error);
    }

    private OrderEntry mockOrderEntry() {
        OrderEntry mock = new OrderEntry();

        mock.setId("test");

        return mock;
    }

    private List<InventoryEntry> mockInventoryList() {
        List<InventoryEntry> mock = new ArrayList<>();

        mock.add(mockInventoryEntry());

        return mock;
    }

    private InventoryEntry mockInventoryEntry() {
        InventoryEntry mock = new InventoryEntry();

        mock.setBeanType(BeanType.ARABICA);

        return mock;
    }
}
