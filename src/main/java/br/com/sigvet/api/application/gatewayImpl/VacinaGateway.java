package br.com.sigvet.api.application.gatewayImpl;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.sigvet.api.application.builder.EntitySpecification;
import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.application.exception.UsuarioNaoEncontradoException;
import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.core.domain.entities.Vacina;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IVacinaGateway;
import br.com.sigvet.api.infrastructure.entity.VacinaEntity;
import br.com.sigvet.api.infrastructure.repository.VacinaJpaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VacinaGateway implements IVacinaGateway {

    private final VacinaJpaRepository vacinaJpaRepository;
 
    @Override
    public Vacina save(Vacina record) throws DomainInvalidException, UsuarioExistenteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Vacina findById(Long id) throws DomainInvalidException, UsuarioNaoEncontradoException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Page<Vacina> findAll(FilterModel filter) throws DomainInvalidException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Vacina update(Long id, Vacina source)
            throws UsuarioNaoEncontradoException, UsuarioExistenteException, DomainInvalidException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean delete(Long id) throws UsuarioExistenteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Specification<VacinaEntity> buildSpecification(FilterModel filterModel) {
        Specification<VacinaEntity> spec = Specification.where((root, query, cb) -> cb.equal(root.get("deleted"), false));

        for (var equalFilter : filterModel.getEqualFilters())
            spec = spec.and(EntitySpecification.equal(equalFilter, VacinaEntity.class));

        for (var inFilter : filterModel.getInFilters())
            spec = spec.and(EntitySpecification.in(inFilter, VacinaEntity.class));

        return spec;
    }
    
}
