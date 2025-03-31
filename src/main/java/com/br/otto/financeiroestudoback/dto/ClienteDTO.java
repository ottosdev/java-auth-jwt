package com.br.otto.financeiroestudoback.dto;

import com.br.otto.financeiroestudoback.model.Endereco;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ClienteDTO {
    private UUID id;
    private String nome;
    private String senha;
    private String cpf;
    private String email;
    private String telefone;
    private EnderecoDTO endereco;
}
