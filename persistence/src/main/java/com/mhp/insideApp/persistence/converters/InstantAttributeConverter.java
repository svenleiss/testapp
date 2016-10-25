package com.mhp.insideApp.persistence.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.Instant;

@Converter(autoApply = true)
public class InstantAttributeConverter implements AttributeConverter<Instant, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(Instant instant) {
        if (instant == null) {
            return null;
        } else {
            return new Timestamp(instant.toEpochMilli());
        }
    }

    @Override
    public Instant convertToEntityAttribute(Timestamp sqlTimestamp) {
        if (sqlTimestamp == null) {
            return null;
        } else {
            return sqlTimestamp.toInstant();
        }
    }
}
