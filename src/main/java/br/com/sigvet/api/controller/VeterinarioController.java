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

import br.com.sigvet.api.application.dto.veterinario.UpdateVeterinarianRequestDTO;
import br.com.sigvet.api.application.dto.veterinario.CreateVeterinarianRequestDTO;
import br.com.sigvet.api.application.dto.veterinario.VeterinarianResponseDTO;
import br.com.sigvet.api.application.exception.CidadeNotFoundException;
import br.com.sigvet.api.application.exception.UsuarioExistsException;
import br.com.sigvet.api.application.exception.UsuarioNotFoundException;
import br.com.sigvet.api.application.mapper.base.IVeterinarioMapper;
import br.com.sigvet.api.application.mapper.veterinario.VeterinarioDTOMapper;
import br.com.sigvet.api.application.model.BaseResponse;
import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.application.model.PageModel;
import br.com.sigvet.api.controller.base.BaseCrudController;
import br.com.sigvet.api.controller.base.MapperManager;
import br.com.sigvet.api.core.domain.entities.Veterinario;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "Veterinários")
@RestController
@RequestMapping("/api/veterinarian")
@Validated
@RequiredArgsConstructor
public class VeterinarioController extends BaseCrudController<Veterinario, CreateVeterinarianRequestDTO, UpdateVeterinarianRequestDTO, VeterinarianResponseDTO> {

    private final MapperManager<IVeterinarioMapper, VeterinarioDTOMapper> mapperManager;

    @GetMapping("/getAll")
    @Override
    public ResponseEntity<PageModel<VeterinarianResponseDTO>> list(@RequestParam Map<String, String> parametros) throws DomainInvalidException {
        log.info("Entrando no método VeterinarioController::list", parametros);
        var filter = new FilterModel(parametros);
        var page = domainObjectUseCaseManager.getListarUseCase().executar(filter);
        var veterinariosDTO = mapperManager.getDTOMapper().toVeterinarioDTO(page.getContent());
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS));
        log.info("Saindo do método VeterinarioController::list", parametros);
        return new ResponseEntity<>(new PageModel<>(veterinariosDTO, page), headers, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    @Override
    public ResponseEntity<BaseResponse<VeterinarianResponseDTO>> get(@PathVariable Long id) throws DomainInvalidException, UsuarioNotFoundException {
        log.info("Entrando no método VeterinarioController::get", id);
        var veterinarioDTO = mapperManager.getDTOMapper().toVeterinarioDTO(domainObjectUseCaseManager.getObterPorIdUseCase().executar(id));
        var baseResponse = new BaseResponse<VeterinarianResponseDTO>(true, HttpStatus.OK.value(), "Veterinario retornado", veterinarioDTO);
        log.info("Saindo do método VeterinarioController::get", id);
        return ResponseEntity.ok(baseResponse);
    }

    @PostMapping("/create")
    @Override
    public ResponseEntity<BaseResponse<VeterinarianResponseDTO>> create(@RequestBody @Valid CreateVeterinarianRequestDTO record) throws DomainInvalidException, CidadeNotFoundException, UsuarioExistsException {
        log.info("Entrando no método VeterinarioController::create", record);
        var veterinarioToSave = mapperManager.getMapper().fromCriarModelToDomain(record);
        var uriBuilder = UriComponentsBuilder.fromUriString("/{id}").buildAndExpand(veterinarioToSave.getId());
        var veterinarioDTO = mapperManager.getDTOMapper().toVeterinarioDTO(domainObjectUseCaseManager.getCadastrarUseCase().executar(veterinarioToSave));
        var baseResponse = new BaseResponse<VeterinarianResponseDTO>(true, HttpStatus.CREATED.value(), "Veterinario retornado", veterinarioDTO);
        log.info("Saindo do método VeterinarioController::create", record);
        return ResponseEntity.created(uriBuilder.toUri()).body(baseResponse);
    }

    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<BaseResponse<VeterinarianResponseDTO>> put(@PathVariable Long id, @RequestBody UpdateVeterinarianRequestDTO record) throws UsuarioNotFoundException, UsuarioExistsException, DomainInvalidException {
        log.info("Entrando no método VeterinarioController::put", id, record);
        VeterinarianResponseDTO veterinarioDTO = mapperManager.getDTOMapper().toVeterinarioDTO(domainObjectUseCaseManager.getAtualizarUseCase().executar(id, mapperManager.getMapper().fromAtualizarModelToDomain(record)));
        var baseResponse = new BaseResponse<VeterinarianResponseDTO>(true, HttpStatus.OK.value(), "Veterinario retornado", veterinarioDTO);
        log.info("Saindo do método VeterinarioController::put", id, record);
        return ResponseEntity.ok(baseResponse);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public ResponseEntity<BaseResponse<Boolean>> delete(@PathVariable Long id) throws UsuarioExistsException, DomainInvalidException, UsuarioNotFoundException {
        log.info("Entrando no método VeterinarioController::delete", id);
        var result = domainObjectUseCaseManager.getDeletarUseCase().executar(id);
        var baseResponse = new BaseResponse<>(true, HttpStatus.OK.value(), "Operação de deletar veterinario", result, null);
        log.info("Saindo do método VeterinarioController::delete", id);
        return ResponseEntity.ok(baseResponse);
    }
    
}
