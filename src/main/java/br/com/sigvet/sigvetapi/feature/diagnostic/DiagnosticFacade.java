package br.com.sigvet.sigvetapi.feature.diagnostic;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.CrudFacade;
import br.com.sigvet.sigvetapi.common.entities.DiagnosticEntity;
import br.com.sigvet.sigvetapi.common.usecases.CreateUseCase;
import br.com.sigvet.sigvetapi.common.usecases.DeleteUseCase;
import br.com.sigvet.sigvetapi.common.usecases.FindAllUseCase;
import br.com.sigvet.sigvetapi.common.usecases.FindByIdUseCase;
import br.com.sigvet.sigvetapi.common.usecases.UpdateUseCase;

@Component
public class DiagnosticFacade extends CrudFacade<DiagnosticEntity> {

    public DiagnosticFacade(CreateUseCase<DiagnosticEntity> createUseCase, DeleteUseCase<DiagnosticEntity> deleteUseCase,
            UpdateUseCase<DiagnosticEntity> updateUseCase, FindAllUseCase<DiagnosticEntity> findAllUseCase,
            FindByIdUseCase<DiagnosticEntity> findByIdUseCase) {
        super(createUseCase, deleteUseCase, updateUseCase, findAllUseCase, findByIdUseCase);
    }

}
