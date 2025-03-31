package com.br.otto.financeiroestudoback.service;

import com.br.otto.financeiroestudoback.dto.ClienteDTO;
import com.br.otto.financeiroestudoback.mapper.ClienteMapper;
import com.br.otto.financeiroestudoback.mapper.EnderecoMapper;
import com.br.otto.financeiroestudoback.model.Cliente;
import com.br.otto.financeiroestudoback.model.Endereco;
import com.br.otto.financeiroestudoback.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteMapper clienteMapper;
    private final ClienteRepository clienteRepository;
    private final EnderecoMapper enderecoMapper;

    @Transactional
    public ClienteDTO salvarCliente(ClienteDTO clienteDTO) {
        Optional<Cliente> clienteOptional = clienteRepository.findByEmail(clienteDTO.getEmail());

        if (clienteOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail já cadastrado.");
        }

        Cliente cliente = clienteMapper.toEntity(clienteDTO);

        if (Objects.nonNull(clienteDTO.getEndereco())) {
            Endereco endereco = enderecoMapper.toEntity(clienteDTO.getEndereco());
            endereco.setCliente(cliente);
            cliente.setEndereco(endereco);
        }

        cliente = clienteRepository.save(cliente);
        return clienteMapper.toDTO(cliente);
    }

    public List<ClienteDTO> listarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(clienteMapper::toDTO).toList();
    }

    public ClienteDTO buscarClientePorId(UUID id) {
        Cliente cliente = clienteRepository.getClienteById(id);
        return clienteMapper.toDTO(cliente);
    }

    @Transactional
    public ClienteDTO atualizarCliente(ClienteDTO clienteDTO) {
        Cliente clienteExistente = clienteRepository.findByEmail(clienteDTO.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não existe!"));

        if (clienteExistente.getEndereco() != null) {
            clienteExistente.getEndereco().setCliente(clienteExistente);
        }

        clienteMapper.atualizarEntidadeComDTO(clienteDTO, clienteExistente);
        clienteExistente = clienteRepository.save(clienteExistente);

        return clienteMapper.toDTO(clienteExistente);
    }


}
