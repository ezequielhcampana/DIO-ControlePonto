package com.dio.controleponto.controller;

import com.dio.controleponto.model.JornadaTrabalho;
import com.dio.controleponto.service.JornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/v1/jornada")
public class JornadaTrabalhoController {

    @Autowired
    private JornadaService jornadaService;

    @PostMapping
    public ResponseEntity<JornadaTrabalho> createJornada(@RequestBody JornadaTrabalho jornadaTrabalho) {

        jornadaService.save(jornadaTrabalho);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(jornadaTrabalho.getId()).toUri();

        return ResponseEntity.created(uri).body(jornadaTrabalho);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JornadaTrabalho> updateJornada(@PathVariable Long id, @RequestBody JornadaTrabalho jornadaTrabalho ) {

        jornadaTrabalho = jornadaService.update(id, jornadaTrabalho);

        if (jornadaTrabalho == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(jornadaTrabalho);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirJornada(@PathVariable Long id) {
        try {
            jornadaService.delete(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<JornadaTrabalho> getJornadaList() {
        return jornadaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JornadaTrabalho> getJornadaId(@PathVariable("id") Long id) throws Exception{
        return ResponseEntity
                .ok(jornadaService
                .getById(id)
                .orElseThrow(() -> new NoSuchElementException("Jornada não encontrada!")));
    }
}
