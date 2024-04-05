package br.com.sigvet.api.application.mapper.base;

import br.com.sigvet.api.application.dto.veterinario.AtualizarVeterinarioDTO;
import br.com.sigvet.api.application.dto.veterinario.CriarVeterinarioDTO;
import br.com.sigvet.api.core.domain.entities.Veterinario;
import br.com.sigvet.api.infrastructure.entity.VeterinarioEntity;

public interface IVeterinarioMapper extends IBaseMapper<Veterinario, VeterinarioEntity, CriarVeterinarioDTO, AtualizarVeterinarioDTO> {
    
}
