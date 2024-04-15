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
import br.com.sigvet.api.application.dto.vacinacao.AgendarVacinacaoRequestDTO;
import br.com.sigvet.api.application.dto.vacinacao.VacinacaoResponseDTO;
import br.com.sigvet.api.application.exception.CidadeNotFoundException;
import br.com.sigvet.api.application.exception.UsuarioExistsException;
import br.com.sigvet.api.application.exception.UsuarioNotFoundException;
import br.com.sigvet.api.application.exception.VacinaNotFoundException;
import br.com.sigvet.api.application.mapper.base.IVacinacaoMapper;
import br.com.sigvet.api.application.mapper.vacinacao.VacinacaoDTOMapper;
import br.com.sigvet.api.application.model.BaseResponse;
import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.application.model.PageModel;
import br.com.sigvet.api.controller.base.BaseCrudController;
import br.com.sigvet.api.controller.base.MapperManager;
import br.com.sigvet.api.core.domain.entities.Vacinacao;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "Vacinacaos")
@RestController
@RequestMapping("/api/vacinacao")
@Validated
@RequiredArgsConstructor
public class VacinacaoController extends BaseCrudController<Vacinacao, AgendarVacinacaoRequestDTO, Object, VacinacaoResponseDTO> {

        private final MapperManager<IVacinacaoMapper, VacinacaoDTOMapper> mapperManager;

        @GetMapping("/getAll")
        @Override
        public ResponseEntity<PageModel<VacinacaoResponseDTO>> list(@RequestParam Map<String, String> parametros) throws DomainInvalidException {
                log.info("Entrando no método VacinacaoController::list", parametros);
                var filter = new FilterModel(parametros);
                var page = domainObjectUseCaseManager.getListarUseCase().executar(filter);
                var clientesDTO = mapperManager.getDTOMapper().toVacinacaoResponseDTO(page.getContent());
                HttpHeaders headers = new HttpHeaders();
                headers.setCacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS));
                log.info("Saindo do método VacinacaoController::list");
                return new ResponseEntity<>(new PageModel<>(clientesDTO, page), headers, HttpStatus.OK);
        }

        @Override
        public ResponseEntity<BaseResponse<VacinacaoResponseDTO>> create(@Valid AgendarVacinacaoRequestDTO record)
                        throws Exception {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'create'");
        }

        @Override
        public ResponseEntity<BaseResponse<VacinacaoResponseDTO>> put(Long id, @Valid Object record) throws Exception {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'put'");
        }

        @Override
        public ResponseEntity<BaseResponse<Boolean>> delete(Long id) throws Exception {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'delete'");
        }

        @Override
        public ResponseEntity<BaseResponse<VacinacaoResponseDTO>> get(Long id) throws Exception {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'get'");
        }

}
