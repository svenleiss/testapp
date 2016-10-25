package com.mhp.insideApp.persistence.lifters;

import com.mhp.insideApp.insideApplications.Greetings;
import com.mhp.insideApp.persistence.insideApplications.entity.GreetingEntity;

public final class GreetingLifter {

    private GreetingLifter() {

    }

    public static Greetings liftGreeting(GreetingEntity greetingEntity) {
        return new Greetings()
                .withId(greetingEntity.getId())
                .withUserName(messageEntity.getUserName())
                .withMessage(messageEntity.getMessage());
    }
}
