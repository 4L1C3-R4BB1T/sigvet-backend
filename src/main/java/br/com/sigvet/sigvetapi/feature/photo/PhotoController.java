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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Foto", description = "Agrupa endpoints para gerenciar upload de fotos")
@RestController
@RequestMapping("/api/v1/photo")
@RequiredArgsConstructor
public class PhotoController {

    private final SetPhotoUseCase setPhotoUseCase;

    private final FindPhotoUseCase findPhotoUseCase;

    @Operation(summary = "Upload de foto para usuário")
    @PutMapping(value = "/user/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PhotoResponseDTO> putForClient(@PathVariable("id") Long id, 
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
            @Valid PhotoRequestDTO photoRequestDTO) {
        final var photoResponse = setPhotoUseCase.execute(id, photoRequestDTO.photo(), EntityType.USER);
        final var url = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(id);
        photoResponse.setLink(url.toUriString());
        return ResponseEntity.ok(photoResponse);
    } 

    @Operation(summary = "Obeter foto de usuário")
    @GetMapping(value = "/user/{id}", produces = { MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE })
    public ResponseEntity<InputStreamResource> getForClient(@PathVariable("id") Long id) {
        return ResponseEntity.ok(findPhotoUseCase.execute(id, EntityType.USER));
    }

    @Operation(summary = "Upload de foto para vacina")
    @PutMapping(value = "/vaccine/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<PhotoResponseDTO> putForVaccine(@PathVariable("id") Long id, 
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
            @Valid PhotoRequestDTO photoRequestDTO) {
        final var photoResponse = setPhotoUseCase.execute(id, photoRequestDTO.photo(), EntityType.VACCINE);
        final var url = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(id);
        photoResponse.setLink(url.toUriString());
        return ResponseEntity.ok(photoResponse);
    } 

    @Operation(summary = "Obter foto de vacina")
    @GetMapping(value = "/vaccine/{id}", produces = { MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE })
    public ResponseEntity<InputStreamResource> getForVaccine(@PathVariable("id") Long id) {
        return ResponseEntity.ok(findPhotoUseCase.execute(id, EntityType.VACCINE));
    } 

    @Operation(summary = "Upload de foto para animal")
    @PutMapping(value = "/animal/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<PhotoResponseDTO> putForAnimal(@PathVariable("id") Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)) @Valid PhotoRequestDTO photoRequestDTO) {
        final var photoResponse = setPhotoUseCase.execute(id, photoRequestDTO.photo(), EntityType.ANIMAL);
        final var url = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(id);
        photoResponse.setLink(url.toUriString());
        return ResponseEntity.ok(photoResponse);
    }

    @Operation(summary = "Obter foto de animal")
    @GetMapping(value = "/animal/{id}", produces = { MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE })
    public ResponseEntity<InputStreamResource> getForAnimal(@PathVariable("id") Long id) {
        System.out.println("OII");
        return ResponseEntity.ok(findPhotoUseCase.execute(id, EntityType.ANIMAL));
    }

}
