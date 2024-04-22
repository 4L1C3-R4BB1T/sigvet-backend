package br.com.sigvet.sigvetapi.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import br.com.sigvet.sigvetapi.common.entities.BaseEntity;
import br.com.sigvet.sigvetapi.common.models.FilterModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CrudController<E extends BaseEntity<Long>, M> {

    protected final CrudFacade<E> facade;
    protected final EntityMapper<M, E> mapper;

    protected final Map<String, List<String>> attributeFilters = new HashMap<>();

    @GetMapping
    public final ResponseEntity<MappingJacksonValue> get(final FilterModel filterModel) {
        log.info("Entering the get method with filter model: {}", filterModel);
        final var result = facade.findAll(filterModel);
        log.info("Exiting the get method with result: {}", result);
        return ResponseEntity.ok(buildJacksonValue(result));
    }

    @GetMapping("/{id}")
    public final ResponseEntity<MappingJacksonValue> get(@PathVariable("id") final Long id) {
        log.info("Entering the get method with id: {}", id);
        var client = facade.findById(id);
        log.info("Exiting the get method with client: {}", client);
        return ResponseEntity.ok(buildJacksonValue(client));
    }

    @PostMapping
    public final ResponseEntity<MappingJacksonValue> post(@RequestBody @Valid final M record) {
        log.info("Entering post method with record: {}", record);
        final var entitySaved = facade.create(mapper.fromModel(record));
        final Long id = entitySaved.getId();
        final var uri = ServletUriComponentsBuilder.fromPath("/{id}").buildAndExpand(id).toUri();
        log.info("New entity saved: {}", entitySaved);
        log.info("Exiting post method with URI: {}", uri);
        return ResponseEntity.created(uri).body(buildJacksonValue(entitySaved));
    }
    
    @PutMapping("/{id}")
    public final ResponseEntity<Void> put(@PathVariable("id") final Long id, @RequestBody @Valid final M record) {
        log.info("Entering the put method with id: {} and record: {}", id, record);
        facade.update(id, mapper.fromModel(record));
        log.info("Exiting the put method");
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public final ResponseEntity<Void> delete(@PathVariable("id") final Long id) {
        log.info("Entering the delete method with id: {}", id);
        facade.delete(id);
        log.info("Exiting the delete method");
        return ResponseEntity.noContent().build();
    }

    private <T> MappingJacksonValue buildJacksonValue(final T obj) {
        final var simpleFilterProvider = new SimpleFilterProvider();

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
