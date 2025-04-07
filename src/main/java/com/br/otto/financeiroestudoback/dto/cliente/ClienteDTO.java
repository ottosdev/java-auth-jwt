package com.br.otto.financeiroestudoback.dto.cliente;
import com.br.otto.financeiroestudoback.dto.EnderecoDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
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
    private LocalDateTime ultimoAcesso;
    private EnderecoDTO endereco;
}
