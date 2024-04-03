package br.com.sigvet.api.infrastructure.convert;

import jakarta.persistence.AttributeConverter;

public class CEPAttributeConvert implements AttributeConverter<String, String>{

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return attribute.replaceAll("\\d*", "");
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData.replaceAll("(\\d{5})(\\d{3})", "$1-$2");
    }
    
}
