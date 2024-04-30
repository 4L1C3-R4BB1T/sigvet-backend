package br.com.sigvet.sigvetapi.common.validators;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileContetTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {

    private List<String> allowed;

    @Override
    public void initialize(FileContentType constraintAnnotation) {
        allowed = List.of(constraintAnnotation.allowed());
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
       return allowed.isEmpty() || allowed.stream().anyMatch(contentType -> contentType.equalsIgnoreCase(value.getContentType()));
    }
    
}
