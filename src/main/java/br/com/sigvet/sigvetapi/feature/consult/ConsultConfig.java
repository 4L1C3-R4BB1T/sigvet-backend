package br.com.sigvet.sigvetapi.feature.consult;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsultConfig {

    @Bean
    ConsultMapper consultMapper() {
        return ConsultMapper.INSTANCE;
    }

}
