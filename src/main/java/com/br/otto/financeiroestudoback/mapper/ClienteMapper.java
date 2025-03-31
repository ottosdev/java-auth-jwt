package com.br.otto.financeiroestudoback.mapper;

import com.br.otto.financeiroestudoback.dto.ClienteDTO;
import com.br.otto.financeiroestudoback.model.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {EnderecoMapper.class})
public interface ClienteMapper extends GenericMapper<ClienteDTO, Cliente>{

}
