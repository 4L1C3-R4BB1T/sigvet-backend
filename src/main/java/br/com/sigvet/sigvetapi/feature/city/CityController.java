package br.com.sigvet.sigvetapi.feature.city;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import br.com.sigvet.sigvetapi.common.entities.CityEntity;
import br.com.sigvet.sigvetapi.common.entities.StateEntity;
import br.com.sigvet.sigvetapi.common.repositories.CityRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Cidades", description = "Agrupa endpoints para gerenciar cidades")
@Slf4j
@RestController
@RequestMapping("/api/v1/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityRepository repository;

    @Operation(summary = "Obter objetos dessa entidade")
    @GetMapping
    public final ResponseEntity<MappingJacksonValue> get() {
        log.info("Entering the get method");
        final var result = repository.findAll();

        MappingJacksonValue jacksonValue = new MappingJacksonValue(result);
        SimpleFilterProvider filters = new SimpleFilterProvider();

        filters.addFilter(CityEntity.CITY_ENTITY_FILTER_KEY, SimpleBeanPropertyFilter.serializeAllExcept());
        filters.addFilter(StateEntity.STATE_ENTITY_FILTER_KEY, SimpleBeanPropertyFilter.serializeAllExcept());

        jacksonValue.setFilters(filters);

        log.info("Exiting the get method with result: {}", result);
        return ResponseEntity.ok(jacksonValue);
    }

}
