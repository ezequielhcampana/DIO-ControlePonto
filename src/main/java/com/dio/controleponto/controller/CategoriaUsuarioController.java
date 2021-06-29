package com.dio.controleponto.controller;

import com.dio.controleponto.model.CategoriaUsuario;
import com.dio.controleponto.service.CategoriaUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/v1/categoria-usuario")
public class CategoriaUsuarioController {

    @Autowired
    private CategoriaUsuarioService categoriaUsuarioService;

    @PostMapping
    public ResponseEntity<CategoriaUsuario> createCategoriaUsuario(@RequestBody CategoriaUsuario categoriaUsuario) {

        categoriaUsuarioService.save(categoriaUsuario);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(categoriaUsuario.getId()).toUri();

        return ResponseEntity.created(uri).body(categoriaUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaUsuario> updateCategoriaUsuario(@PathVariable Long id, @RequestBody CategoriaUsuario categoriaUsuario) {

        categoriaUsuario = categoriaUsuarioService.update(id, categoriaUsuario);

        if (categoriaUsuario == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(categoriaUsuario);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirCategoriaUsuario(@PathVariable Long id) {
        try {
            categoriaUsuarioService.delete(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<CategoriaUsuario> getCategoriaUsuarioList() {
        return categoriaUsuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaUsuario> getLocalidadeId(@PathVariable Long id) throws Exception {
        return ResponseEntity
                .ok(categoriaUsuarioService.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Categoria de Usuário não encontrado!")));
    }
}
