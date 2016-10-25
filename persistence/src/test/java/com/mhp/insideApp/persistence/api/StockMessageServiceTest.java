package com.mhp.insideApp.persistence.api;

import com.mhp.insideApp.persistence.common.BaseRepositoryTest;
import com.mhp.insideApp.persistence.insideApplications.entity.OrderEntity;
import com.mhp.insideApp.insideApplications.Order;
import com.mhp.insideApp.insideApplications.sources.OrderService;
import com.mhp.insideApp.persistence.insideApplications.repository.MessageJpaRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {StockMessageServiceTest.class})
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestRun.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterTestRun.sql")
})
public class StockMessageServiceTest extends BaseRepositoryTest {

    @Autowired
    private MessageJpaRepository messageJpaRepository;

    @Autowired
    private OrderService orderService;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void create() throws Exception {
        Order order = orderService.create(
                "DEU20570V",
                "mockLicense2",
                Order.Status.NEW,
                "orderItemName",
                "orderItemDescription",
                "orderItemType",
                "mediaUrl");

        //asserting on business object
        assertThat(order.getContract()).isEqualTo("DEU20570V");
        assertThat(order.getLicensePlate()).isEqualTo("mockLicense2");
        assertThat(order.getStatus()).isEqualTo(Order.Status.NEW);
        assertThat(order.getCustomerMessage().getMessage()).isEmpty();
        assertThat(order.getOrderItems().size()).isEqualTo(1);
        assertThat(order.getOrderItems().get(0).getMedias().size()).isEqualTo(1);


        //asserting on business object
        OrderItem firstOrderItemResult = order.getOrderItems().get(0);
        assertThat(firstOrderItemResult.getItemName()).isEqualTo("orderItemName");
        assertThat(firstOrderItemResult.getItemType()).isEqualTo("orderItemType");
        assertThat(firstOrderItemResult.getDescription()).isEqualTo("orderItemDescription");
        assertThat(firstOrderItemResult.getStatus()).isEqualTo(OrderItem.Status.NEW);
        assertThat(firstOrderItemResult.getCurrencyCode()).isEqualTo("EUR");

        //get persisted object from repo - with status completed
        OrderEntity result = messageJpaRepository.findOneOpenOrderByContractAndLicensePlate(
                order.getContract(),
                order.getLicensePlate());

        //.. other stuff
        assertThat(result.getContract()).isEqualTo("DEU20570V");
        assertThat(result.getLicensePlate()).isEqualTo("mockLicense2");
        assertThat(result.getStatus()).isEqualTo(Order.Status.NEW);

        assertThat(result.getOrderItems().size()).isEqualTo(1);
        assertThat(result.getOrderItems().get(0).getOrderEntity().getContract()).isEqualTo("DEU20570V");
        assertThat(result.getOrderItems().get(0).getOrderEntity().getLicensePlate()).isEqualTo("mockLicense2");
        assertThat(result.getOrderItems().get(0).getOrderEntity().getStatus()).isEqualTo(Order.Status.NEW);
        assertThat(result.getOrderItems().get(0).getMedias().size()).isEqualTo(1);

        OrderItemEntity firstOrderItemEntityResult = result.getOrderItems().get(0);
        assertThat(firstOrderItemEntityResult.getItemName()).isEqualTo("orderItemName");
        assertThat(firstOrderItemEntityResult.getItemType()).isEqualTo("orderItemType");
        assertThat(firstOrderItemEntityResult.getDescription()).isEqualTo("orderItemDescription");

    }

    @Test
    public void findByContract() throws Exception {

        List<Order> result = orderService.findByContract("contract-1");

        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0).getLicensePlate()).isEqualTo("mockLicenseFirst");
        assertThat(result.get(1).getLicensePlate()).isEqualTo("mockLicenseSecond");
        assertThat(result.get(2).getLicensePlate()).isEqualTo("mockLicenseThird");
    }

    @Test
    public void findOpenOrderByContractAndLicensePlate() throws Exception {

        Order order = orderService.findOpenOrderByContractAndLicensePlate("contract-2", "mockLicenseForth");

        List<OrderItem> orderItems = order.getOrderItems();
        assertThat(orderItems.size()).isEqualTo(2);
        assertThat(orderItems.get(0).getId()).isEqualTo(7);
        assertThat(orderItems.get(1).getId()).isEqualTo(6);
    }

    @Test
    public void findById() throws Exception {

        Order result = orderService.findById(2L);

        assertThat(result.getLicensePlate()).isEqualTo("mockLicense");
    }

}
