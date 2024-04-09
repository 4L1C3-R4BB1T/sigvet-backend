package br.com.sigvet.api.application.dto;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import br.com.sigvet.api.application.validation.FileContentType;
import br.com.sigvet.api.application.validation.FileSize;
import jakarta.validation.constraints.NotNull;

public record UploadPhotoRequestDTO(
    @NotNull
    @FileSize(max = "5MB")
    @FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
    MultipartFile file
) {
    
}
