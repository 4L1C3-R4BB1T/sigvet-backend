package br.com.sigvet.api.infrastructure.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sigvet.api.application.dto.BaseResponse;
import br.com.sigvet.api.application.dto.ClienteDTO;
import br.com.sigvet.api.application.dto.CriarClienteDTO;
import br.com.sigvet.api.application.exception.CidadeNaoExistenteException;
import br.com.sigvet.api.application.exception.ClienteNaoEncontradoException;
import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.application.mapper.ClienteDTOMapper;
import br.com.sigvet.api.application.mapper.ClienteMapper;
import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.application.model.PageModel;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.usecase.cliente.ICadastrarClienteUseCase;
import br.com.sigvet.api.usecase.cliente.IListarClientesUseCase;
import br.com.sigvet.api.usecase.cliente.IObterClientePorIdUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    private final IObterClientePorIdUseCase obterClientePorIdUseCase;


    @Operation(summary = "Operação de criar", description = "Descrição", responses = {
        @ApiResponse(description = "Retorna o objeto cliente criado", responseCode = "201", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class))
        }),
    })
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<ClienteDTO> create(@RequestBody @Valid CriarClienteDTO record) throws DomainInvalidException, CidadeNaoExistenteException, UsuarioExistenteException {
        var clienteToSave = clienteMapper.toCliente(record);
        var uriBuilder = UriComponentsBuilder.fromUriString("/{id}").buildAndExpand(new HashMap<String, Object>() {
            {
                put("id", clienteToSave.getId());
            }
        });
        return ResponseEntity.created(uriBuilder.toUri()).body(clienteDTOMapper.toClienteDTO(cadastrarClienteUseCase.executar(clienteToSave)));
    }

    @Operation(summary = "Operação de buscar", description = "Descrição", responses = {
        @ApiResponse(description = "Retorna uma lista de objetos clientes", responseCode = "200", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = PageModel.class))
        }),
    })
    @GetMapping
    public ResponseEntity<PageModel<ClienteDTO>> list(@RequestParam Map<String, String> parametros) throws DomainInvalidException {
        var filter = new FilterModel(parametros);
        var page = listarClientesUseCase.executar(filter);
        var clientesDTO = clienteDTOMapper.toClienteDTO(page.getContent());
        return ResponseEntity.ok(new PageModel<>(clientesDTO, page));
    }

    @Operation(summary = "Operação de buscar um cliente", description = "Descrição", responses = {
        @ApiResponse(description = "Retorna um objeto cliente", responseCode = "200", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponse.class))
        }),
    })
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ClienteDTO>> get(@PathVariable Long id) throws DomainInvalidException, ClienteNaoEncontradoException {
        var clienteDTO = clienteDTOMapper.toClienteDTO(obterClientePorIdUseCase.executar(id));
        var baseResponse = BaseResponse.<ClienteDTO>builder()
            .message("Cliente retornado") 
            .result(clienteDTO)
            .status(HttpStatus.OK.value())
            .success(true)
            .build();
        return ResponseEntity.ok(baseResponse);
    }
}
