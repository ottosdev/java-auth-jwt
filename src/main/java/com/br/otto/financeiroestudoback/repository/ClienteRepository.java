package com.br.otto.financeiroestudoback.repository;

import com.br.otto.financeiroestudoback.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    Optional<Cliente> findByEmail(String email);
    Optional<Cliente> findByCpf(String cpf);

    Cliente getClienteById(UUID id);

    Optional<Cliente> findByCpfAndEmail(String cpf, String email);
}
