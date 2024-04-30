package br.com.sigvet.sigvetapi.feature.profile;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import br.com.sigvet.sigvetapi.common.validators.FileContentType;
import br.com.sigvet.sigvetapi.common.validators.FileSize;
import jakarta.validation.constraints.NotNull;

public record ProfileRequestDTO(

    @NotNull(message = "The Upload cannot be null")
    @FileContentType(allowed = { MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_PNG_VALUE } )
    @FileSize(max = "5MB")
    MultipartFile upload
) {
    
}
