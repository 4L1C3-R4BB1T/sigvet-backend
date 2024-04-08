package br.com.sigvet.api.controller.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MapperManager<DomainMapper, DTOMapper>  {

    private final DomainMapper mapper;

    private final DTOMapper DTOMapper;
}
