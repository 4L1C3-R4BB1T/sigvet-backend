package br.com.sigvet.sigvetapi.feature.animal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnimalConfig {
    
    @Bean
    AnimalMapper animalMapper() {
        return AnimalMapper.INSTANCE;
    }
    
}
