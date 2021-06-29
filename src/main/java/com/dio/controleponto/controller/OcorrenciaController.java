package com.dio.controleponto.controller;

import com.dio.controleponto.model.Ocorrencia;
import com.dio.controleponto.service.OcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/v1/ocorrencia")
public class OcorrenciaController {

    @Autowired
    private OcorrenciaService ocorrenciaService;

    @PostMapping
    public ResponseEntity<Ocorrencia> createOcorrencia(@RequestBody Ocorrencia ocorrencia) {

        ocorrenciaService.save(ocorrencia);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(ocorrencia.getId()).toUri();

        return ResponseEntity.created(uri).body(ocorrencia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ocorrencia> updateOcorrencia(@PathVariable Long id, @RequestBody Ocorrencia ocorrencia) {

        ocorrencia = ocorrenciaService.update(id, ocorrencia);

        if (ocorrencia == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(ocorrencia);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirOcorrencia(@PathVariable Long id) {
        try {
            ocorrenciaService.delete(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<Ocorrencia> getOcorrenciaList() {
        return ocorrenciaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ocorrencia> getOcorrenciaId(@PathVariable("id") Long id) throws Exception{
        return ResponseEntity
                .ok(ocorrenciaService
                        .findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Ocorrência não encontrada!")));
    }
}
