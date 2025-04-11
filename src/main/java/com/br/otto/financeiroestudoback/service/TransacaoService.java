package com.br.otto.financeiroestudoback.service;

import com.br.otto.financeiroestudoback.dto.transacao.TotaisDTO;
import com.br.otto.financeiroestudoback.dto.transacao.TransacaoRequestDTO;
import com.br.otto.financeiroestudoback.dto.transacao.TransacaoResposeDTO;
import com.br.otto.financeiroestudoback.dto.transacao.TransacoResponseDTO;
import com.br.otto.financeiroestudoback.mapper.TransacaoMapper;
import com.br.otto.financeiroestudoback.mapper.TransacaoResponseMapper;
import com.br.otto.financeiroestudoback.model.Categoria;
import com.br.otto.financeiroestudoback.model.Cliente;
import com.br.otto.financeiroestudoback.model.Transacao;
import com.br.otto.financeiroestudoback.repository.CategoriaRepository;
import com.br.otto.financeiroestudoback.repository.ClienteRepository;
import com.br.otto.financeiroestudoback.repository.TransacaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final ClienteRepository clienteRepository;
    private final CategoriaRepository categoriaRepository;
    private final TransacaoMapper transacaoMapper;
    private final TransacaoResponseMapper transacaoResponseMapper;

    @Transactional
    public TransacoResponseDTO salvar(TransacaoRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado"));

        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));

        var transacao = transacaoMapper.toEntity(dto);
        transacao.setCliente(cliente);
        transacao.setCategoria(categoria);
        transacaoRepository.save(transacao);

        return transacaoResponseMapper.toDTO(transacao);
    }

    public TransacaoResposeDTO listarTudo(UUID clienteId) {
        List<Transacao> transacoes = transacaoRepository.findAllByClienteId(clienteId);
        List<TransacoResponseDTO> dtos = transacoes.stream()
                .map(transacaoResponseMapper::toDTO)
                .toList();

        TotaisDTO receitasDespesas = transacaoRepository.calcularTotais(clienteId);

        BigDecimal receitas = Optional.ofNullable(receitasDespesas.receitas()).orElse(BigDecimal.ZERO);
        BigDecimal despesas = Optional.ofNullable(receitasDespesas.despesas()).orElse(BigDecimal.ZERO);

        BigDecimal total = receitas.subtract(despesas);

        return new TransacaoResposeDTO(dtos, receitas, despesas, total);
    }


    public void deletarTransacao(UUID id) {
        Transacao transacao = transacaoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Transação não encontrada"));
        transacaoRepository.delete(transacao);
    }
}
