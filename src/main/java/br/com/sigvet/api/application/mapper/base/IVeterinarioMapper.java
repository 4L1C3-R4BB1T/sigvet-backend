package br.com.sigvet.api.application.mapper.base;

import br.com.sigvet.api.application.dto.veterinario.RequestAtualizarVeterinarioDTO;
import br.com.sigvet.api.application.dto.veterinario.RequestCriarVeterinarioDTO;
import br.com.sigvet.api.core.domain.entities.Veterinario;
import br.com.sigvet.api.infrastructure.entity.VeterinarioEntity;

public interface IVeterinarioMapper extends IBaseMapper<Veterinario, VeterinarioEntity, RequestCriarVeterinarioDTO, RequestAtualizarVeterinarioDTO> {
    
}
