package br.com.sigvet.api.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.sigvet.api.application.gatewayImpl.ClienteGateway;
import br.com.sigvet.api.application.mapper.cliente.ClienteDTOMapper;
import br.com.sigvet.api.application.usecaseImpl.cliente.AtualizarClienteUseCase;
import br.com.sigvet.api.application.usecaseImpl.cliente.CadastrarClienteUseCase;
import br.com.sigvet.api.application.usecaseImpl.cliente.DeletarClienteUseCase;
import br.com.sigvet.api.application.usecaseImpl.cliente.ListarClientesUseCase;
import br.com.sigvet.api.application.usecaseImpl.cliente.ObterClientePorIdUseCase;
import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.usecase.base.IAtualizarUseCase;
import br.com.sigvet.api.usecase.base.ICadastrarUseCase;
import br.com.sigvet.api.usecase.base.IDeletarUseCase;
import br.com.sigvet.api.usecase.base.IListarUseCase;
import br.com.sigvet.api.usecase.base.IObterPorIdUseCase;
@Configuration
public class ClienteConfig {

    @Bean
    ClienteDTOMapper clienteDTOMapper() {
        return ClienteDTOMapper.MAPPER;
    }
    
    @Bean
    IListarUseCase<Cliente> listarClientesUseCase(ClienteGateway clienteGateway) {
        return new ListarClientesUseCase(clienteGateway);
    }

    @Bean
    ICadastrarUseCase<Cliente> cadastrarClienteUseCase(ClienteGateway clienteGateway) {
        return new CadastrarClienteUseCase(clienteGateway);
    }

    @Bean
    IObterPorIdUseCase<Cliente> obterClientePorIdUseCase(ClienteGateway clienteGateway) {
        return new ObterClientePorIdUseCase(clienteGateway);
    }

    @Bean 
    IDeletarUseCase<Cliente> deletarClienteUseCase(ClienteGateway clienteGateway) {
        return new DeletarClienteUseCase(clienteGateway);
    }

    @Bean
    IAtualizarUseCase<Cliente> atualizarClienteUseCase(ClienteGateway clienteGateway) {
        return new AtualizarClienteUseCase(clienteGateway);
    }
    
}
