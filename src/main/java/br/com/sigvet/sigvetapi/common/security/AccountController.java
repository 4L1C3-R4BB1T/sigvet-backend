package br.com.sigvet.sigvetapi.common.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigvet.sigvetapi.common.models.ResponseResultModel;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    
    private final JWTService jwtService;

    private final AuthenticationManager authenticationManager;

    @PostMapping
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
