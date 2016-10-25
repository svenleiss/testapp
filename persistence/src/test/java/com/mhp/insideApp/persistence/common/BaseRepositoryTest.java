package com.mhp.insideApp.persistence.common;

import com.mhp.insideApp.persistence.common.repository.CustomerJpaRepository;
import com.mhp.insideApp.persistence.NullApplication;
import com.mhp.insideApp.persistence.insideApplications.repository.MessageJpaRepository;
import com.mhp.insideApp.persistence.support.factories.Factories;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = NullApplication.class)
@Transactional(TxType.NEVER)
public abstract class BaseRepositoryTest {

    @Autowired private CustomerJpaRepository customerJpaRepository;

    @Autowired private MessageJpaRepository messageJpaRepository;


    @Before
    public void setUp() throws Exception {
        Factories.setCustomerJpaRepository(customerJpaRepository);
        Factories.setOrderJpaRepository(messageJpaRepository);
    }
}
