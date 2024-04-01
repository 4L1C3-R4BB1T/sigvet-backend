package br.com.sigvet.api.infrastructure.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigvet.api.application.dto.ClienteDTO;
import br.com.sigvet.api.application.dto.CriarClienteDTO;
import br.com.sigvet.api.application.exception.CidadeNaoExistenteException;
import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.application.mapper.ClienteDTOMapper;
import br.com.sigvet.api.application.mapper.ClienteMapper;
import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.application.model.PageModel;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.usecase.cliente.ICadastrarClienteUseCase;
import br.com.sigvet.api.usecase.cliente.IListarClientesUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Clientes")
@RestController
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor
@Validated
public class ClienteController {
    
    private final IListarClientesUseCase listarClientesUseCase;

    private final ClienteDTOMapper clienteDTOMapper;

    private final ClienteMapper clienteMapper;

    private final ICadastrarClienteUseCase cadastrarClienteUseCase;

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@RequestBody @Valid CriarClienteDTO record) throws DomainInvalidException, CidadeNaoExistenteException, UsuarioExistenteException {
        var clienteToSave = clienteMapper.toCliente(record);
        return ResponseEntity.ok(clienteDTOMapper.toClienteDTO(cadastrarClienteUseCase.executar(clienteToSave)));
    }

    @GetMapping
    public ResponseEntity<PageModel<ClienteDTO>> list(@RequestParam Map<String, String> parametros) throws DomainInvalidException {
        var filter = new FilterModel(parametros);
        var page = listarClientesUseCase.executar(filter);
        var clientesDTO = clienteDTOMapper.toClienteDTO(page.getContent());
        return ResponseEntity.ok(new PageModel<>(clientesDTO, page));
    }
}
