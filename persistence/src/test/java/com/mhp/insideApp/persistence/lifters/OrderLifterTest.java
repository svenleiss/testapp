package com.mhp.insideApp.persistence.lifters;

import com.mhp.insideApp.persistence.insideApplications.entity.OrderEntity;
import com.mhp.insideApp.insideApplications.Order;
import com.mhp.insideApp.persistence.support.factories.Factories;
import org.junit.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderLifterTest {

    @Test
    public void testLiftOrder() throws Exception {

        OrderEntity orderEntity = Factories.Order.construct()
                .withId(123L)
                .withContract("CONTRACT_TO_TEST")
                .withLicensePlate("TEST-1-9876")
                .withMediaUrl("http://test-it")
                .withStatus(Order.Status.NEW)
                .withPrice("12345")
                .withDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula "
                        + "eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, "
                        + "nascetur ridiculus mus. ")
                .withCurrencyCode("EUR")
                .withDateCreated(Instant.now())
                .build();

        Order order = OrderLifter.liftOrder(orderEntity);

        assertThat(order.getId()).isNotNull();
        assertThat(order.getId()).isEqualTo(123L);
        assertThat(order.getContract()).isNotNull();
        assertThat(order.getContract()).isEqualTo("CONTRACT_TO_TEST");
        assertThat(order.getLicensePlate()).isNotNull();
        assertThat(order.getLicensePlate()).isEqualTo("TEST-1-9876");
        assertThat(order.getOrderItems().get(0).getMedias().get(0).getMediaUrl()).isNotNull();
        assertThat(order.getOrderItems().get(0).getMedias().get(0).getMediaUrl()).isEqualTo("http://test-it");
        assertThat(order.getStatus()).isNotNull();
        assertThat(order.getStatus()).isEqualTo(Order.Status.NEW);
        assertThat(order.getOrderItems().get(0).getPrice()).isNotNull();
        assertThat(order.getOrderItems().get(0).getPrice()).isEqualTo("12345");
        assertThat(order.getOrderItems().get(0).getCurrencyCode()).isEqualTo("EUR");
        assertThat(order.getOrderItems().get(0).getDescription()).isNotNull();
        assertThat(order.getOrderItems().get(0).getDescription()).isEqualTo("Lorem ipsum dolor sit amet, consectetuer"
                + " adipiscing "
                + "elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis "
                + "parturient montes, nascetur ridiculus mus. ");
        assertThat(order.getCustomer().getFirstName()).isEmpty();
        assertThat(order.getCustomer().getLastName()).isEmpty();
        assertThat(order.getCustomer().getEmail()).isEmpty();
        assertThat(order.getCustomer().getTelephone()).isEmpty();
        assertThat(order.getCustomerMessage().getMessage()).isEmpty();
        assertThat(order.getStartDate()).isEqualTo(OrderLifter.getFormatter(Instant.now()));
    }
}
