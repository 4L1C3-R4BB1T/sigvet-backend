package br.com.sigvet.api.infrastructure.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.application.model.PageModel;
import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.usecase.cliente.IListarClientesUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Clientes")
@RestController
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {
    
    private final IListarClientesUseCase listarClientesUseCase;

    @GetMapping
    public ResponseEntity<PageModel<Cliente>> list(@RequestParam Map<String, String> parametros) throws DomainInvalidException {
        return ResponseEntity.ok(listarClientesUseCase.executar(new FilterModel(parametros)));
    }
}
