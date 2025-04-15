package com.br.otto.financeiroestudoback.controller;

import com.br.otto.financeiroestudoback.dto.transacao.TransacaoRequestDTO;
import com.br.otto.financeiroestudoback.dto.transacao.TransacaoResposeDTO;
import com.br.otto.financeiroestudoback.dto.transacao.TransacoResponseDTO;
import com.br.otto.financeiroestudoback.service.TransacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping(value = "/transacao")
@RestController
@RequiredArgsConstructor
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<TransacoResponseDTO> salvarTransacao(@RequestBody @Valid TransacaoRequestDTO dto) {
        return ResponseEntity.ok(transacaoService.salvar(dto));
    }
    
    @GetMapping("/{clienteId}")
    public ResponseEntity<TransacaoResposeDTO> listarTransacoes(@PathVariable("clienteId") UUID clienteId) {
        return ResponseEntity.ok(transacaoService.listarTudo(clienteId));
    }

    @DeleteMapping("/{transacaoId}")
    public void deletarTransacao(@PathVariable("transacaoId") UUID id) {
        transacaoService.deletarTransacao(id);
    }
}
