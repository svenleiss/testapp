package com.mhp.insideApp.common.util;

import java.time.Clock;
import java.time.Instant;

public final class TimeProvider {
    private TimeProvider() {

    }

    private static Clock clock = Clock.systemDefaultZone();

    public static Instant now() {
        return Instant.now(clock);
    }
}
