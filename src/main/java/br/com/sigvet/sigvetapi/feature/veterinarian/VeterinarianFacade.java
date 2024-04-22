package br.com.sigvet.sigvetapi.feature.veterinarian;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.CrudFacade;
import br.com.sigvet.sigvetapi.common.entities.VeterinarianEntity;
import br.com.sigvet.sigvetapi.common.usecases.CreateUseCase;
import br.com.sigvet.sigvetapi.common.usecases.DeleteUseCase;
import br.com.sigvet.sigvetapi.common.usecases.FindAllUseCase;
import br.com.sigvet.sigvetapi.common.usecases.FindByIdUseCase;
import br.com.sigvet.sigvetapi.common.usecases.UpdateUseCase;

@Component
public class VeterinarianFacade extends CrudFacade<VeterinarianEntity> {

    public VeterinarianFacade(CreateUseCase<VeterinarianEntity> createUseCase, DeleteUseCase<VeterinarianEntity> deleteUseCase,
            UpdateUseCase<VeterinarianEntity> updateUseCase, FindAllUseCase<VeterinarianEntity> findAllUseCase,
            FindByIdUseCase<VeterinarianEntity> findByIdUseCase) {
        super(createUseCase, deleteUseCase, updateUseCase, findAllUseCase, findByIdUseCase);
    }

}
