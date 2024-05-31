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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import br.com.sigvet.sigvetapi.common.entities.AddressEntity;
import br.com.sigvet.sigvetapi.common.entities.AnimalEntity;
import br.com.sigvet.sigvetapi.common.entities.ClientEntity;
import br.com.sigvet.sigvetapi.common.entities.UserEntity;
import br.com.sigvet.sigvetapi.common.entities.enums.EntityType;
import br.com.sigvet.sigvetapi.common.models.ResponseResultModel;
import br.com.sigvet.sigvetapi.feature.photo.usecases.FindPhotoUseCase;
import br.com.sigvet.sigvetapi.feature.security.usecases.GrantUserAccessUseCase;
import br.com.sigvet.sigvetapi.feature.security.usecases.RecoverUserUseCase;
import br.com.sigvet.sigvetapi.feature.user.UserRequestDTO;
import br.com.sigvet.sigvetapi.feature.user.usecases.CreateUserUseCase;
import br.com.sigvet.sigvetapi.feature.user.usecases.FindUserByIdUseCase;
import br.com.sigvet.sigvetapi.feature.user.usecases.UpdateUserUseCase;
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

    private final FindPhotoUseCase findPhotoUseCase;

    private final RecoverUserUseCase recoverUserUseCase;

    private final UpdateUserUseCase updateUserUseCase;

    private final GrantUserAccessUseCase grantUserAccessUseCase;

    @Operation(summary = "Endpoint para permitir acesso para um usuário visualizador")
    @PostMapping("/allow-access/{id}")
    public ResponseEntity<Void> postAllowAccess(@PathVariable("id") Long id) {
        grantUserAccessUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Endpoint para atualizar perfil do usuário") 
    @PutMapping("/profile/{id}/update")
    public ResponseEntity<ResponseResultModel<Boolean>> put(@PathVariable("id") Long id, @RequestBody @Valid UserRequestDTO record) {
        final var responseResultModel = ResponseResultModel.<Boolean>builder()
            .title("User Updated")
            .statusCode(HttpStatus.OK.value())
            .success(true)
            .result(updateUserUseCase.execute(id, record))
            .build();
        return ResponseEntity.ok(responseResultModel);
    }

    @Operation(summary = "Resetar senha de um usuário") 
    @PostMapping("/recover")
    public ResponseEntity<ResponseResultModel<Boolean>> post(@RequestBody @Valid RecoverUserRequestDTO request) {
        final var responseResultModel = ResponseResultModel.<Boolean>builder()
            .title("Authentication")
            .statusCode(HttpStatus.CREATED.value())
            .success(true)
            .result(recoverUserUseCase.execute(request))
            .build();
        
        return ResponseEntity.ok(responseResultModel);
    }


    @Operation(summary = "Obter informações de um usuário autenticado") 
    @GetMapping("/{id}")
    public ResponseEntity<MappingJacksonValue> get(@PathVariable("id") Long id) {
        final var userEntity = findUserByIdUseCase.execute(id);

        try {
            findPhotoUseCase.execute(id, EntityType.USER);
            userEntity.setPhotoUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/photo/user/{id}").buildAndExpand(id).toString());
        } catch (Exception ex) {}

        final var responseResultModel = ResponseResultModel.<UserEntity>builder()
                .title("Authentication")
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .result(userEntity)
                .build();
 
        final var mappingJacksonValue = new MappingJacksonValue(responseResultModel);
        final var simpleFilterProvider = new SimpleFilterProvider().setFailOnUnknownId(false);

        simpleFilterProvider
            .addFilter(UserEntity.USER_ENTITY_FILTER_KEY, SimpleBeanPropertyFilter.serializeAllExcept("password", "animals"))
            .addFilter(ClientEntity.CLIENT_ENTITY_FILTER_KEY, SimpleBeanPropertyFilter.serializeAllExcept("password"))
            .addFilter(AddressEntity.ADDRESS_ENTITY_FILTER_KEY, SimpleBeanPropertyFilter.serializeAllExcept("user"))
            .addFilter(AnimalEntity.ANIMAL_ENTITY_FILTER_KEY, SimpleBeanPropertyFilter.serializeAllExcept("client"));

        
        mappingJacksonValue.setFilters(simpleFilterProvider);
 
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
        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.emailOrUsername(), user.password());
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
