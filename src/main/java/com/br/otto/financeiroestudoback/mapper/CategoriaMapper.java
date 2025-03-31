package com.br.otto.financeiroestudoback.mapper;

import com.br.otto.financeiroestudoback.dto.CategoriaDTO;
import com.br.otto.financeiroestudoback.model.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoriaMapper extends GenericMapper<CategoriaDTO, Categoria> {
}
