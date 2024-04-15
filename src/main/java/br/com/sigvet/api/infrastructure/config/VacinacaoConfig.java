package br.com.sigvet.api.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.sigvet.api.application.gateway.impl.VacinacaoGateway;
import br.com.sigvet.api.application.mapper.base.IVacinacaoMapper;
import br.com.sigvet.api.application.mapper.vacinacao.VacinacaoDTOMapper;
import br.com.sigvet.api.application.usecase.impl.vacinacao.AgendarVacinacaoUseCase;
import br.com.sigvet.api.application.usecase.impl.vacinacao.AtualizarVacinacaoUseCase;
import br.com.sigvet.api.application.usecase.impl.vacinacao.DeletarVacinacaoUseCase;
import br.com.sigvet.api.application.usecase.impl.vacinacao.ListarVacinacoesUseCase;
import br.com.sigvet.api.application.usecase.impl.vacinacao.ObterVacinacaoPorIdUseCase;
import br.com.sigvet.api.controller.base.DomainObjectUseCaseManager;
import br.com.sigvet.api.controller.base.MapperManager;
import br.com.sigvet.api.core.domain.entities.Vacinacao;
import br.com.sigvet.api.usecase.base.IAtualizarUseCase;
import br.com.sigvet.api.usecase.base.ICadastrarUseCase;
import br.com.sigvet.api.usecase.base.IDeletarUseCase;
import br.com.sigvet.api.usecase.base.IListarUseCase;
import br.com.sigvet.api.usecase.base.IObterPorIdUseCase;

@Configuration
public class VacinacaoConfig {
    
    @Bean
    MapperManager<IVacinacaoMapper, VacinacaoDTOMapper> vacinacaoMapperManager(IVacinacaoMapper vacinacaoMapper, VacinacaoDTOMapper vacinacaoDTOMapper) {
        return new MapperManager<>(vacinacaoMapper, vacinacaoDTOMapper);
    }
    
    @Bean
    VacinacaoDTOMapper vacinacaoDTOMapper() {
        return VacinacaoDTOMapper.MAPPER;
    }

    @Bean
    DomainObjectUseCaseManager<Vacinacao> vacinacaoDomainObjectUseCaseManager(IListarUseCase<Vacinacao> listarUseCase, ICadastrarUseCase<Vacinacao> cadastrarUseCase, IAtualizarUseCase<Vacinacao> atualizarUseCase, IObterPorIdUseCase<Vacinacao> obterPorIdUseCase, IDeletarUseCase<Vacinacao> deletarUseCase) {
        return new DomainObjectUseCaseManager<>(cadastrarUseCase, listarUseCase, obterPorIdUseCase, atualizarUseCase, deletarUseCase);
    }
    
    @Bean
    IListarUseCase<Vacinacao> listarVacinacaosUseCase(VacinacaoGateway vacinacaoGateway) {
        return new ListarVacinacoesUseCase(vacinacaoGateway);
    }

    @Bean
    ICadastrarUseCase<Vacinacao> cadastrarVacinacaoUseCase(VacinacaoGateway vacinacaoGateway) {
        return new AgendarVacinacaoUseCase(vacinacaoGateway);
    }

    @Bean
    IObterPorIdUseCase<Vacinacao> obterVacinacaoPorIdUseCase(VacinacaoGateway vacinacaoGateway) {
        return new ObterVacinacaoPorIdUseCase();
    }

    @Bean 
    IDeletarUseCase<Vacinacao> deletarVacinacaoUseCase(VacinacaoGateway vacinacaoGateway) {
        return new DeletarVacinacaoUseCase(vacinacaoGateway);
    } 

    @Bean
    IAtualizarUseCase<Vacinacao> atualizarVacinacaoUseCase() {
        return new AtualizarVacinacaoUseCase();
    }


}
