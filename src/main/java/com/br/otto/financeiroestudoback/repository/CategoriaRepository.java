package com.br.otto.financeiroestudoback.repository;

import com.br.otto.financeiroestudoback.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {
}
