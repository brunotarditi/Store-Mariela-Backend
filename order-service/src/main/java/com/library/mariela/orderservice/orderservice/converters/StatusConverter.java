package com.library.mariela.orderservice.orderservice.converters;

import com.library.mariela.orderservice.orderservice.enums.Status;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Status status) {
        if (status == null)
            return null;
        return status.getStatus();
    }

    @Override
    public Status convertToEntityAttribute(Integer status) {
        if (status == null)
            return null;
        return Stream.of(Status.values())
                .filter(s -> s.getStatus().equals(status))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
