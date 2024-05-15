package br.com.sigvet.sigvetapi.feature.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import br.com.sigvet.sigvetapi.common.entities.UserEntity;
import br.com.sigvet.sigvetapi.common.models.ResponseResultModel;
import br.com.sigvet.sigvetapi.feature.user.UserRequestDTO;
import br.com.sigvet.sigvetapi.feature.user.usecases.CreateUserUseCase;
import br.com.sigvet.sigvetapi.feature.user.usecases.FindUserByIdUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Conta", description = "Agrupa endpoints para gerenciar credenciais")
@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final JWTService jwtService;

    private final AuthenticationManager authenticationManager;

    private final CreateUserUseCase createUserUseCase;

    private final FindUserByIdUseCase findUserByIdUseCase;

    @Operation(summary = "Obter informações de um usuário autenticado") 
    @GetMapping("/{id}")
    public ResponseEntity<MappingJacksonValue> get(@PathVariable("id") Long id) {
        final var userEntity = findUserByIdUseCase.execute(id);
        final var responseResultModel = ResponseResultModel.<UserEntity>builder()
                .title("Authentication")
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .result(userEntity)
                .build();

        final var mappingJacksonValue = new MappingJacksonValue(responseResultModel);

        mappingJacksonValue.setFilters(new SimpleFilterProvider().addFilter("user", SimpleBeanPropertyFilter.serializeAll()));
 
        return ResponseEntity.ok(mappingJacksonValue);

    }
    
    @Operation(summary = "Criar um usuário e obter token de autenticação") 
    @PostMapping
    public ResponseEntity<ResponseResultModel<TokenResponseDTO>> post(@RequestBody @Valid UserRequestDTO record) {
        final var userEntitySaved = createUserUseCase.execute(record);
        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userEntitySaved.getEmail(), record.getPassword());
        final Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        final var responseResultModel = ResponseResultModel.<TokenResponseDTO>builder()
                .title("Authentication")
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .result(new TokenResponseDTO(jwtService.generateToken(authentication)))
                .build();
 
        return ResponseEntity.ok(responseResultModel);
    }

    @Operation(summary = "Gerar token de autenticação") 
    @ApiResponse(content = @Content(
        schema = @Schema(example = "{\"title\":\"Authentication\",\"statusCode\":200,\"success\":true,\"result\": { {\"token\": \"TOKEN\"}} }")
    ))
    @PostMapping("/token")
    public ResponseEntity<ResponseResultModel<TokenResponseDTO>> post(@RequestBody @Valid final UserAccountRequestDTO user) {
        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.email(), user.password());
        final Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        final var responseResultModel = ResponseResultModel.<TokenResponseDTO>builder()
                .title("Authentication")
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .result(new TokenResponseDTO(jwtService.generateToken(authentication)))
                .build();
        return ResponseEntity.ok(responseResultModel);
    }

}
