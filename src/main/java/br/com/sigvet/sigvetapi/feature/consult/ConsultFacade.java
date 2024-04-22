package br.com.sigvet.sigvetapi.feature.consult;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.CrudFacade;
import br.com.sigvet.sigvetapi.common.entities.ConsultEntity;
import br.com.sigvet.sigvetapi.common.usecases.CreateUseCase;
import br.com.sigvet.sigvetapi.common.usecases.DeleteUseCase;
import br.com.sigvet.sigvetapi.common.usecases.FindAllUseCase;
import br.com.sigvet.sigvetapi.common.usecases.FindByIdUseCase;
import br.com.sigvet.sigvetapi.common.usecases.UpdateUseCase;

@Component
public class ConsultFacade extends CrudFacade<ConsultEntity> {

    public ConsultFacade(CreateUseCase<ConsultEntity> createUseCase, DeleteUseCase<ConsultEntity> deleteUseCase,
            UpdateUseCase<ConsultEntity> updateUseCase, FindAllUseCase<ConsultEntity> findAllUseCase,
            FindByIdUseCase<ConsultEntity> findByIdUseCase) {
        super(createUseCase, deleteUseCase, updateUseCase, findAllUseCase, findByIdUseCase);
    }

}
