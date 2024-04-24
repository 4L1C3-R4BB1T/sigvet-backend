package br.com.sigvet.sigvetapi.feature.diagnostic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiagnosticConfig {

    @Bean
    DiagnosticMapper diagnosticMapper() {
        return DiagnosticMapper.INSTANCE;
    }

}
