package br.com.sigvet.sigvetapi.common.utils;

import org.springframework.util.Assert;

public class StringNormalizer {
    
    public static String normalizeString(final String value) {
        Assert.notNull(value, "The value cannot be null");
        return value.trim().toLowerCase();
    }

    public static String removeNonNumericCharacteres(final String value) {
        Assert.notNull(value, "The value cannot be null");
        return value.replaceAll("\\D", "");
    }
    
}
