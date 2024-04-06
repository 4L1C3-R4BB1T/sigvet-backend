package br.com.sigvet.api.infrastructure.controller;

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
import br.com.sigvet.api.application.dto.vacina.RequestAtualizarVacinaDTO;
import br.com.sigvet.api.application.dto.vacina.ResponseVacinaDTO;
import br.com.sigvet.api.application.dto.vacina.RequestCriarVacinaDTO;
import br.com.sigvet.api.application.exception.CidadeNaoExistenteException;
import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.application.exception.UsuarioNaoEncontradoException;
import br.com.sigvet.api.application.mapper.base.IVacinaMapper;
import br.com.sigvet.api.application.mapper.vacina.VacinaDTOMapper;
import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.application.model.PageModel;
import br.com.sigvet.api.core.domain.entities.Vacina;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.infrastructure.controller.base.CrudUseCaseController;
import br.com.sigvet.api.infrastructure.entity.VacinaEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Vacinas")
@RestController
@RequestMapping("/api/vaccine")
@Validated
public class VacinaController extends CrudUseCaseController<Vacina, VacinaEntity, RequestCriarVacinaDTO, RequestAtualizarVacinaDTO, IVacinaMapper, VacinaDTOMapper> {

        @Operation(summary = "Operação de criar um novo vacina", responses = {
                @ApiResponse(description = "Retorna o objeto vacina criado", responseCode = "201", content = { 
                        @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseVacinaDTO.class))
                }),
        })
        @PostMapping("/add")
        @ResponseStatus(code = HttpStatus.CREATED)
        public ResponseEntity<BaseResponse<ResponseVacinaDTO>> create(@RequestBody @Valid RequestCriarVacinaDTO record) throws DomainInvalidException, CidadeNaoExistenteException, UsuarioExistenteException {
                var vacinaToSave = mapper.fromCriarModelToDomain(record);
                var uriBuilder = UriComponentsBuilder.fromUriString("/{id}").buildAndExpand(vacinaToSave.getId());  
                var vacinaDTO = DTOMapper.toVacinaDTO(cadastrarUseCase.executar(vacinaToSave));
                var baseResponse = new BaseResponse<ResponseVacinaDTO>(true,  HttpStatus.CREATED.value(), "Vacina retornado", vacinaDTO);
                return ResponseEntity.created(uriBuilder.toUri()).body(baseResponse);
        }


        @Operation(summary = "Operação de atualizar um vacina", responses = {
                        @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = RequestAtualizarVacinaDTO.class)))
        })
        @PutMapping("/update/{id}")
        public ResponseEntity<BaseResponse<ResponseVacinaDTO>> put(@PathVariable Long id, @RequestBody RequestAtualizarVacinaDTO record) throws UsuarioNaoEncontradoException, UsuarioExistenteException, DomainInvalidException {
                ResponseVacinaDTO vacinaDTO = DTOMapper.toVacinaDTO(atualizarUseCase.executar(id, mapper.fromAtualizarModelToDomain(record)));
                var baseResponse = new BaseResponse<ResponseVacinaDTO>(true, HttpStatus.OK.value(), "Vacina retornado", vacinaDTO);
                return ResponseEntity.ok(baseResponse);
        }

        @Operation(summary = "Operação de deletar um vacina", responses = {
                @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = BaseResponse.class)))
        })
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<BaseResponse<Boolean>> delete(@PathVariable Long id)
                        throws UsuarioExistenteException, DomainInvalidException, UsuarioNaoEncontradoException {
                var result = deletarUseCase.executar(id);
                var baseResponse = new BaseResponse<>(true, HttpStatus.OK.value(), "Resposta de sucesso retornada", result);
                return ResponseEntity.ok(baseResponse);
        }

        @Operation(summary = "Operação de buscar todos os vacinas", description = "Descrição", responses = {
                @ApiResponse(description = "Retorna uma lista de objetos vacinas", responseCode = "200", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = PageModel.class))
                }),
        })
        @GetMapping("/getAll")
        public ResponseEntity<PageModel<ResponseVacinaDTO>> list(
                        @Parameter(description = "Filtros de pesquisas", example = "{\"equal_filters\": \"nome:=Gabriel;cpf:!=17364509720\", \"page\": 1, \"limit\": 10, \"sort\": \"-nome\", \"in_filters\": \"id:1,2,3,4;~nome:José,Carlos,Pedro\"}") @RequestParam Map<String, String> parametros)
                        throws DomainInvalidException {
                var filter = new FilterModel(parametros);
                var page = listarUseCase.executar(filter);
                var vacinasDTO = DTOMapper.toVacinaDTO(page.getContent());
                return ResponseEntity.ok(new PageModel<>(vacinasDTO, page));
        }

        @Operation(summary = "Operação de buscar um vacina", responses = {
                @ApiResponse(description = "Retorna um objeto vacina", responseCode = "200", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponse.class))
                }),
        })
        @GetMapping("/get/{id}")
        public ResponseEntity<BaseResponse<ResponseVacinaDTO>> get(@PathVariable Long id) throws DomainInvalidException, UsuarioNaoEncontradoException {
                var vacinaDTO = DTOMapper.toVacinaDTO(obterPorIdUseCase.executar(id));
                var baseResponse = new BaseResponse<>(true, HttpStatus.OK.value(), "Vacina retornado", vacinaDTO);
                return ResponseEntity.ok(baseResponse);
        }

}
