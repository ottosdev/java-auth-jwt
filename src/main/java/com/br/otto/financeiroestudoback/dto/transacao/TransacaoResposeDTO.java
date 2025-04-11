package com.br.otto.financeiroestudoback.dto.transacao;

import java.math.BigDecimal;
import java.util.List;

public record TransacaoResposeDTO(
        List<TransacoResponseDTO> transacoes,
        BigDecimal receitas,
        BigDecimal despesas,
        BigDecimal total
) {
}
