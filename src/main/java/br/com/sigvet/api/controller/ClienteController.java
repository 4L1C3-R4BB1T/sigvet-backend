package br.com.sigvet.api.controller;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import br.com.sigvet.api.application.dto.UploadPhotoRequestDTO;
import br.com.sigvet.api.application.dto.cliente.ClientResponseDTO;
import br.com.sigvet.api.application.dto.cliente.CreateClientRequestDTO;
import br.com.sigvet.api.application.dto.cliente.UpdateClientRequestDTO;
import br.com.sigvet.api.application.exception.CidadeNotFoundException;
import br.com.sigvet.api.application.exception.UsuarioExistsException;
import br.com.sigvet.api.application.exception.UsuarioNotFoundException;
import br.com.sigvet.api.application.mapper.base.IClienteMapper;
import br.com.sigvet.api.application.mapper.cliente.ClienteDTOMapper;
import br.com.sigvet.api.application.model.BaseResponse;
import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.application.model.PageModel;
import br.com.sigvet.api.application.service.base.IClienteUploadPhotoService;
import br.com.sigvet.api.controller.base.BaseCrudController;
import br.com.sigvet.api.controller.base.MapperManager;
import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "Clientes")
@RestController
@RequestMapping("/api/customer")
@Validated
@RequiredArgsConstructor
public class ClienteController extends BaseCrudController<Cliente, CreateClientRequestDTO, UpdateClientRequestDTO, ClientResponseDTO> {

        private final MapperManager<IClienteMapper, ClienteDTOMapper> mapperManager;

        private final IClienteUploadPhotoService clienteUploadPhotoService;

        @GetMapping("/getAll")
        @Override
        public ResponseEntity<PageModel<ClientResponseDTO>> list(@RequestParam Map<String, String> parametros) throws DomainInvalidException {
                log.info("Entrando no método ClienteController::list", parametros);
                var filter = new FilterModel(parametros);
                var page = domainObjectUseCaseManager.getListarUseCase().executar(filter);
                var clientesDTO = mapperManager.getDTOMapper().toClienteDTO(page.getContent());
                HttpHeaders headers = new HttpHeaders();
                headers.setCacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS));
                log.info("Saindo do método ClienteController::list");
                return new ResponseEntity<>(new PageModel<>(clientesDTO, page), headers, HttpStatus.OK);
        }
      
        @GetMapping("/get/{id}")
        @Override
        public ResponseEntity<BaseResponse<ClientResponseDTO>> get(@PathVariable Long id) throws DomainInvalidException, UsuarioNotFoundException {
                log.info("Entrando no método ClienteController::get", id);
                var clienteDTO = mapperManager.getDTOMapper().toClienteDTO(domainObjectUseCaseManager.getObterPorIdUseCase().executar(id));
                var baseResponse = new BaseResponse<>(true, HttpStatus.OK.value(), "Cliente retornado", clienteDTO);
                log.info("Saindo do método ClienteController::get");
                return ResponseEntity.ok(baseResponse);
        }

        @PostMapping("/create")
        @Override
        public ResponseEntity<BaseResponse<ClientResponseDTO>> create(@RequestBody @Valid CreateClientRequestDTO record) throws DomainInvalidException, CidadeNotFoundException, UsuarioExistsException {
                log.info("Entrando no método ClienteController::create", record);
                var clienteToSave = mapperManager.getMapper().fromCriarModelToDomain(record);
                var uriBuilder = UriComponentsBuilder.fromUriString("/{id}").buildAndExpand(clienteToSave.getId());  
                var clienteDTO = mapperManager.getDTOMapper().toClienteDTO(domainObjectUseCaseManager.getCadastrarUseCase().executar(clienteToSave));
                var baseResponse = new BaseResponse<ClientResponseDTO>(true,  HttpStatus.CREATED.value(), "Cliente retornado", clienteDTO);
                log.info("Saindo do método ClienteController::create");
                return ResponseEntity.created(uriBuilder.toUri()).body(baseResponse);
        }

        @PutMapping("/update/{id}")
        @Override
        public ResponseEntity<BaseResponse<ClientResponseDTO>> put(@PathVariable Long id, @RequestBody UpdateClientRequestDTO record) throws UsuarioNotFoundException, UsuarioExistsException, DomainInvalidException {
                log.info("Entrando no método ClienteController::put", id, record);
                ClientResponseDTO clienteDTO = mapperManager.getDTOMapper().toClienteDTO(domainObjectUseCaseManager.getAtualizarUseCase().executar(id, mapperManager.getMapper().fromAtualizarModelToDomain(record)));
                var baseResponse = new BaseResponse<ClientResponseDTO>(true, HttpStatus.OK.value(), "Cliente retornado", clienteDTO);
                log.info("Saindo do método ClienteController::put");
                return ResponseEntity.ok(baseResponse);
        }

        @DeleteMapping("/delete/{id}")
        @Override
        public ResponseEntity<BaseResponse<Boolean>> delete(@PathVariable Long id) throws UsuarioExistsException, DomainInvalidException, UsuarioNotFoundException {
                log.info("Entrando no método ClienteController::delete", id);
                var result = domainObjectUseCaseManager.getDeletarUseCase().executar(id);
                var baseResponse = new BaseResponse<>(true, HttpStatus.OK.value(), "Resposta de sucesso retornada", result);
                log.info("Saindo do método ClienteController::delete");
                return ResponseEntity.ok(baseResponse);
        }

        @PostMapping(value = "/upload-photo/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
        public ResponseEntity<byte[]> update(@PathVariable Long id, @Valid UploadPhotoRequestDTO uploadPhotoRequestDTO) {
                byte[] bytes = clienteUploadPhotoService.save(id, uploadPhotoRequestDTO);
                return ResponseEntity.ok().body(bytes);
        }

        @GetMapping(value = "/{id}/photo", produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
        public ResponseEntity<byte[]> update(@PathVariable Long id) {
                return ResponseEntity.ok(clienteUploadPhotoService.getPhoto(id));
        }
}
