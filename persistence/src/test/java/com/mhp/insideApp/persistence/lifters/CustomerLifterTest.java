package com.mhp.insideApp.persistence.lifters;

import com.mhp.insideApp.persistence.common.entity.CustomerEntity;
import com.mhp.insideApp.persistence.support.factories.Factories;
import org.junit.Test;

public class CustomerLifterTest {

    @Test
    public void testLiftCustomer() throws Exception {
        CustomerEntity customerEntity = Factories.Customer.construct()
                .withFirstName("")
                .withLastName("")
                .withEmail("")
                .withTelephone("")
                .build();

    }
}
