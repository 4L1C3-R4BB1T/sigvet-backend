package br.com.sigvet.sigvetapi.feature.photo;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import br.com.sigvet.sigvetapi.common.validators.FileContentType;
import br.com.sigvet.sigvetapi.common.validators.FileSize;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;

@Tag(name = "Foto")
public record PhotoRequestDTO(
    @NotNull(message = "The Upload cannot be null")
    @FileContentType(allowed = { MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE }, message = "Only PNG and JPEG is allowed.")
    @FileSize(max = "5MB")
    MultipartFile photo
) {}
