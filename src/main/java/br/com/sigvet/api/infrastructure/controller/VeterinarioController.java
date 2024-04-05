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

import br.com.sigvet.api.application.dto.BaseResponse;
import br.com.sigvet.api.application.dto.veterinario.AtualizarVeterinarioDTO;
import br.com.sigvet.api.application.dto.veterinario.CriarVeterinarioDTO;
import br.com.sigvet.api.application.dto.veterinario.VeterinarioDTO;
import br.com.sigvet.api.application.exception.CidadeNaoExistenteException;
import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.application.exception.UsuarioNaoEncontradoException;
import br.com.sigvet.api.application.mapper.base.IVeterinarioMapper;
import br.com.sigvet.api.application.mapper.veterinario.VeterinarioDTOMapper;
import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.application.model.PageModel;
import br.com.sigvet.api.core.domain.entities.Veterinario;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.infrastructure.controller.base.CrudUseCaseController;
import br.com.sigvet.api.infrastructure.entity.VeterinarioEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Veterinários")
@RestController
@RequestMapping("/api/veterinarian")
@Validated
public class VeterinarioController extends CrudUseCaseController<Veterinario, VeterinarioEntity, CriarVeterinarioDTO, AtualizarVeterinarioDTO, IVeterinarioMapper, VeterinarioDTOMapper> {

    @Operation(summary = "Operação de criar um novo veterinario", responses = {
            @ApiResponse(description = "Retorna o objeto veterinario criado", responseCode = "201", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = VeterinarioDTO.class))
            }),
    })
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<BaseResponse<VeterinarioDTO>> create(@RequestBody @Valid CriarVeterinarioDTO record) throws DomainInvalidException, CidadeNaoExistenteException, UsuarioExistenteException {
        var veterinarioToSave = mapper.fromCriarModelToDomain(record);
        var uriBuilder = UriComponentsBuilder.fromUriString("/{id}").buildAndExpand(veterinarioToSave.getId());
        var veterinarioDTO = DTOMapper.toVeterinarioDTO(cadastrarUseCase.executar(veterinarioToSave));
        var baseResponse = new BaseResponse<VeterinarioDTO>(true, HttpStatus.CREATED.value(), "Veterinario retornado", veterinarioDTO);
        return ResponseEntity.created(uriBuilder.toUri()).body(baseResponse);
    }

    @Operation(summary = "Operação de atualizar um veterinario", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = AtualizarVeterinarioDTO.class)))
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<BaseResponse<VeterinarioDTO>> put(@PathVariable Long id, @RequestBody AtualizarVeterinarioDTO record) throws UsuarioNaoEncontradoException, UsuarioExistenteException, DomainInvalidException {
        VeterinarioDTO veterinarioDTO = DTOMapper.toVeterinarioDTO(atualizarUseCase.executar(id, mapper.fromAtualizarModelToDomain(record)));
        var baseResponse = new BaseResponse<VeterinarioDTO>(true, HttpStatus.OK.value(), "Veterinario retornado", veterinarioDTO);
        return ResponseEntity.ok(baseResponse);
    }

    @Operation(summary = "Operação de deletar um veterinario", responses = {
        @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponse<Boolean>> delete(@PathVariable Long id) throws UsuarioExistenteException, DomainInvalidException, UsuarioNaoEncontradoException {
        var result = deletarUseCase.executar(id);
        var baseResponse = new BaseResponse<>(true, HttpStatus.OK.value(), "Operação de deletar veterinario", result, null);
        return ResponseEntity.ok(baseResponse);
    }

    @Operation(summary = "Operação de buscar todos os veterinarios", description = "Descrição", responses = {
        @ApiResponse(description = "Retorna uma lista de objetos veterinarios", responseCode = "200", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = PageModel.class))
        }),
    })
    @GetMapping("/get")
    public ResponseEntity<PageModel<VeterinarioDTO>> list(@Parameter(description = "Filters for search", example = "{\"equal_filters\": \"nome:=Gabriel;cpf:!=17364509720\", \"page\": 1, \"limit\": 10, \"sort\": \"-nome\", \"in_filters\": \"id:1,2,3,4;~nome:José,Carlos,Pedro\"}") @RequestParam Map<String, String> parametros) throws DomainInvalidException {
        var filter = new FilterModel(parametros);
        var page = listarUseCase.executar(filter);
        var veterinariosDTO = DTOMapper.toVeterinarioDTO(page.getContent());
        return ResponseEntity.ok(new PageModel<>(veterinariosDTO, page));
    }

    @Operation(summary = "Operação de buscar um veterinario", responses = {
            @ApiResponse(description = "Retorna um objeto veterinario", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponse.class))
            }),
    })
    @GetMapping("/get/{id}")
    public ResponseEntity<BaseResponse<VeterinarioDTO>> get(@PathVariable Long id) throws DomainInvalidException, UsuarioNaoEncontradoException {
        var veterinarioDTO = DTOMapper.toVeterinarioDTO(obterPorIdUseCase.executar(id));
        var baseResponse = new BaseResponse<VeterinarioDTO>(true, HttpStatus.OK.value(), "Veterinario retornado", veterinarioDTO);
        return ResponseEntity.ok(baseResponse);
    }
    
}
