package br.com.sigvet.api.gateway;

import br.com.sigvet.api.core.domain.entities.Animal;
import br.com.sigvet.api.gateway.base.IBaseGateway;
import br.com.sigvet.api.infrastructure.entity.AnimalEntity;

public interface IAnimalGateway extends IBaseGateway<Animal, AnimalEntity> {
    
}
