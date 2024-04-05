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
import br.com.sigvet.api.core.domain.entities.Veterinario;
import br.com.sigvet.api.usecase.base.IAtualizarUseCase;
import br.com.sigvet.api.usecase.base.ICadastrarUseCase;
import br.com.sigvet.api.usecase.base.IDeletarUseCase;
import br.com.sigvet.api.usecase.base.IListarUseCase;
import br.com.sigvet.api.usecase.base.IObterPorIdUseCase;


@Configuration
public class VeterinarioConfig {
    
     @Bean
    VeterinarioDTOMapper veterinarioDTOMapper() {
        return VeterinarioDTOMapper.MAPPER;
    }
    
    @Bean
    IListarUseCase<Veterinario> listarVeterinariosUseCase(VeterinarioGateway clienteGateway) {
        return new ListarVeterinariosUseCase(clienteGateway);
    }

    @Bean
    ICadastrarUseCase<Veterinario> cadastrarVeterinarioUseCase(VeterinarioGateway clienteGateway) {
        return new CadastrarVeterinarioUseCase(clienteGateway);
    }

    @Bean
    IObterPorIdUseCase<Veterinario> obterVeterinarioPorIdUseCase(VeterinarioGateway clienteGateway) {
        return new ObterVeterinarioPorIdUseCase(clienteGateway);
    }

    @Bean 
    IDeletarUseCase<Veterinario> deletarVeterinarioUseCase(VeterinarioGateway clienteGateway) {
        return new DeletarVeterinarioUseCase(clienteGateway);
    }

    @Bean
    IAtualizarUseCase<Veterinario> atualizarVeterinarioUseCase(VeterinarioGateway clienteGateway) {
        return new AtualizarVeterinarioUseCase(clienteGateway);
    }
}
