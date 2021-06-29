package com.dio.controleponto.controller;

import com.dio.controleponto.model.Localidade;
import com.dio.controleponto.model.TipoData;
import com.dio.controleponto.service.LocalidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/v1/localidade")
public class LocalidadeController {

    @Autowired
    private LocalidadeService localidadeService;

    @PostMapping
    public ResponseEntity<Localidade> createLocalidade(@RequestBody Localidade localidade) {

        localidadeService.save(localidade);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(localidade.getId()).toUri();

        return ResponseEntity.created(uri).body(localidade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Localidade> updateLocalidade(@PathVariable Long id, @RequestBody Localidade localidade) {

        localidade = localidadeService.update(id, localidade);

        if (localidade == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(localidade);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirLocalidade(@PathVariable Long id) {
        try {
            localidadeService.delete(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<Localidade> getLocalidadeList() {
        return localidadeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Localidade> getLocalidadeId(@PathVariable Long id) throws Exception {
        return ResponseEntity
                .ok(localidadeService.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Localidade não encontrada!")));
    }
}
