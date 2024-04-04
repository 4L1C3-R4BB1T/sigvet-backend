package br.com.sigvet.api.infrastructure.convert;

import jakarta.persistence.AttributeConverter;

public class CPFAttributeConvert implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
       return attribute.replaceAll("\\D", "");
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }
    
}
