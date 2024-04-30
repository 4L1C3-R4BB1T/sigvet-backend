package br.com.sigvet.sigvetapi.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import br.com.sigvet.sigvetapi.common.entities.BaseEntity;
import br.com.sigvet.sigvetapi.common.models.FilterModel;
import br.com.sigvet.sigvetapi.common.models.ResponseResultModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CrudController<E extends BaseEntity<Long>, M> {

    protected final CrudFacade<E> facade;
    protected final EntityMapper<M, E> mapper;

    protected final Map<String, List<String>> attributeFilters = new HashMap<>();
    
    @Operation(summary = "Obter objetos")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(example = "{\"currentPage\":1,\"totalPages\":5,\"totalElements\":100,\"pageSize\":20,\"elements\": [] }"))),
        @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(schema = @Schema())),
    })
    @GetMapping
    public final ResponseEntity<MappingJacksonValue> get(
        @Parameter(
            description = "Parâmetros para filtrar dados",
            allowEmptyValue = true,
            example = "{\"size\":100,\"page\":0,\"sort\":\"-field1,field2\",\"equal_filters\":\"field:=Value\",\"in_filters\":\"field1=Value1,Value2;field2=Value1,Value2\"}"        )
        @RequestParam final Map<String, String> parameters
    ) {
        final var filterModel = new FilterModel(parameters);
        log.info("Entering the get method with filter model: {}", filterModel);
        final var result = facade.findAll(filterModel);
        log.info("Exiting the get method with result: {}", result);
        return ResponseEntity.ok(buildJacksonValue(result));
    }

    @Operation(summary = "Obter um objeto")
    @ApiResponses({
        @ApiResponse(content = @Content(
            schema = @Schema(example = "{\"title\":\"Objeto\",\"statusCode\":200,\"success\":true,\"result\": {} }")
        )),
        @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content()),
    })
    @GetMapping("/{id}")
    public final ResponseEntity<MappingJacksonValue> get(@PathVariable("id") final Long id) {
        log.info("Entering the get method with id: {}", id);
        var client = facade.findById(id);
        var responseResultModel = ResponseResultModel.<E>builder()
            .title("Objeto")
            .success(true)
            .statusCode(HttpStatus.OK.value())
            .result(client)
            .build();
        log.info("Exiting the get method with client: {}", client);
        return ResponseEntity.ok(buildJacksonValue(responseResultModel));
    }

    @Operation(summary = "Criar um objeto")
    @ApiResponses({
        @ApiResponse(responseCode = "201",  content = @Content(
            schema = @Schema(example = "{\"title\":\"Objeto\",\"statusCode\":201,\"success\":true,\"result\": {} }")
        )),
        @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content())
    })
    @PostMapping
    public final ResponseEntity<MappingJacksonValue> post(@RequestBody @Valid final M record) {
        log.info("Entering post method with record: {}", record);
        final var entitySaved = facade.create(mapper.fromModel(record));
        var responseResultModel = ResponseResultModel.<E>builder()
        .title("Objeto")
        .success(true)
        .statusCode(HttpStatus.OK.value())
        .result(entitySaved)
        .build();
        final Long id = entitySaved.getId();
        final var uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();
        log.info("New entity saved: {}", entitySaved);
        log.info("Exiting post method with URI: {}", uri); 
        return ResponseEntity.created(uri).body(buildJacksonValue(responseResultModel));
    }
    
    @Operation(summary = "Atualizar um objeto")
    @ApiResponse(content = @Content())
    @PutMapping("/{id}")
    public final ResponseEntity<Void> put(@PathVariable("id") final Long id, @RequestBody @Valid final M record) {
        log.info("Entering the put method with id: {} and record: {}", id, record);
        facade.update(id, mapper.fromModel(record));
        log.info("Exiting the put method");
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deletar um objeto")
    @ApiResponse(content = @Content())
    @DeleteMapping("/{id}")
    public final ResponseEntity<Void> delete(@PathVariable("id") final Long id) {
        log.info("Entering the delete method with id: {}", id);
        facade.delete(id);
        log.info("Exiting the delete method");
        return ResponseEntity.noContent().build();
    }

    private <T> MappingJacksonValue buildJacksonValue(final T obj) {
        final var simpleFilterProvider = new SimpleFilterProvider().setFailOnUnknownId(false);

        for (Map.Entry<String, List<String>> set : attributeFilters.entrySet()) {
            simpleFilterProvider.addFilter(
                    set.getKey(),
                    SimpleBeanPropertyFilter.serializeAllExcept(set.getValue().toArray(new String[] {})));
        }

        final var mappingJacksonValue = new MappingJacksonValue(obj);
        mappingJacksonValue.setFilters(simpleFilterProvider);

        return mappingJacksonValue;
    }
    
}
