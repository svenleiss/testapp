package com.mhp.insideApp.persistence.api;

import com.mhp.insideApp.persistence.insideApplications.entity.GreetingEntity;
import com.mhp.insideApp.persistence.insideApplications.repository.GreetingJpaRepository;
import com.mhp.insideApp.persistence.lifters.GreetingLifter;
import com.mhp.insideApp.insideApplications.Greetings;
import com.mhp.insideApp.insideApplications.sources.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockGreetingService implements GreetingService {

    @Autowired
    private GreetingJpaRepository greetingJpaRepository;

    @Override
    public Greetings create(Greetings greetings) {
        GreetingEntity greetingEntity = new GreetingEntity()
                .withUserName(greetings.getUserName())
                .withMessage(greetings.getMessage());

        greetingJpaRepository.save(greetingEntity);

        return liftGreeting(GreetingJpaRepository.findOne(id));
    }

}
