package com.br.otto.financeiroestudoback.repository;

import com.br.otto.financeiroestudoback.dto.transacao.TotaisDTO;
import com.br.otto.financeiroestudoback.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TransacaoRepository extends JpaRepository<Transacao, UUID> {
    List<Transacao> findAllByClienteId(UUID id);

    @Query("""
    SELECT new com.br.otto.financeiroestudoback.dto.transacao.TotaisDTO(
        CAST(SUM(CASE WHEN c.tipo = 'RECEITA' THEN t.valor ELSE 0 END) AS BigDecimal),
        CAST(SUM(CASE WHEN c.tipo = 'DESPESA' THEN t.valor ELSE 0 END) AS BigDecimal)
    )
    FROM Transacao t
    JOIN t.categoria c
    WHERE t.cliente.id = :clienteId
""")
    TotaisDTO calcularTotais(@Param("clienteId") UUID clienteId);

}
