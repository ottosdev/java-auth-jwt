package com.br.otto.financeiroestudoback.dto.transacao;

import java.math.BigDecimal;

public record TotaisDTO(
        BigDecimal receitas,
        BigDecimal despesas
) {
}
