package com.mhp.insideApp.persistence.api;

import com.mhp.insideApp.common.Customer;
import com.mhp.insideApp.common.sources.CustomerService;
import com.mhp.insideApp.persistence.common.BaseRepositoryTest;
import com.mhp.insideApp.persistence.common.entity.CustomerEntity;
import com.mhp.insideApp.persistence.common.repository.CustomerJpaRepository;
import com.mhp.insideApp.persistence.support.factories.Factories;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class StockCustomerServiceTest extends BaseRepositoryTest {

    @Autowired
    private CustomerJpaRepository customerJpaRepository;

    @Autowired
    private CustomerService customerService;

    @Test
    public void create() throws Exception {
        Customer customer = customerService.create(
                "firstName",
                "lastName",
                "rahulsingh336@gmail.com",
                "1234567890"
        );

        assertThat(customer.getId()).isNotNull();
        assertThat(customer.getFirstName()).isEqualTo("firstName");
        assertThat(customer.getLastName()).isEqualTo("lastName");
        assertThat(customer.getEmail()).isEqualTo("rahulsingh336@gmail.com");
        assertThat(customer.getTelephone()).isEqualTo("1234567890");

        assertThat(customerJpaRepository.findOne(customer.getId())).isNotNull();
    }


    @Test
    public void updateCustomer() throws Exception {
        CustomerEntity existingCustomer = Factories.Customer.construct()
                .withFirstName("first-name")
                .withLastName("last-name")
                .create();

        Long customerId = existingCustomer.getId();

        Customer customer = new Customer()
                .withId(customerId)
                .withFirstName("new-first-name")
                .withLastName("new-last-name")
                .withEmail("rahulsingh336@gmail.com")
                .withTelephone("0123456789");

        Customer updatedCustomer = customerService.update(customer);

        assertThat(updatedCustomer.getFirstName()).isEqualTo("new-first-name");
        assertThat(updatedCustomer.getLastName()).isEqualTo("new-last-name");
        assertThat(updatedCustomer.getEmail()).isEqualTo("rahulsingh336@gmail.com");
        assertThat(updatedCustomer.getTelephone()).isEqualTo("0123456789");

        CustomerEntity refreshedCustomer = customerJpaRepository.findOne(customerId);

        assertThat(refreshedCustomer.getFirstName()).isEqualTo("new-first-name");
        assertThat(refreshedCustomer.getLastName()).isEqualTo("new-last-name");
        assertThat(refreshedCustomer.getEmail()).isEqualTo("rahulsingh336@gmail.com");
        assertThat(refreshedCustomer.getTelephone()).isEqualTo("0123456789");


    }

}
