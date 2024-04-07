package br.com.sigvet.api.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sigvet.api.application.dto.cliente.UpdateClientRequestDTO;
import br.com.sigvet.api.application.dto.cliente.CreateClientRequestDTO;
import br.com.sigvet.api.application.dto.cliente.ClientResponseDTO;
import br.com.sigvet.api.application.exception.CidadeNotFoundException;
import br.com.sigvet.api.application.exception.UsuarioExistsException;
import br.com.sigvet.api.application.exception.UsuarioNotFoundException;
import br.com.sigvet.api.application.mapper.base.IClienteMapper;
import br.com.sigvet.api.application.mapper.cliente.ClienteDTOMapper;
import br.com.sigvet.api.application.model.BaseResponse;
import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.application.model.PageModel;
import br.com.sigvet.api.controller.base.BaseUseCaseController;
import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.infrastructure.entity.ClienteEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Clientes")
@RestController
@RequestMapping("/api/customer")
@Validated
public class ClienteController extends BaseUseCaseController<Cliente, ClienteEntity, CreateClientRequestDTO, UpdateClientRequestDTO, IClienteMapper, ClienteDTOMapper, ClientResponseDTO> {

        @GetMapping("/getAll")
        @Override
        public ResponseEntity<PageModel<ClientResponseDTO>> list(@RequestParam Map<String, String> parametros) throws DomainInvalidException {
                var filter = new FilterModel(parametros);
                var page = listarUseCase.executar(filter);
                var clientesDTO = DTOMapper.toClienteDTO(page.getContent());
                HttpHeaders headers = new HttpHeaders();
                headers.setCacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS));
                return new ResponseEntity<>(new PageModel<>(clientesDTO, page), headers, HttpStatus.OK);
        }

      
        @GetMapping("/get/{id}")
        @Override
        public ResponseEntity<BaseResponse<ClientResponseDTO>> get(@PathVariable Long id) throws DomainInvalidException, UsuarioNotFoundException {
                var clienteDTO = DTOMapper.toClienteDTO(obterPorIdUseCase.executar(id));
                var baseResponse = new BaseResponse<>(true, HttpStatus.OK.value(), "Cliente retornado", clienteDTO);
                return ResponseEntity.ok(baseResponse);
        }

        @PostMapping("/create")
        @Override
        public ResponseEntity<BaseResponse<ClientResponseDTO>> create(@RequestBody @Valid CreateClientRequestDTO record) throws DomainInvalidException, CidadeNotFoundException, UsuarioExistsException {
                var clienteToSave = mapper.fromCriarModelToDomain(record);
                var uriBuilder = UriComponentsBuilder.fromUriString("/{id}").buildAndExpand(clienteToSave.getId());  
                var clienteDTO = DTOMapper.toClienteDTO(cadastrarUseCase.executar(clienteToSave));
                var baseResponse = new BaseResponse<ClientResponseDTO>(true,  HttpStatus.CREATED.value(), "Cliente retornado", clienteDTO);
                return ResponseEntity.created(uriBuilder.toUri()).body(baseResponse);
        }


        @PutMapping("/update/{id}")
        @Override
        public ResponseEntity<BaseResponse<ClientResponseDTO>> put(@PathVariable Long id, @RequestBody UpdateClientRequestDTO record) throws UsuarioNotFoundException, UsuarioExistsException, DomainInvalidException {
                ClientResponseDTO clienteDTO = DTOMapper.toClienteDTO(atualizarUseCase.executar(id, mapper.fromAtualizarModelToDomain(record)));
                var baseResponse = new BaseResponse<ClientResponseDTO>(true, HttpStatus.OK.value(), "Cliente retornado", clienteDTO);
                return ResponseEntity.ok(baseResponse);
        }


        @DeleteMapping("/delete/{id}")
        @Override
        public ResponseEntity<BaseResponse<Boolean>> delete(@PathVariable Long id)
                        throws UsuarioExistsException, DomainInvalidException, UsuarioNotFoundException {
                var result = deletarUseCase.executar(id);
                var baseResponse = new BaseResponse<>(true, HttpStatus.OK.value(), "Resposta de sucesso retornada", result);
                return ResponseEntity.ok(baseResponse);
        }

}
