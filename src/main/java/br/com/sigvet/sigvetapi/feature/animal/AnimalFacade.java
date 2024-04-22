package br.com.sigvet.sigvetapi.feature.animal;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.CrudFacade;
import br.com.sigvet.sigvetapi.common.entities.AnimalEntity;
import br.com.sigvet.sigvetapi.common.usecases.CreateUseCase;
import br.com.sigvet.sigvetapi.common.usecases.DeleteUseCase;
import br.com.sigvet.sigvetapi.common.usecases.FindAllUseCase;
import br.com.sigvet.sigvetapi.common.usecases.FindByIdUseCase;
import br.com.sigvet.sigvetapi.common.usecases.UpdateUseCase;

@Component
public class AnimalFacade extends CrudFacade<AnimalEntity> {

    public AnimalFacade(CreateUseCase<AnimalEntity> createUseCase, DeleteUseCase<AnimalEntity> deleteUseCase,
            UpdateUseCase<AnimalEntity> updateUseCase, FindAllUseCase<AnimalEntity> findAllUseCase,
            FindByIdUseCase<AnimalEntity> findByIdUseCase) {
        super(createUseCase, deleteUseCase, updateUseCase, findAllUseCase, findByIdUseCase);
    }


}
