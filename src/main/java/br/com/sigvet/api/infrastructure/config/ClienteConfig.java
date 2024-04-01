package br.com.sigvet.api.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.sigvet.api.application.gatewayImpl.ClienteGateway;
import br.com.sigvet.api.application.mapper.ClienteDTOMapper;
import br.com.sigvet.api.application.usecaseImpl.CadastrarClienteUseCase;
import br.com.sigvet.api.application.usecaseImpl.ListarClientesUseCase;
import br.com.sigvet.api.usecase.cliente.ICadastrarClienteUseCase;
import br.com.sigvet.api.usecase.cliente.IListarClientesUseCase;

@Configuration
public class ClienteConfig {


    @Bean
    ClienteDTOMapper clienteDTOMapper() {
        return ClienteDTOMapper.MAPPER;
    }
    
    @Bean
    IListarClientesUseCase listarClientesUseCase(ClienteGateway clienteGateway) {
        return new ListarClientesUseCase(clienteGateway);
    }

    @Bean
    ICadastrarClienteUseCase cadastrarClienteUseCase(ClienteGateway clienteGateway) {
        return new CadastrarClienteUseCase(clienteGateway);
    }
}
