package br.com.sigvet.sigvetapi.common.swagger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
    
    private static final String DEFAULT_SCHEMA = "Bearer";
    
    @Bean
    OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sigvet - API")
                        .license(new License().name("MIT").url("https://opensource.org/licenses/MIT"))
                        .description("Esta é uma solução completa para gerenciamento de animais de estimação, oferecendo recursos abrangentes para cadastro e atualização de animais, agendamento de consultas veterinárias, prescrição e acompanhamento de tratamentos médicos, além de atribuição e gestão de veterinários. Com esta API, os usuários podem cadastrar novos animais de estimação, agendar consultas veterinárias, prescrever tratamentos médicos, atribuir veterinários a consultas específicas e muito mais. Foi desenvolvida com o objetivo de otimizar o fluxo de trabalho em clínicas veterinárias e proporcionar eficiência operacional, melhorando a qualidade do atendimento aos animais de estimação e seus proprietários. Ao oferecer uma solução integrada e de fácil utilização, esta API simplifica a gestão de informações e contribui para uma prática veterinária mais organizada e eficaz.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Gabriel Cardoso Girarde/Lívia Guimarães de Jesus")
                                .email("sigvet@suporte.com")
                                .url("https://sigvet.netlify.app")))
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
