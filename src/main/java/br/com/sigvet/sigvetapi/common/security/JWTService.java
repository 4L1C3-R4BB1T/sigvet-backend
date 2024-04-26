package br.com.sigvet.sigvetapi.common.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.boot.context.properties.ConfigurationProperties;
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
@ConfigurationProperties(prefix = "jwt", ignoreUnknownFields = true, ignoreInvalidFields = true)
public class JWTService {
    
    private final JwtEncoder jwtEncoder;

    private String issuer;

    private Long expiresInMinutes; 

    public String generateToken(Authentication authentication) {
        var now = Instant.now();

        String scopes = authentication.getAuthorities()
            .stream()
            .map(scope -> scope.getAuthority())
            .collect(Collectors.joining(","));

        var jwtClaims = JwtClaimsSet.builder()
            .issuedAt(now)
            .issuer(issuer)
            .expiresAt(now.plus(expiresInMinutes, ChronoUnit.MINUTES))
            .subject(authentication.getName())
            .claims(addClaims -> addClaims.put("scope", scopes))
            .build();

        return jwtEncoder.encode(
            JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS256).build(), jwtClaims)
            )
            .getTokenValue();
    }



}
