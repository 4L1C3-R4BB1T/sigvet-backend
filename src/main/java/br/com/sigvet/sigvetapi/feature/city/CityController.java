package br.com.sigvet.sigvetapi.feature.city;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigvet.sigvetapi.common.repositories.CityRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Cidades", description = "Agrupa endpoints para gerenciar cidades")
@Slf4j
@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityRepository repository;

    @GetMapping
    public final ResponseEntity<?> get() {
        log.info("Entering the get method");
        final var result = repository.findAll();
        log.info("Exiting the get method with result: {}", result);
        return ResponseEntity.ok(result);
    }

}
