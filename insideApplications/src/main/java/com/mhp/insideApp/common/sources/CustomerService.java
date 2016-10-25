package com.mhp.insideApp.common.sources;

import com.mhp.insideApp.common.Customer;

public interface CustomerService {

    Customer create(
            String firstName,
            String lastName,
            String email,
            String telephone
    );

    Customer update(
            Customer customer
    );

}
