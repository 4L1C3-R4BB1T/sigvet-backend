package br.com.sigvet.sigvetapi.common.converts;

import java.util.Objects;

import org.springframework.util.Assert;

import jakarta.persistence.AttributeConverter;

public class CEPConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(final String value) {
        Assert.notNull(value, "The cep value cannot be null or empty");
        return value.replaceAll("\\D", "").trim();
    }

    @Override
    public String convertToEntityAttribute(final String value) {
        if (Objects.isNull(value)) {
            return null;
        }
        return value.replaceAll("(\\d{5})(\\d{3})", "$1-$2");
    }
    
}