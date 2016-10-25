package com.mhp.insideApp.persistence.support.factories;

import com.mhp.insideApp.persistence.common.entity.CustomerEntity;
import com.mhp.insideApp.persistence.common.repository.CustomerJpaRepository;
import com.mhp.insideApp.persistence.insideApplications.entity.OrderEntity;
import com.mhp.insideApp.persistence.insideApplications.repository.MessageJpaRepository;
import lombok.Setter;

import java.time.Instant;


public class Factories {

    @Setter
    private static CustomerJpaRepository customerJpaRepository;
    @Setter
    private static MessageJpaRepository messageJpaRepository;

    public static final class Customer {
        public static class CustomerEntityBuilder {
            private CustomerEntity customer;

            CustomerEntityBuilder() {
                customer = new CustomerEntity()
                        .withFirstName("first-name")
                        .withLastName("last-name")
                        .withEmail("rahulsingh336@gmail.com")
                        .withTelephone("1234567890");
            }

            public CustomerEntity build() {
                return customer;
            }

            public CustomerEntity create() {
                return customerJpaRepository.save(customer);
            }

            public Customer.CustomerEntityBuilder withFirstName(String firstName) {
                this.customer.setLastName(firstName);
                return this;
            }

            public Customer.CustomerEntityBuilder withLastName(String lastName) {
                this.customer.setLastName(lastName);
                return this;
            }

            public Customer.CustomerEntityBuilder withEmail(String email) {
                this.customer.setLastName(email);
                return this;
            }

            public Customer.CustomerEntityBuilder withTelephone(String telephone) {
                this.customer.setLastName(telephone);
                return this;
            }
        }

        public static CustomerEntityBuilder construct() {
            return new CustomerEntityBuilder();
        }

    }

    public static class Order {
        public static class OrderEntityBuilder {
            private OrderEntity order;
            private OrderItemEntity orderItemEntity;
            private MediaEntity mediaEntity;

            OrderEntityBuilder() {
                order = new OrderEntity()
                        .withContract("contract")
                        .withLicensePlate("license-plate")
                        .withStatus(com.mhp.insideApp.insideApplications.Order.Status.NEW)
                        .withId(123L);
                mediaEntity = new MediaEntity();
                orderItemEntity = new OrderItemEntity();
                orderItemEntity.getMedias().add(mediaEntity);
                order.getOrderItems().add(orderItemEntity);
                order.setCustomer(new CustomerEntity());
                order.setCustomerMessage(new CustomerMessageEntity());
            }

            public OrderEntity build() {
                return this.order;
            }

            public OrderEntity create() {
                return messageJpaRepository.save(this.order);
            }

            public Order.OrderEntityBuilder withId(Long id) {
                this.order.setId(id);
                return this;
            }

            public Order.OrderEntityBuilder withContract(String contract) {
                this.order.setContract(contract);
                return this;
            }

            public Order.OrderEntityBuilder withLicensePlate(String licensePlate) {
                this.order.setLicensePlate(licensePlate);
                return this;
            }

            public Order.OrderEntityBuilder withMediaUrl(String mediaUrl) {
                this.order.getOrderItems().get(0).getMedias().get(0).setMediaUrl(mediaUrl);
                return this;
            }

            public Order.OrderEntityBuilder withStatus(
                    com.mhp.insideApp.insideApplications.Order.Status status) {
                this.order.setStatus(status);
                return this;
            }

            public Order.OrderEntityBuilder withPrice(String price) {
                this.order.getOrderItems().get(0).setPrice(price);
                return this;
            }

            public Order.OrderEntityBuilder withDescription(String description) {
                this.order.getOrderItems().get(0).setDescription(description);
                return this;
            }

            public Order.OrderEntityBuilder withCurrencyCode(String currency) {
                this.order.getOrderItems().get(0).setCurrencyCode(currency);
                return this;
            }

            public Order.OrderEntityBuilder withDateCreated(Instant date) {
                this.order.setStartDate(date);
                return this;
            }
        }

        public static OrderEntityBuilder construct() {
            return new Order.OrderEntityBuilder();
        }

    }
}
