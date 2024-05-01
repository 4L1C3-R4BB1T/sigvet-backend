package br.com.sigvet.sigvetapi.feature.photo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PhotoConfig {
    
    @Bean
    PhotoMapper profileMapper() {
        return PhotoMapper.INSTANCE;
    }
}
