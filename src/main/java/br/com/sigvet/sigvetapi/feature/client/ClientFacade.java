package br.com.sigvet.sigvetapi.feature.client;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.CreateUseCase;
import br.com.sigvet.sigvetapi.common.CrudFacade;
import br.com.sigvet.sigvetapi.common.DeleteUseCase;
import br.com.sigvet.sigvetapi.common.FindAllUseCase;
import br.com.sigvet.sigvetapi.common.FindByIdUseCase;
import br.com.sigvet.sigvetapi.common.UpdateUseCase;
import br.com.sigvet.sigvetapi.common.entities.ClientEntity;

@Component
public class ClientFacade extends CrudFacade<ClientEntity> {

    public ClientFacade(CreateUseCase<ClientEntity> createUseCase, DeleteUseCase<ClientEntity> deleteUseCase,
            UpdateUseCase<ClientEntity> updateUseCase, FindAllUseCase<ClientEntity> findAllUseCase,
            FindByIdUseCase<ClientEntity> findByIdUseCase) {
        super(createUseCase, deleteUseCase, updateUseCase, findAllUseCase, findByIdUseCase);
    }


}
