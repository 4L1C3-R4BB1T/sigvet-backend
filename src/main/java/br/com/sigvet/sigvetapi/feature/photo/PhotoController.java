package br.com.sigvet.sigvetapi.feature.photo;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sigvet.sigvetapi.common.entities.enums.EntityType;
import br.com.sigvet.sigvetapi.feature.photo.usecases.FindPhotoUseCase;
import br.com.sigvet.sigvetapi.feature.photo.usecases.SetPhotoUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Photo")
@RestController
@RequestMapping("/photo")
@RequiredArgsConstructor
public class PhotoController {

    private final SetPhotoUseCase setPhotoUseCase;

    private final FindPhotoUseCase findPhotoUseCase;
    
    @PutMapping(value = "/client/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<PhotoResponseDTO> putForClient(@PathVariable("id") Long id, @Valid PhotoRequestDTO photoRequestDTO) {
        final var photoResponse = setPhotoUseCase.execute(id, photoRequestDTO.content(), EntityType.CLIENT);
        final var url = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(id);
        photoResponse.setLink(url.toUriString());
        return ResponseEntity.ok(photoResponse);
    }

    @GetMapping(value = "/client/{id}", produces = { MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE })
    public ResponseEntity<InputStreamResource> getForClient(@PathVariable("id") Long id) {
        return ResponseEntity.ok(findPhotoUseCase.execute(id, EntityType.CLIENT));
    }

    @PutMapping(value = "/vaccine/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<PhotoResponseDTO> putForVaccine(@PathVariable("id") Long id, @Valid PhotoRequestDTO photoRequestDTO) {
        final var photoResponse = setPhotoUseCase.execute(id, photoRequestDTO.content(), EntityType.VACCINE);
        final var url = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(id);
        photoResponse.setLink(url.toUriString());
        return ResponseEntity.ok(photoResponse);
    }

    @GetMapping(value = "/vaccine/{id}", produces = { MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE })
    public ResponseEntity<InputStreamResource> getForVaccine(@PathVariable("id") Long id) {
        return ResponseEntity.ok(findPhotoUseCase.execute(id, EntityType.VACCINE));
    }

}
