package br.com.sigvet.sigvetapi.feature.security;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.nimbusds.jose.jwk.source.ImmutableSecret;

import jakarta.annotation.PostConstruct;
import lombok.Getter;

@Configuration
@EnableWebSecurity
@Getter
public class SecurityConfig implements WebMvcConfigurer {

    private String secretKey;

    private final String[] WHITELIST = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/",
            "/api/v1/cities/**"
    };

    @PostConstruct
    void generateSecretKey() {
        byte[] secretKeyBytes = new byte[32];
        new SecureRandom().nextBytes(secretKeyBytes);
        secretKey = Base64.getEncoder().encodeToString(secretKeyBytes);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
        .allowedOrigins("*")
        .allowedMethods("*");
    }

    @Bean
    SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(
                        sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeRequests -> {
                    // authorizeRequests.requestMatchers(WHITELIST).permitAll();
                    // authorizeRequests.requestMatchers(HttpMethod.POST, "/api/v1/account/**").permitAll();
                    // authorizeRequests.requestMatchers(HttpMethod.GET, "/api/v1/account/**").permitAll();
                    authorizeRequests.anyRequest().permitAll();
                    // authorizeRequests.anyRequest().permitAll();
                })
                .oauth2ResourceServer(config -> {
                    config.jwt(Customizer.withDefaults());
                })
                .build();
    }
 
    @Bean
    AuthenticationManager authenticationManager(final AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        final SecretKey originalKey = new SecretKeySpec(secretKey.getBytes(), "RSA");
        return NimbusJwtDecoder.withSecretKey(originalKey).build();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        final SecretKey key = new SecretKeySpec(secretKey.getBytes(), "RSA");
        final var immutableSecret = new ImmutableSecret<>(key);
        return new NimbusJwtEncoder(immutableSecret);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
