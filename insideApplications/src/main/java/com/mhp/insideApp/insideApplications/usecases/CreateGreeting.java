package com.mhp.insideApp.insideApplications.usecases;

import com.mhp.insideApp.insideApplications.Greetings;
import com.mhp.insideApp.insideApplications.sources.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateGreeting {

    @Autowired
    private GreetingService greetingService;

    public Greetings run(Greetings greetings) throws Exception{
        return greetingService.create(greetings.getUserName(), greetings.getMessage());
    }
}
