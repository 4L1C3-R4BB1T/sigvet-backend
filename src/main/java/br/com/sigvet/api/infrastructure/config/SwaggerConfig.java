package br.com.sigvet.api.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    
    @Bean
    OpenAPI openAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("SIGVET BACKEND")
                .description("Uma API de gerencimaneto para uma clínica veterinária")
                .contact(new Contact()
                    .email("sigvet@gmail.com")
                    .url("https://github.com/4L1C3-R4BB1T/sigvet-backend")));
    }
}
