package br.com.sigvet.sigvetapi.common.swagger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
    
    private static final String DEFAULT_SCHEMA = "Bearer";
    
    @Bean
    OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sigvet")
                        .description("Uma API para fornecer recursos de gerenciamento para uma clínica veterinária")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Gabriel Cardoso Girarde/Lívia Guimarães de Jesus")
                                .email("sigvet@suporte.com")
                                .url("https://github.com/4L1C3-R4BB1T/sigvet-backend")))
                .addSecurityItem(new SecurityRequirement().addList(DEFAULT_SCHEMA))
                .components(new Components()
                        .addSecuritySchemes(DEFAULT_SCHEMA, new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .scheme(DEFAULT_SCHEMA)
                                .name("Authorization")
                                .in(SecurityScheme.In.HEADER)
                                .description("Bearer [TOKEN]"))
                );
    }
}
