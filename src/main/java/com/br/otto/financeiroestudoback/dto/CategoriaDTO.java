package com.br.otto.financeiroestudoback.dto;

import com.br.otto.financeiroestudoback.enumaration.TipoCategoria;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CategoriaDTO {
    private UUID id;
    private String nome;
    private TipoCategoria tipo;
}
