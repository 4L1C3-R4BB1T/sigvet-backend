package br.com.sigvet.api.infrastructure.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sigvet.api.application.dto.AtualizarClienteDTO;
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
import br.com.sigvet.api.usecase.cliente.IAtualizarClienteUseCase;
import br.com.sigvet.api.usecase.cliente.ICadastrarClienteUseCase;
import br.com.sigvet.api.usecase.cliente.IDeletarClienteUseCase;
import br.com.sigvet.api.usecase.cliente.IListarClientesUseCase;
import br.com.sigvet.api.usecase.cliente.IObterClientePorIdUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Clientes")
@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@Validated
public class ClienteController {

    private final IListarClientesUseCase listarClientesUseCase;
    private final ClienteDTOMapper clienteDTOMapper;
    private final ClienteMapper clienteMapper;
    private final ICadastrarClienteUseCase cadastrarClienteUseCase;
    private final IObterClientePorIdUseCase obterClientePorIdUseCase;
    private final IAtualizarClienteUseCase atualizarClienteUseCase;
    private final IDeletarClienteUseCase deletarClienteUseCase;

    @Operation(summary = "Operação de criar um novo cliente", responses = {
            @ApiResponse(description = "Retorna o objeto cliente criado", responseCode = "201", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class))
            }),
    })
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<BaseResponse<ClienteDTO>> create(@RequestBody @Valid CriarClienteDTO record) throws DomainInvalidException, CidadeNaoExistenteException, UsuarioExistenteException {
        var clienteToSave = clienteMapper.toCliente(record);
        var uriBuilder = UriComponentsBuilder.fromUriString("/{id}").buildAndExpand(new HashMap<String, Object>() {
            {
                put("id", clienteToSave.getId());
            }
        });

        var clienteDTO = clienteDTOMapper.toClienteDTO(cadastrarClienteUseCase.executar(clienteToSave));

        var baseResponse = new BaseResponse<ClienteDTO>(
                true,
                HttpStatus.CREATED.value(),
                "Cliente retornado",
                clienteDTO,
                null);

        return ResponseEntity.created(uriBuilder.toUri())
                .body(baseResponse);
    }

    @Operation(summary = "Operação de atualizar um cliente", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = AtualizarClienteDTO.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<ClienteDTO>> put(@PathVariable Long id, @RequestBody AtualizarClienteDTO record) throws ClienteNaoEncontradoException, UsuarioExistenteException, DomainInvalidException {
        ClienteDTO clienteDTO = clienteDTOMapper
                .toClienteDTO(atualizarClienteUseCase.executar(id, clienteMapper.toCliente(record)));

        var baseResponse = new BaseResponse<ClienteDTO>(
                true,
                HttpStatus.OK.value(),
                "Cliente retornado",
                clienteDTO,
                null);

        return ResponseEntity.ok(baseResponse);
    }

    @Operation(summary = "Operação de deletar um cliente", responses = {
        @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Boolean>> delete(@PathVariable Long id) throws UsuarioExistenteException, DomainInvalidException, ClienteNaoEncontradoException {
        var result = deletarClienteUseCase.executar(id);
        var baseResponse = new BaseResponse<>(true, HttpStatus.OK.value(), "Operação de deletar cliente", result, null);
        return ResponseEntity.ok(baseResponse);
    }

    @Operation(summary = "Operação de buscar todos os clientes", description = "Descrição", responses = {
            @ApiResponse(description = "Retorna uma lista de objetos clientes", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PageModel.class))
            }),
    })
    @GetMapping
    public ResponseEntity<PageModel<ClienteDTO>> list(@Parameter(description = "Filters for search", example = "{\"equal_filters\": \"nome:=Gabriel;cpf:!=17364509720\", \"page\": 1, \"limit\": 10, \"sort\": \"-nome\", \"in_filters\": \"id:1,2,3,4;~nome:José,Carlos,Pedro\"}") @RequestParam Map<String, String> parametros) throws DomainInvalidException {
        var filter = new FilterModel(parametros);
        var page = listarClientesUseCase.executar(filter);
        var clientesDTO = clienteDTOMapper.toClienteDTO(page.getContent());
        return ResponseEntity.ok(new PageModel<>(clientesDTO, page));
    }

    @Operation(summary = "Operação de buscar um cliente", responses = {
            @ApiResponse(description = "Retorna um objeto cliente", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponse.class))
            }),
    })
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ClienteDTO>> get(@PathVariable Long id)
            throws DomainInvalidException, ClienteNaoEncontradoException {
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
