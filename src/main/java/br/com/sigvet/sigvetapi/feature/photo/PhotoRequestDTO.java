package br.com.sigvet.sigvetapi.feature.photo;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import br.com.sigvet.sigvetapi.common.validators.FileContentType;
import br.com.sigvet.sigvetapi.common.validators.FileSize;
import jakarta.validation.constraints.NotNull;

public record PhotoRequestDTO(
    @NotNull(message = "The Upload cannot be null")
    @FileContentType(allowed = { MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE } )
    @FileSize(max = "5MB")
    MultipartFile content
) {}
