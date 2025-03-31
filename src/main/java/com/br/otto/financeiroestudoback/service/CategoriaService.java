package com.br.otto.financeiroestudoback.service;

import com.br.otto.financeiroestudoback.dto.CategoriaDTO;
import com.br.otto.financeiroestudoback.mapper.CategoriaMapper;
import com.br.otto.financeiroestudoback.model.Categoria;
import com.br.otto.financeiroestudoback.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaDTO salvarCategoria(CategoriaDTO categoriaDto) {
        Categoria categoria = categoriaMapper.toEntity(categoriaDto);
        categoria = categoriaRepository.save(categoria);
        return categoriaMapper.toDTO(categoria);
    }

    public CategoriaDTO atualizarCategoria(CategoriaDTO categoriaDto) {
        Categoria categoria = categoriaRepository.findById(categoriaDto.getId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        categoriaMapper.atualizarEntidadeComDTO(categoriaDto, categoria);
        categoria = categoriaRepository.save(categoria);
        return categoriaMapper.toDTO(categoria);
    }

    public List<CategoriaDTO> listarCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(categoriaMapper::toDTO)
                .toList();
    }

    public void deletarCategoria(UUID id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        categoriaRepository.delete(categoria);
    }
}
