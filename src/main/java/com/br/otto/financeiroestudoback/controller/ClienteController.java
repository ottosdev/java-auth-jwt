package com.br.otto.financeiroestudoback.controller;

import com.br.otto.financeiroestudoback.dto.cliente.AtualizarClienteDTO;
import com.br.otto.financeiroestudoback.dto.cliente.ClienteDTO;
import com.br.otto.financeiroestudoback.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        return ResponseEntity.ok(clienteService.listarClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarCliente(@PathVariable UUID id) {
        return ResponseEntity.ok(clienteService.buscarCliente(id));
    }

    @PutMapping
    public ResponseEntity<AtualizarClienteDTO> atualizarCliente(@RequestBody AtualizarClienteDTO clienteDTO) {
            return ResponseEntity.ok(clienteService.atualizarCliente(clienteDTO));
    }
}
