package com.br.otto.financeiroestudoback.dto.transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransacaoRequestDTO(
        String nome,
        UUID categoriaId,
        UUID clienteId,
        BigDecimal valor,
        LocalDateTime dataTransacao
) {
}
