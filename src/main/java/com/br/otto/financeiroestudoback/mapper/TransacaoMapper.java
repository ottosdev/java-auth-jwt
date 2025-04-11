package com.br.otto.financeiroestudoback.mapper;

import com.br.otto.financeiroestudoback.dto.transacao.TransacaoRequestDTO;
import com.br.otto.financeiroestudoback.model.Transacao;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {CategoriaMapper.class, ClienteMapper.class})
public interface TransacaoMapper extends GenericMapper<TransacaoRequestDTO, Transacao> {
}
