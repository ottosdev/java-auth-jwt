package com.br.otto.financeiroestudoback.controller;

import com.br.otto.financeiroestudoback.dto.CategoriaDTO;
import com.br.otto.financeiroestudoback.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping
    public CategoriaDTO salvarCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        return categoriaService.salvarCategoria(categoriaDTO);
    }

    @PutMapping
    public CategoriaDTO atualizarCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        return categoriaService.atualizarCategoria(categoriaDTO);
    }

    @GetMapping
    public List<CategoriaDTO> listarTodasCategorias() {
        return categoriaService.listarCategorias();
    }

    @DeleteMapping("/{id}")
    public void deletarCategoria(@PathVariable UUID id) {
        categoriaService.deletarCategoria(id);
    }
}
