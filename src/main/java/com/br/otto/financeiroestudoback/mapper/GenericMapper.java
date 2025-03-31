package com.br.otto.financeiroestudoback.mapper;

import com.br.otto.financeiroestudoback.dto.ClienteDTO;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface GenericMapper <D, E> {
    D toDTO(E e);

    E toEntity(D d);

    List<D> toDTO(List<E> es);

    List<E> toEntity(List<D> ds);

    @Mapping(target = "id", ignore = true)
    void atualizarEntidadeComDTO(D dto, @MappingTarget E entidade);

}

