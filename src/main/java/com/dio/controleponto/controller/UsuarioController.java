package com.dio.controleponto.controller;

import com.dio.controleponto.model.Calendario;
import com.dio.controleponto.model.Usuario;
import com.dio.controleponto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {

        usuarioService.save(usuario);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario ) {

        usuario = usuarioService.update(id, usuario);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(usuario);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirUsuario(@PathVariable Long id) {
        try {
            usuarioService.delete(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<Usuario> getUsuarioList() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getCalendarioId(@PathVariable("id") Long id) throws Exception{
        return ResponseEntity
                .ok(usuarioService
                        .findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado!")));
    }
}
