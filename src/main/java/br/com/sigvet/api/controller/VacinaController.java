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

import br.com.sigvet.api.application.dto.vacina.CreateVaccineRequestDTO;
import br.com.sigvet.api.application.dto.vacina.UpdateVaccineRequestDTO;
import br.com.sigvet.api.application.dto.vacina.VaccineResponseDTO;
import br.com.sigvet.api.application.exception.CidadeNotFoundException;
import br.com.sigvet.api.application.exception.UsuarioExistsException;
import br.com.sigvet.api.application.exception.UsuarioNotFoundException;
import br.com.sigvet.api.application.exception.VacinaNotFoundException;
import br.com.sigvet.api.application.mapper.base.IVacinaMapper;
import br.com.sigvet.api.application.mapper.vacina.VacinaDTOMapper;
import br.com.sigvet.api.application.model.BaseResponse;
import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.application.model.PageModel;
import br.com.sigvet.api.controller.base.BaseCrudController;
import br.com.sigvet.api.controller.base.MapperManager;
import br.com.sigvet.api.core.domain.entities.Vacina;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "Vacinas")
@RestController
@RequestMapping("/api/vaccine")
@Validated
@RequiredArgsConstructor
public class VacinaController extends BaseCrudController<Vacina, CreateVaccineRequestDTO, UpdateVaccineRequestDTO, VaccineResponseDTO> {

        private final MapperManager<IVacinaMapper, VacinaDTOMapper> mapperManager;

        @GetMapping("/getAll")
        @Override
        public ResponseEntity<PageModel<VaccineResponseDTO>> list(@RequestParam Map<String, String> parametros) throws DomainInvalidException {
                log.info("Entrando no método VacinaController::list", parametros);
                var filter = new FilterModel(parametros);
                var page = domainObjectUseCaseManager.getListarUseCase().executar(filter);
                var vacinasDTO = mapperManager.getDTOMapper().toVacinaDTO(page.getContent());
                HttpHeaders headers = new HttpHeaders();
                headers.setCacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS));
                log.info("Saindo do método VacinaController::list");
                return new ResponseEntity<>(new PageModel<>(vacinasDTO, page), headers, HttpStatus.OK);
        }

        @GetMapping("/get/{id}")
        @Override
        public ResponseEntity<BaseResponse<VaccineResponseDTO>> get(@PathVariable Long id) throws DomainInvalidException, VacinaNotFoundException {
                log.info("Entrando no método VacinaController::get", id);
                var vacinaDTO = mapperManager.getDTOMapper().toVacinaDTO(domainObjectUseCaseManager.getObterPorIdUseCase().executar(id));
                var baseResponse = new BaseResponse<>(true, HttpStatus.OK.value(), "Vacina retornado", vacinaDTO);
                log.info("Saindo do método VacinaController::get");
                return ResponseEntity.ok(baseResponse);
        }

        @PostMapping("/create")
        @Override
        public ResponseEntity<BaseResponse<VaccineResponseDTO>> create(@RequestBody @Valid CreateVaccineRequestDTO record) throws DomainInvalidException {
                log.info("Entrando no método VacinaController::create", record);
                var vacinaToSave = mapperManager.getMapper().fromCriarModelToDomain(record);
                var uriBuilder = UriComponentsBuilder.fromUriString("/{id}").buildAndExpand(vacinaToSave.getId());  
                var vacinaDTO = mapperManager.getDTOMapper().toVacinaDTO(domainObjectUseCaseManager.getCadastrarUseCase().executar(vacinaToSave));
                var baseResponse = new BaseResponse<VaccineResponseDTO>(true,  HttpStatus.CREATED.value(), "Vacina retornado", vacinaDTO);
                log.info("Saindo do método VacinaController::create");
                return ResponseEntity.created(uriBuilder.toUri()).body(baseResponse);
        }

        @PutMapping("/update/{id}")
        @Override
        public ResponseEntity<BaseResponse<VaccineResponseDTO>> put(@PathVariable Long id, @RequestBody UpdateVaccineRequestDTO record) throws DomainInvalidException, UsuarioExistsException, UsuarioNotFoundException, CidadeNotFoundException, VacinaNotFoundException {
                log.info("Entrando no método VacinaController::put", id, record);
                VaccineResponseDTO vacinaDTO = mapperManager.getDTOMapper().toVacinaDTO(domainObjectUseCaseManager.getAtualizarUseCase().executar(id, mapperManager.getMapper().fromAtualizarModelToDomain(record)));
                var baseResponse = new BaseResponse<VaccineResponseDTO>(true, HttpStatus.OK.value(), "Vacina retornado", vacinaDTO);
                log.info("Saindo do método VacinaController::put");
                return ResponseEntity.ok(baseResponse);
        }

        @DeleteMapping("/delete/{id}")
        @Override
        public ResponseEntity<BaseResponse<Boolean>> delete(@PathVariable Long id) throws DomainInvalidException, VacinaNotFoundException {
                log.info("Entrando no método VacinaController::delete", id);
                var result = domainObjectUseCaseManager.getDeletarUseCase().executar(id);
                var baseResponse = new BaseResponse<>(true, HttpStatus.OK.value(), "Resposta de sucesso retornada", result);
                log.info("Saindo do método VacinaController::delete");
                return ResponseEntity.ok(baseResponse);
        }

}
