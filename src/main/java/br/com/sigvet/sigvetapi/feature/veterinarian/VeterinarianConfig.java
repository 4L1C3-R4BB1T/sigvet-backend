package br.com.sigvet.sigvetapi.feature.veterinarian;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VeterinarianConfig {
    
    @Bean
    VeterinarianMapper veterinarianMapper() {
        return VeterinarianMapper.INSTANCE;
    }
    
}
