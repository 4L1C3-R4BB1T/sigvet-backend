package br.com.sigvet.sigvetapi.feature.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JWTService {
    
    private final JwtEncoder jwtEncoder;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiresInMinutes:30}")
    private Long expiresInMinutes; 

    public String generateToken(Authentication authentication) {
        var now = Instant.now();

        String scopes = authentication.getAuthorities()
            .stream()
            .map(scope -> scope.getAuthority())
            .collect(Collectors.joining(" "));

        var jwtClaims = JwtClaimsSet.builder()
            .issuedAt(now)
            .issuer(issuer)
            .expiresAt(now.plus(expiresInMinutes, ChronoUnit.MINUTES))
            .subject(authentication.getName())
            .claims(addClaims -> {
                addClaims.put("scope", scopes);
                final var principal = authentication.getPrincipal();
                if (principal instanceof User user) {
                    addClaims.put("user_id", user.getId());
                }
            })
            .build(); 

        return jwtEncoder.encode(
            JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS256).build(), jwtClaims)
            )
            .getTokenValue();
    }



}
