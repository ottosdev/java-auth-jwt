package com.br.otto.financeiroestudoback.dto.cliente;

import com.br.otto.financeiroestudoback.dto.EnderecoDTO;

import java.util.UUID;

public record AtualizarClienteDTO(String nome, String cpf, String email, String telefone, UUID id, EnderecoDTO endereco) {
}
