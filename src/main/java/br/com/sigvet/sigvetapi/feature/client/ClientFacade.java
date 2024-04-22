package br.com.sigvet.sigvetapi.feature.client;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.CrudFacade;
import br.com.sigvet.sigvetapi.common.entities.ClientEntity;
import br.com.sigvet.sigvetapi.common.usecases.CreateUseCase;
import br.com.sigvet.sigvetapi.common.usecases.DeleteUseCase;
import br.com.sigvet.sigvetapi.common.usecases.FindAllUseCase;
import br.com.sigvet.sigvetapi.common.usecases.FindByIdUseCase;
import br.com.sigvet.sigvetapi.common.usecases.UpdateUseCase;

@Component
public class ClientFacade extends CrudFacade<ClientEntity> {

    public ClientFacade(CreateUseCase<ClientEntity> createUseCase, DeleteUseCase<ClientEntity> deleteUseCase,
            UpdateUseCase<ClientEntity> updateUseCase, FindAllUseCase<ClientEntity> findAllUseCase,
            FindByIdUseCase<ClientEntity> findByIdUseCase) {
        super(createUseCase, deleteUseCase, updateUseCase, findAllUseCase, findByIdUseCase);
    }

}
