package com.mhp.insideApp.persistence.converters;

import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class InstantAttributeConverterTest {

    private InstantAttributeConverter subject;

    @Before
    public void setUp() throws Exception {
        subject = new InstantAttributeConverter();
    }

    @Test
    public void testConvertFromDatabaseTime() throws Exception {
        Instant now = Instant.now();
        Timestamp result = subject.convertToDatabaseColumn(now);

        assertThat(result).isEqualTo(Timestamp.from(now));
    }

    @Test
    public void testConvertToDatabaseTime() throws Exception {


    }
}
