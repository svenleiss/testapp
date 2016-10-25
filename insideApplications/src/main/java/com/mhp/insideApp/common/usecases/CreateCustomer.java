package com.mhp.insideApp.common.usecases;

import com.mhp.insideApp.common.Customer;
import com.mhp.insideApp.common.sources.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateCustomer {

    @Autowired
    private CustomerService customerService;

    @Transactional
    public Customer run(Customer customer) {
        Customer newCustomer = customerService.create(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getTelephone()
        );

        return newCustomer;
    }

}
