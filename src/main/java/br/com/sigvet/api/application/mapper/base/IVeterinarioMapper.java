package br.com.sigvet.api.application.mapper.base;

import br.com.sigvet.api.application.dto.veterinario.UpdateVeterinarianRequestDTO;
import br.com.sigvet.api.application.dto.veterinario.CreateVeterinarianRequestDTO;
import br.com.sigvet.api.core.domain.entities.Veterinario;
import br.com.sigvet.api.infrastructure.entity.VeterinarioEntity;

public interface IVeterinarioMapper extends IBaseMapper<Veterinario, VeterinarioEntity, CreateVeterinarianRequestDTO, UpdateVeterinarianRequestDTO> {
    
}
