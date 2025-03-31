package com.br.otto.financeiroestudoback.mapper;

import com.br.otto.financeiroestudoback.dto.EnderecoDTO;
import com.br.otto.financeiroestudoback.model.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper extends GenericMapper<EnderecoDTO, Endereco> {
}
