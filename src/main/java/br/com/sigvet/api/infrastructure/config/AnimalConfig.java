package br.com.sigvet.api.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.sigvet.api.application.gateway.impl.AnimalGateway;
import br.com.sigvet.api.application.mapper.animal.AnimalDTOMapper;
import br.com.sigvet.api.application.mapper.base.IAnimalMapper;
import br.com.sigvet.api.application.usecase.impl.animal.AtualizarAnimalUseCase;
import br.com.sigvet.api.application.usecase.impl.animal.CadastrarAnimalUseCase;
import br.com.sigvet.api.application.usecase.impl.animal.DeletarAnimalUseCase;
import br.com.sigvet.api.application.usecase.impl.animal.ListarAnimaisUseCase;
import br.com.sigvet.api.application.usecase.impl.animal.ObterAnimalPorIdUseCase;
import br.com.sigvet.api.controller.base.DomainObjectUseCaseManager;
import br.com.sigvet.api.controller.base.MapperManager;
import br.com.sigvet.api.core.domain.entities.Animal;
import br.com.sigvet.api.usecase.base.IAtualizarUseCase;
import br.com.sigvet.api.usecase.base.ICadastrarUseCase;
import br.com.sigvet.api.usecase.base.IDeletarUseCase;
import br.com.sigvet.api.usecase.base.IListarUseCase;
import br.com.sigvet.api.usecase.base.IObterPorIdUseCase;

@Configuration
public class AnimalConfig {

    @Bean
    MapperManager<IAnimalMapper, AnimalDTOMapper> animalMapperManager(IAnimalMapper animalMapper, AnimalDTOMapper animalDTOMapper) {
        return new MapperManager<>(animalMapper, animalDTOMapper);
    }

    @Bean
    DomainObjectUseCaseManager<Animal> animalDomainObjectUseCaseManager(IListarUseCase<Animal> listarUseCase, ICadastrarUseCase<Animal> cadastrarUseCase, IAtualizarUseCase<Animal> atualizarUseCasem, IObterPorIdUseCase<Animal> obterPorIdUseCase, IDeletarUseCase<Animal> deletarUseCase) {
        return new DomainObjectUseCaseManager<>(cadastrarUseCase, listarUseCase, obterPorIdUseCase, atualizarUseCasem, deletarUseCase);
    }

    @Bean
    AnimalDTOMapper animalDTOMapper() {
        return AnimalDTOMapper.MAPPER;
    }

    @Bean
    IListarUseCase<Animal> listarAnimalsUseCase(AnimalGateway clienteGateway) {
        return new ListarAnimaisUseCase(clienteGateway);
    }

    @Bean
    ICadastrarUseCase<Animal> cadastrarAnimalUseCase(AnimalGateway clienteGateway) {
        return new CadastrarAnimalUseCase(clienteGateway);
    }

    @Bean
    IObterPorIdUseCase<Animal> obterAnimalPorIdUseCase(AnimalGateway clienteGateway) {
        return new ObterAnimalPorIdUseCase(clienteGateway);
    }

    @Bean 
    IDeletarUseCase<Animal> deletarAnimalUseCase(AnimalGateway clienteGateway) {
        return new DeletarAnimalUseCase(clienteGateway);
    }

    @Bean
    IAtualizarUseCase<Animal> atualizarAnimalUseCase(AnimalGateway clienteGateway) {
        return new AtualizarAnimalUseCase(clienteGateway);
    }

}
