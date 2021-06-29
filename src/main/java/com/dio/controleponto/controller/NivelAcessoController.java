package com.dio.controleponto.controller;

import com.dio.controleponto.model.NivelAcesso;
import com.dio.controleponto.service.NivelAcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/v1/nivel-acesso")
public class NivelAcessoController {

    @Autowired
    private NivelAcessoService nivelAcessoService;

    @PostMapping
    public ResponseEntity<NivelAcesso> createNivelAcesso(@RequestBody NivelAcesso nivelAcesso) {

        nivelAcessoService.save(nivelAcesso);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(nivelAcesso.getId()).toUri();

        return ResponseEntity.created(uri).body(nivelAcesso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NivelAcesso> updateNivelAcesso(@PathVariable Long id, @RequestBody NivelAcesso nivelAcesso ) {

        nivelAcesso = nivelAcessoService.update(id, nivelAcesso);

        if (nivelAcesso == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(nivelAcesso);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirNivelAcesso(@PathVariable Long id) {
        try {
            nivelAcessoService.delete(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<NivelAcesso> getNivelAcessoList() {
        return nivelAcessoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NivelAcesso> getNivelAcessoId(@PathVariable("id") Long id) throws Exception{
        return ResponseEntity
                .ok(nivelAcessoService
                        .getById(id)
                        .orElseThrow(() -> new NoSuchElementException("Nivel de Acesso não encontrado!")));
    }
}
