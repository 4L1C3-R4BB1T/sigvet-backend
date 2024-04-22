package br.com.sigvet.sigvetapi.feature.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {
    
    @Bean
    ClientMapper clientMapper() {
        return ClientMapper.INSTANCE;
    }
    
}
