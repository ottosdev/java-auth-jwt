package com.br.otto.financeiroestudoback.service;

import com.br.otto.financeiroestudoback.dto.cliente.AtualizarClienteDTO;
import com.br.otto.financeiroestudoback.dto.cliente.ClienteDTO;
import com.br.otto.financeiroestudoback.mapper.AtualizarClienteMapper;
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

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteMapper clienteMapper;
    private final AtualizarClienteMapper atualizarClienteMapper;
    private final ClienteRepository clienteRepository;
    private final EnderecoMapper enderecoMapper;

    @Transactional
    public AtualizarClienteDTO atualizarCliente(AtualizarClienteDTO clienteDTO) {
        Cliente clienteExistente = clienteRepository.findById(clienteDTO.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario já existe!"));

        atualizarClienteMapper.atualizarEntidadeComDTO(clienteDTO, clienteExistente);

        if (Objects.nonNull(clienteDTO.endereco())) {
            Endereco endereco = enderecoMapper.toEntity(clienteDTO.endereco());
            endereco.setCliente(clienteExistente);
            clienteExistente.setEndereco(endereco);
        }

        clienteExistente = clienteRepository.save(clienteExistente);
        return atualizarClienteMapper.toDTO(clienteExistente);
    }

    public List<ClienteDTO> listarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(clienteMapper::toDTO).toList();
    }

    public ClienteDTO buscarClientePorId(UUID id) {
        Cliente cliente = clienteRepository.getClienteById(id);
        return clienteMapper.toDTO(cliente);
    }

//    @Transactional
//    public AtualizarClienteDTO atualizarCliente(AtualizarClienteDTO clienteDTO) {
//        Cliente clienteExistente = clienteRepository.findByEmail(clienteDTO.email())
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não existe!"));
//
//
//        if (clienteExistente.getEndereco() != null) {
//            clienteExistente.getEndereco().setCliente(clienteExistente);
//        }
//
//        if(clienteExistente.getEndereco() == null) {
//            Endereco endereco = enderecoMapper.toEntity(clienteDTO.endereco());
//            endereco.setCliente(clienteExistente);
//            clienteExistente.setEndereco(endereco);
//        }
//
//        atualizarClienteMapper.atualizarEntidadeComDTO(clienteDTO, clienteExistente);
//        clienteExistente = clienteRepository.save(clienteExistente);
//
//        return atualizarClienteMapper.toDTO(clienteExistente);
//    }


    public ClienteDTO buscarCliente(UUID id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));

        return clienteMapper.toDTO(cliente);
    }
}
