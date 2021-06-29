package com.dio.controleponto.controller;

import com.dio.controleponto.model.TipoData;
import com.dio.controleponto.service.TipoDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/v1/tipo-data")
public class TipoDataController {

    @Autowired
    private TipoDataService tipoDataService;

    @PostMapping
    public ResponseEntity<TipoData> createTipoData(@RequestBody TipoData tipoData) {

        tipoDataService.save(tipoData);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(tipoData.getId()).toUri();

        return ResponseEntity.created(uri).body(tipoData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoData> updateTipoData(@PathVariable Long id, @RequestBody TipoData tipoData) {

        tipoData = tipoDataService.update(id, tipoData);

        if (tipoData == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(tipoData);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirTipoData(@PathVariable Long id) {
        try {
            tipoDataService.delete(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<TipoData> getTipoDataList() {
        return tipoDataService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoData> getTipoDataId(@PathVariable Long id) throws Exception {
        return ResponseEntity
                .ok(tipoDataService.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Tipo Data não encontrado!")));
    }
}
