package br.com.sigvet.api.application.mapper.base;

import br.com.sigvet.api.application.dto.animal.UpdateAnimalRequestDTO;
import br.com.sigvet.api.application.dto.animal.CreateAnimalRequestDTO;
import br.com.sigvet.api.core.domain.entities.Animal;
import br.com.sigvet.api.infrastructure.entity.AnimalEntity;

public interface IAnimalMapper extends IBaseMapper<Animal, AnimalEntity, CreateAnimalRequestDTO, UpdateAnimalRequestDTO> {

}
