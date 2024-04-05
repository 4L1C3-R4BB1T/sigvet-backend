package br.com.sigvet.api.application.mapper.base;

import br.com.sigvet.api.application.dto.cliente.AtualizarClienteDTO;
import br.com.sigvet.api.application.dto.cliente.CriarClienteDTO;
import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.infrastructure.entity.ClienteEntity;

public interface IClienteMapper extends IBaseMapper<Cliente, ClienteEntity, CriarClienteDTO, AtualizarClienteDTO> {
    
}
