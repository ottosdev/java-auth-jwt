package com.br.otto.financeiroestudoback.dto.transacao;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransacaoRequestDTO(
        @NotNull(message = "O nome é obrigatório")
        @NotBlank(message = "O nome é obrigatório") String nome,
        @NotNull(message = "O ID da categoria é obrigatório") UUID categoriaId,
        @NotNull(message = "O ID do cliente é obrigatório") UUID clienteId,
        @NotNull(message = "O valor é obrigatório")
        @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero") BigDecimal valor,
        @NotNull(message = "A data da transação é obrigatória") LocalDateTime dataTransacao
) {}