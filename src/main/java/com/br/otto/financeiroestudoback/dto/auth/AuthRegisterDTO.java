package com.br.otto.financeiroestudoback.dto.auth;

public record AuthRegisterDTO(
        String nome,
        String senha,
        String email,
        String telefone,
        String cpf
) {
}
