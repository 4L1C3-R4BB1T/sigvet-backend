package br.com.sigvet.sigvetapi.feature.vaccination;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.CrudFacade;
import br.com.sigvet.sigvetapi.common.entities.VaccinationEntity;
import br.com.sigvet.sigvetapi.common.usecases.CreateUseCase;
import br.com.sigvet.sigvetapi.common.usecases.DeleteUseCase;
import br.com.sigvet.sigvetapi.common.usecases.FindAllUseCase;
import br.com.sigvet.sigvetapi.common.usecases.FindByIdUseCase;
import br.com.sigvet.sigvetapi.common.usecases.UpdateUseCase;

@Component
public class VaccinationFacade extends CrudFacade<VaccinationEntity> {

    public VaccinationFacade(CreateUseCase<VaccinationEntity> createUseCase, DeleteUseCase<VaccinationEntity> deleteUseCase,
            UpdateUseCase<VaccinationEntity> updateUseCase, FindAllUseCase<VaccinationEntity> findAllUseCase,
            FindByIdUseCase<VaccinationEntity> findByIdUseCase) {
        super(createUseCase, deleteUseCase, updateUseCase, findAllUseCase, findByIdUseCase);
    }

}
