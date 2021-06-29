package com.dio.controleponto.controller;

import com.dio.controleponto.model.Calendario;
import com.dio.controleponto.service.CalendarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/v1/calendario")
public class CalendarioController {

    @Autowired
    private CalendarioService calendarioService;

    @PostMapping
    public ResponseEntity<Calendario> createCalendario(@RequestBody Calendario calendario) {

        calendarioService.save(calendario);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(calendario.getId()).toUri();

        return ResponseEntity.created(uri).body(calendario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Calendario> updateCalendario(@PathVariable Long id, @RequestBody Calendario calendario ) {

        calendario = calendarioService.update(id, calendario);

        if (calendario == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(calendario);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirCalendario(@PathVariable Long id) {
        try {
            calendarioService.delete(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<Calendario> getCalendarioList() {
        return calendarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Calendario> getCalendarioId(@PathVariable("id") Long id) throws Exception{
        return ResponseEntity
                .ok(calendarioService
                        .findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Calendário não encontrado!")));
    }
}
