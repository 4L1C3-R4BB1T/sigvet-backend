package br.com.sigvet.api.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.sigvet.api.application.gatewayImpl.VeterinarioGateway;
import br.com.sigvet.api.application.mapper.veterinario.VeterinarioDTOMapper;
import br.com.sigvet.api.application.usecaseImpl.veterinario.AtualizarVeterinarioUseCase;
import br.com.sigvet.api.application.usecaseImpl.veterinario.CadastrarVeterinarioUseCase;
import br.com.sigvet.api.application.usecaseImpl.veterinario.DeletarVeterinarioUseCase;
import br.com.sigvet.api.application.usecaseImpl.veterinario.ListarVeterinariosUseCase;
import br.com.sigvet.api.application.usecaseImpl.veterinario.ObterVeterinarioPorIdUseCase;
import br.com.sigvet.api.usecase.veterinario.IAtualizarVeterinarioUseCase;
import br.com.sigvet.api.usecase.veterinario.ICadastrarVeterinarioUseCase;
import br.com.sigvet.api.usecase.veterinario.IDeletarVeterinarioUseCase;
import br.com.sigvet.api.usecase.veterinario.IListarVeterinariosUseCase;
import br.com.sigvet.api.usecase.veterinario.IObterVeterinarioPorIdUseCase;

@Configuration
public class VeterinarioConfig {
    
     @Bean
    VeterinarioDTOMapper veterinarioDTOMapper() {
        return VeterinarioDTOMapper.MAPPER;
    }
    
    @Bean
    IListarVeterinariosUseCase listarVeterinariosUseCase(VeterinarioGateway veterinarioGateway) {
        return new ListarVeterinariosUseCase(veterinarioGateway);
    }

    @Bean
    ICadastrarVeterinarioUseCase cadastrarVeterinarioUseCase(VeterinarioGateway veterinarioGateway) {
        return new CadastrarVeterinarioUseCase(veterinarioGateway);
    }

    @Bean
    IObterVeterinarioPorIdUseCase obterVeterinarioPorIdUseCase(VeterinarioGateway veterinarioGateway) {
        return new ObterVeterinarioPorIdUseCase(veterinarioGateway);
    }

    @Bean 
    IDeletarVeterinarioUseCase deletarVeterinarioUseCase(VeterinarioGateway veterinarioGateway) {
        return new DeletarVeterinarioUseCase(veterinarioGateway);
    }

    @Bean
    IAtualizarVeterinarioUseCase atualizarVeterinarioUseCase(VeterinarioGateway veterinarioGateway) {
        return new AtualizarVeterinarioUseCase(veterinarioGateway);
    }
}
