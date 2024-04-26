package br.com.sigvet.sigvetapi.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    
    // @Bean
    // OpenAPI openAPI() {
    //     return new OpenAPI()
    //             .info(new Info()
    //                     .title("Sigvet API")
    //                     .description("API que fornece CRUD para entidades relacionadas a uma clínica veterinária")
    //                     .version("1.0")
    //                     .contact(new Contact()
    //                             .name("Gabriel Cardoso Girarde/Lívia Guimarães de Jesus")
    //                             .email("sigvet@suporte.com")
    //                             .url("https://github.com/4L1C3-R4BB1T/sigvet-backend")));
    //             // .servers(Collections
    //             //         .singletonList(new Server()
    //             //                 .url(serverUrl)
    //             //                 .description("Servidor de desenvolvimento")))
    //             // .addSecurityItem(new SecurityRequirement().addList(JWT_AUTHENTICATION))
    //             // .components(new Components()
    //             //         .addSecuritySchemes(JWT_AUTHENTICATION, new SecurityScheme()
    //             //                 .type(SecurityScheme.Type.APIKEY)
    //             //                 .scheme(JWT_AUTHENTICATION)
    //             //                 .name("Authorization")
    //             //                 .in(SecurityScheme.In.HEADER)
    //             //                 .description("Bearer token_value"))
    //             // );
    // }
}
