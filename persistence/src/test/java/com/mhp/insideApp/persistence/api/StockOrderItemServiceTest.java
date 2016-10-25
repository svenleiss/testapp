package com.mhp.insideApp.persistence.api;


import com.mhp.insideApp.persistence.common.BaseRepositoryTest;
import com.mhp.insideApp.insideApplications.Order;
import com.mhp.insideApp.insideApplications.sources.OrderService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class StockOrderItemServiceTest extends BaseRepositoryTest {

    private static final String MOCK_CONTRACT_VALUE = "DEU20570V";

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderService orderService;

    //Mechanic creates a new order item
    @Test
    public void testCreateNewOrderWithNewOrderItem() {

        Order order = orderService.create(
                MOCK_CONTRACT_VALUE,
                "mockLicense",
                Order.Status.NEW,
                "orderItemName",
                "orderItemDescription",
                "orderItemType",
                "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");

        //asserting on business object
        assertThat(order.getContract()).isEqualTo(MOCK_CONTRACT_VALUE);
        assertThat(order.getLicensePlate()).isEqualTo("mockLicense");
        assertThat(order.getStatus()).isEqualTo(Order.Status.NEW);
        assertThat(order.getOrderItems().size()).isEqualTo(1);
        assertThat(order.getOrderItems().get(0).getMedias().size()).isEqualTo(1);
        //asserting on business object
        OrderItem firstOrderItemResult = order.getOrderItems().get(0);
        assertThat(firstOrderItemResult.getItemName()).isEqualTo("orderItemName");
        assertThat(firstOrderItemResult.getDescription()).isEqualTo("orderItemDescription");
        assertThat(firstOrderItemResult.getItemType()).isEqualTo("orderItemType");
        assertThat(firstOrderItemResult.getStatus()).isEqualTo(OrderItem.Status.NEW);

    }

    //2 service advisor UPDATES price description and urgency
    @Test
    public void testOrderItemStatusOnServiceAdvisorUpdatesDetails() {
        Order order = orderService.create(
                MOCK_CONTRACT_VALUE,
                "mockLicense",
                Order.Status.NEW,
                "orderItemName",
                "orderItemDescription",
                "orderItemType",
                "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");

        // will create the initial one, with the status NEW

        OrderItem firstOrderItemResult = order.getOrderItems().get(0);

        //simulates the Service Advisor adding a price & description + urgency which
        // - changes status order item status to service advisor viewed (i.e. customer-ready)
        OrderItem updatedOrderItem = orderItemService.updateStatus(firstOrderItemResult.getId(),
                OrderItem.Status.VIEWED_BY_CUSTOMER);

        assertThat(updatedOrderItem.getStatus()).isEqualTo(OrderItem.Status.VIEWED_BY_CUSTOMER);

    }

    //3 service advisor SENDS order item
    //TODO
    @Test
    public void testServiceAdvisorSendOrderItemsToTheCustomer() {
        Order order = orderService.create(
                MOCK_CONTRACT_VALUE,
                "mockLicense",
                Order.Status.NEW,
                "orderItemName",
                "orderItemDescription",
                "orderItemType",
                "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");

        OrderItem firstOrderItemResult = order.getOrderItems().get(0);

        OrderItem orderItem = orderItemService.updateStatus(firstOrderItemResult.getId(),
                OrderItem.Status.VIEWED_BY_SERVICE_ADVISOR);

        assertThat(orderItem.getStatus()).isEqualTo(OrderItem.Status.VIEWED_BY_SERVICE_ADVISOR);

        OrderItem orderItemWithSentStatus = orderItemService.updateStatus(firstOrderItemResult.getId(),
                OrderItem.Status.SENT);

        assertThat(orderItemWithSentStatus.getStatus()).isEqualTo(OrderItem.Status.SENT);
    }


    //4. Customer views
    //5. Customer submits
    @Test
    public void testCustomerActionChangesOrderItemStatus() {
        Order order = orderService.create(
                MOCK_CONTRACT_VALUE,
                "mockLicense",
                Order.Status.NEW,
                "orderItemName",
                "orderItemDescription",
                "orderItemType",
                "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
        OrderItem firstOrderItemResult = order.getOrderItems().get(0);

        assertThat(firstOrderItemResult.getStatus()).isEqualTo(OrderItem.Status.NEW);
        OrderItem orderItem = orderItemService.updateCustomerAction(firstOrderItemResult.getId(),
                OrderItem.CustomerAction.CUSTOMER_ACCEPT);
        assertThat(orderItem.getStatus()).isEqualTo(OrderItem.Status.SUBMITTED_BY_CUSTOMER);
    }


}
