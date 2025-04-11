package com.br.otto.financeiroestudoback.dto.transacao;

import com.br.otto.financeiroestudoback.dto.CategoriaDTO;
import com.br.otto.financeiroestudoback.dto.cliente.ClienteDTO;
import com.br.otto.financeiroestudoback.model.Categoria;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransacoResponseDTO(
        UUID id,
        String nome,
        CategoriaDTO categoria,
        ClienteDTO cliente,
        BigDecimal valor,
        LocalDateTime dataTransacao
) {
}
