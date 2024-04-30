package br.com.sigvet.sigvetapi.common.validators;

import java.util.Objects;

import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {

    private DataSize dataSize;

    @Override
    public void initialize(FileSize constraintAnnotation) {
        final var max = constraintAnnotation.max();
        if (Objects.nonNull(max)) {
           this.dataSize = DataSize.parse(constraintAnnotation.max());
        } 
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        return dataSize == null || value.getSize() <= dataSize.toBytes();
    }
    
}
