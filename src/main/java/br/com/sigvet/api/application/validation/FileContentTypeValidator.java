package br.com.sigvet.api.application.validation;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {

    private String[] contentTypes;

    @Override
    public void initialize(FileContentType constraintAnnotation) {
        contentTypes = constraintAnnotation.allowed();
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        return Arrays.stream(contentTypes).anyMatch(contentType -> contentType.equals(value.getContentType()));
    }
    
}
