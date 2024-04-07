package br.com.sigvet.api.infrastructure.controller;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
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

import br.com.sigvet.api.application.dto.BaseResponse;
import br.com.sigvet.api.application.dto.cliente.RequestAtualizarClienteDTO;
import br.com.sigvet.api.application.dto.cliente.RequestCriarClienteDTO;
import br.com.sigvet.api.application.dto.cliente.ResponseClienteDTO;
import br.com.sigvet.api.application.exception.CidadeNaoExistenteException;
import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.application.exception.UsuarioNaoEncontradoException;
import br.com.sigvet.api.application.mapper.base.IClienteMapper;
import br.com.sigvet.api.application.mapper.cliente.ClienteDTOMapper;
import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.application.model.PageModel;
import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.infrastructure.entity.ClienteEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Clientes")
@RestController
@RequestMapping("/api/customer")
@Validated
public class ClienteController extends CrudUseCase<Cliente, ClienteEntity, RequestCriarClienteDTO, RequestAtualizarClienteDTO, IClienteMapper, ClienteDTOMapper> {

        @Operation(summary = "Operação de criar um novo cliente", responses = {
                @ApiResponse(description = "Retorna o objeto cliente criado", responseCode = "201", content = { 
                        @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseClienteDTO.class))
                }),
        })
        @PostMapping("/add")
        @ResponseStatus(code = HttpStatus.CREATED)
        public ResponseEntity<BaseResponse<ResponseClienteDTO>> create(@RequestBody @Valid RequestCriarClienteDTO record) throws DomainInvalidException, CidadeNaoExistenteException, UsuarioExistenteException {
                var clienteToSave = mapper.fromCriarModelToDomain(record);
                var uriBuilder = UriComponentsBuilder.fromUriString("/{id}").buildAndExpand(clienteToSave.getId());  
                var clienteDTO = DTOMapper.toClienteDTO(cadastrarUseCase.executar(clienteToSave));
                var baseResponse = new BaseResponse<ResponseClienteDTO>(true,  HttpStatus.CREATED.value(), "Cliente retornado", clienteDTO);
                return ResponseEntity.created(uriBuilder.toUri()).body(baseResponse);
        }


        @Operation(summary = "Operação de atualizar um cliente", responses = {
                        @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = RequestAtualizarClienteDTO.class)))
        })
        @PutMapping("/update/{id}")
        public ResponseEntity<BaseResponse<ResponseClienteDTO>> put(@PathVariable Long id, @RequestBody RequestAtualizarClienteDTO record) throws UsuarioNaoEncontradoException, UsuarioExistenteException, DomainInvalidException {
                ResponseClienteDTO clienteDTO = DTOMapper.toClienteDTO(atualizarUseCase.executar(id, mapper.fromAtualizarModelToDomain(record)));
                var baseResponse = new BaseResponse<ResponseClienteDTO>(true, HttpStatus.OK.value(), "Cliente retornado", clienteDTO);
                return ResponseEntity.ok(baseResponse);
        }

        @Operation(summary = "Operação de deletar um cliente", responses = {
                @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = BaseResponse.class)))
        })
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<BaseResponse<Boolean>> delete(@PathVariable Long id)
                        throws UsuarioExistenteException, DomainInvalidException, UsuarioNaoEncontradoException {
                var result = deletarUseCase.executar(id);
                var baseResponse = new BaseResponse<>(true, HttpStatus.OK.value(), "Resposta de sucesso retornada", result);
                return ResponseEntity.ok(baseResponse);
        }

        @Operation(summary = "Operação de buscar todos os clientes", description = "Descrição", responses = {
                @ApiResponse(description = "Retorna uma lista de objetos clientes", responseCode = "200", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = PageModel.class))
                }),
        })
        @GetMapping("/getAll")
        public ResponseEntity<PageModel<ResponseClienteDTO>> list(
                        @Parameter(description = "Filtros de pesquisas", example = "{\"equal_filters\": \"nome:=Gabriel;cpf:!=17364509720\", \"page\": 1, \"limit\": 10, \"sort\": \"-nome\", \"in_filters\": \"id:1,2,3,4;~nome:José,Carlos,Pedro\"}") @RequestParam Map<String, String> parametros)
                        throws DomainInvalidException {
                var filter = new FilterModel(parametros);
                var page = listarUseCase.executar(filter);
                var clientesDTO = DTOMapper.toClienteDTO(page.getContent());
                HttpHeaders headers = new HttpHeaders();
                headers.setCacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS));
                return new ResponseEntity<>(new PageModel<>(clientesDTO, page), headers, HttpStatus.OK);
        }

        @Operation(summary = "Operação de buscar um cliente", responses = {
                @ApiResponse(description = "Retorna um objeto cliente", responseCode = "200", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponse.class))
                }),
        })
        @GetMapping("/get/{id}")
        public ResponseEntity<BaseResponse<ResponseClienteDTO>> get(@PathVariable Long id) throws DomainInvalidException, UsuarioNaoEncontradoException {
                var clienteDTO = DTOMapper.toClienteDTO(obterPorIdUseCase.executar(id));
                var baseResponse = new BaseResponse<>(true, HttpStatus.OK.value(), "Cliente retornado", clienteDTO);
                return ResponseEntity.ok(baseResponse);
        }

}
