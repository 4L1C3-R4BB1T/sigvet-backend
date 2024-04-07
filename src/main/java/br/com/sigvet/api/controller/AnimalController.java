package br.com.sigvet.api.controller;

import static br.com.sigvet.api.infrastructure.utils.Utilities.logger;

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

import br.com.sigvet.api.application.dto.animal.UpdateAnimalRequestDTO;
import br.com.sigvet.api.application.dto.animal.CreateAnimalRequestDTO;
import br.com.sigvet.api.application.dto.animal.AnimalResponseDTO;
import br.com.sigvet.api.application.exception.CidadeNotFoundException;
import br.com.sigvet.api.application.exception.UsuarioExistsException;
import br.com.sigvet.api.application.exception.UsuarioNotFoundException;
import br.com.sigvet.api.application.mapper.animal.AnimalDTOMapper;
import br.com.sigvet.api.application.mapper.base.IAnimalMapper;
import br.com.sigvet.api.application.model.BaseResponse;
import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.application.model.PageModel;
import br.com.sigvet.api.controller.base.BaseUseCaseController;
import br.com.sigvet.api.core.domain.entities.Animal;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.infrastructure.entity.AnimalEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Animais")
@RestController
@RequestMapping("/api/animal")
@Validated
public class AnimalController extends BaseUseCaseController<Animal, AnimalEntity, CreateAnimalRequestDTO, UpdateAnimalRequestDTO, IAnimalMapper, AnimalDTOMapper, AnimalResponseDTO> {

        @GetMapping("/getAll")
        @Override
        public ResponseEntity<PageModel<AnimalResponseDTO>> list(@RequestParam Map<String, String> parametros) throws DomainInvalidException {
                logger.info("Entrando no método AnimalController::list");
                var filter = new FilterModel(parametros);
                var page = listarUseCase.executar(filter);
                var clientesDTO = DTOMapper.toAnimalDTO(page.getContent());
                HttpHeaders headers = new HttpHeaders();
                headers.setCacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS));
                logger.info("Saíndo do método AnimalController::list");
                return new ResponseEntity<>(new PageModel<>(clientesDTO, page), headers, HttpStatus.OK);
        }

        @GetMapping("/get/{id}")
        @Override
        public ResponseEntity<BaseResponse<AnimalResponseDTO>> get(@PathVariable Long id) throws DomainInvalidException, UsuarioNotFoundException {
                var clienteDTO = DTOMapper.toAnimalDTO(obterPorIdUseCase.executar(id));
                var baseResponse = new BaseResponse<>(true, HttpStatus.OK.value(), "Animal retornado", clienteDTO);
                return ResponseEntity.ok(baseResponse);
        }

        
        @PostMapping("/create")
        @Override
        public ResponseEntity<BaseResponse<AnimalResponseDTO>> create(@RequestBody @Valid CreateAnimalRequestDTO record) throws CidadeNotFoundException, DomainInvalidException  {
                var clienteToSave = mapper.fromCriarModelToDomain(record);
                var uriBuilder = UriComponentsBuilder.fromUriString("/{id}").buildAndExpand(clienteToSave.getId());  
                var clienteDTO = DTOMapper.toAnimalDTO(cadastrarUseCase.executar(clienteToSave));
                var baseResponse = new BaseResponse<AnimalResponseDTO>(true,  HttpStatus.CREATED.value(), "Animal retornado", clienteDTO);
                return ResponseEntity.created(uriBuilder.toUri()).body(baseResponse);
        }

        @PutMapping("/update/{id}")
        @Override
        public ResponseEntity<BaseResponse<AnimalResponseDTO>> put(@PathVariable Long id, @RequestBody UpdateAnimalRequestDTO record) throws UsuarioExistsException, UsuarioNotFoundException, CidadeNotFoundException, DomainInvalidException {
                AnimalResponseDTO clienteDTO = DTOMapper.toAnimalDTO(atualizarUseCase.executar(id, mapper.fromAtualizarModelToDomain(record)));
                var baseResponse = new BaseResponse<AnimalResponseDTO>(true, HttpStatus.OK.value(), "Animal retornado", clienteDTO);
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
