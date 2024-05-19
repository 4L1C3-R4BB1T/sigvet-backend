package br.com.sigvet.sigvetapi.feature.client.usecases;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.entities.ClientEntity;
import br.com.sigvet.sigvetapi.feature.client.ClientRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SearchClientByNameUseCase {

    private final ClientRepository clientRepository;
    
    public List<ClientEntity> execute(String name) {
        return clientRepository.searchByName(name);
    }
}
