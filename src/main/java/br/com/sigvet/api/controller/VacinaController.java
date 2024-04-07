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

import br.com.sigvet.api.application.dto.vacina.UpdateVaccineRequestDTO;
import br.com.sigvet.api.application.dto.vacina.CreateVaccineRequestDTO;
import br.com.sigvet.api.application.dto.vacina.VaccineResponseDTO;
import br.com.sigvet.api.application.exception.CidadeNotFoundException;
import br.com.sigvet.api.application.exception.UsuarioExistsException;
import br.com.sigvet.api.application.exception.UsuarioNotFoundException;
import br.com.sigvet.api.application.mapper.base.IVacinaMapper;
import br.com.sigvet.api.application.mapper.vacina.VacinaDTOMapper;
import br.com.sigvet.api.application.model.BaseResponse;
import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.application.model.PageModel;
import br.com.sigvet.api.controller.base.BaseUseCaseController;
import br.com.sigvet.api.core.domain.entities.Vacina;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.infrastructure.entity.VacinaEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Vacinas")
@RestController
@RequestMapping("/api/vaccine")
@Validated
public class VacinaController extends BaseUseCaseController<Vacina, VacinaEntity, CreateVaccineRequestDTO, UpdateVaccineRequestDTO, IVacinaMapper, VacinaDTOMapper, VaccineResponseDTO> {

        @GetMapping("/getAll")
        @Override
        public ResponseEntity<PageModel<VaccineResponseDTO>> list(@RequestParam Map<String, String> parametros) throws DomainInvalidException {
                var filter = new FilterModel(parametros);
                var page = listarUseCase.executar(filter);
                var vacinasDTO = DTOMapper.toVacinaDTO(page.getContent());
                HttpHeaders headers = new HttpHeaders();
                headers.setCacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS));
                return new ResponseEntity<>(new PageModel<>(vacinasDTO, page), headers, HttpStatus.OK);
        }

      
        @GetMapping("/get/{id}")
        @Override
        public ResponseEntity<BaseResponse<VaccineResponseDTO>> get(@PathVariable Long id) throws DomainInvalidException, UsuarioNotFoundException {
                var vacinaDTO = DTOMapper.toVacinaDTO(obterPorIdUseCase.executar(id));
                var baseResponse = new BaseResponse<>(true, HttpStatus.OK.value(), "Vacina retornado", vacinaDTO);
                return ResponseEntity.ok(baseResponse);
        }

        @PostMapping("/create")
        @Override
        public ResponseEntity<BaseResponse<VaccineResponseDTO>> create(@RequestBody @Valid CreateVaccineRequestDTO record) throws DomainInvalidException, CidadeNotFoundException, UsuarioExistsException {
                var vacinaToSave = mapper.fromCriarModelToDomain(record);
                var uriBuilder = UriComponentsBuilder.fromUriString("/{id}").buildAndExpand(vacinaToSave.getId());  
                var vacinaDTO = DTOMapper.toVacinaDTO(cadastrarUseCase.executar(vacinaToSave));
                var baseResponse = new BaseResponse<VaccineResponseDTO>(true,  HttpStatus.CREATED.value(), "Vacina retornado", vacinaDTO);
                return ResponseEntity.created(uriBuilder.toUri()).body(baseResponse);
        }

        @PutMapping("/update/{id}")
        @Override
        public ResponseEntity<BaseResponse<VaccineResponseDTO>> put(@PathVariable Long id, @RequestBody UpdateVaccineRequestDTO record) throws UsuarioNotFoundException, UsuarioExistsException, DomainInvalidException {
                VaccineResponseDTO vacinaDTO = DTOMapper.toVacinaDTO(atualizarUseCase.executar(id, mapper.fromAtualizarModelToDomain(record)));
                var baseResponse = new BaseResponse<VaccineResponseDTO>(true, HttpStatus.OK.value(), "Vacina retornado", vacinaDTO);
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
