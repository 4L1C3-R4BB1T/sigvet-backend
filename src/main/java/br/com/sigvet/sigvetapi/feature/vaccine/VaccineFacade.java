package br.com.sigvet.sigvetapi.feature.vaccine;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.CrudFacade;
import br.com.sigvet.sigvetapi.common.entities.VaccineEntity;
import br.com.sigvet.sigvetapi.common.usecases.CreateUseCase;
import br.com.sigvet.sigvetapi.common.usecases.DeleteUseCase;
import br.com.sigvet.sigvetapi.common.usecases.FindAllUseCase;
import br.com.sigvet.sigvetapi.common.usecases.FindByIdUseCase;
import br.com.sigvet.sigvetapi.common.usecases.UpdateUseCase;

@Component
public class VaccineFacade extends CrudFacade<VaccineEntity> {

    public VaccineFacade(CreateUseCase<VaccineEntity> createUseCase, DeleteUseCase<VaccineEntity> deleteUseCase,
            UpdateUseCase<VaccineEntity> updateUseCase, FindAllUseCase<VaccineEntity> findAllUseCase,
            FindByIdUseCase<VaccineEntity> findByIdUseCase) {
        super(createUseCase, deleteUseCase, updateUseCase, findAllUseCase, findByIdUseCase);
    }

}
