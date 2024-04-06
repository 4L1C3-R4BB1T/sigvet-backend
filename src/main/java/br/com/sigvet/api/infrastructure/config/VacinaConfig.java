package br.com.sigvet.api.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.sigvet.api.application.gateway.impl.VacinaGateway;
import br.com.sigvet.api.application.mapper.vacina.VacinaDTOMapper;
import br.com.sigvet.api.application.usecase.impl.vacina.AtualizarVacinaUseCase;
import br.com.sigvet.api.application.usecase.impl.vacina.CadastrarVacinaUseCase;
import br.com.sigvet.api.application.usecase.impl.vacina.DeletarVacinaUseCase;
import br.com.sigvet.api.application.usecase.impl.vacina.ListarVacinasUseCase;
import br.com.sigvet.api.application.usecase.impl.vacina.ObterVacinaPorIdUseCase;
import br.com.sigvet.api.core.domain.entities.Vacina;
import br.com.sigvet.api.usecase.base.IAtualizarUseCase;
import br.com.sigvet.api.usecase.base.ICadastrarUseCase;
import br.com.sigvet.api.usecase.base.IDeletarUseCase;
import br.com.sigvet.api.usecase.base.IListarUseCase;
import br.com.sigvet.api.usecase.base.IObterPorIdUseCase;

@Configuration
public class VacinaConfig {
    
    @Bean
    VacinaDTOMapper vacinaDTOMapper() {
        return VacinaDTOMapper.MAPPER;
    }

    @Bean
    IListarUseCase<Vacina> listarVacinasUseCase(VacinaGateway vacinaGateway) {
        return new ListarVacinasUseCase(vacinaGateway);
    }

    @Bean
    ICadastrarUseCase<Vacina> cadastrarVacinaUseCase(VacinaGateway vacinaGateway) {
        return new CadastrarVacinaUseCase(vacinaGateway);
    }

    @Bean
    IObterPorIdUseCase<Vacina> obterVacinaPorIdUseCase(VacinaGateway vacinaGateway) {
        return new ObterVacinaPorIdUseCase(vacinaGateway);
    }

    @Bean 
    IDeletarUseCase<Vacina> deletarVacinaUseCase(VacinaGateway vacinaGateway) {
        return new DeletarVacinaUseCase(vacinaGateway);
    }

    @Bean
    IAtualizarUseCase<Vacina> atualizarVacinaUseCase(VacinaGateway vacinaGateway) {
        return new AtualizarVacinaUseCase(vacinaGateway);
    }
}
