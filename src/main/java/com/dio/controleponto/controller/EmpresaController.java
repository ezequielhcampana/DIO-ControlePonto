package com.dio.controleponto.controller;

import com.dio.controleponto.model.Empresa;
import com.dio.controleponto.service.EmpresaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/v1/empresa")
@Api(value = "API Rest - EMPRESA")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @PostMapping
    @ApiOperation(value = "Salva uma nova Empresa")
    public ResponseEntity<Empresa> createEmpresa(@RequestBody Empresa empresa) {

        empresaService.save(empresa);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(empresa.getId()).toUri();

        return ResponseEntity.created(uri).body(empresa);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza uma Empresa através do ID passado")
    public ResponseEntity<Empresa> updateEmpresa(@PathVariable Long id, @RequestBody Empresa empresa ) {

        empresa = empresaService.update(id, empresa);

        if (empresa == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(empresa);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Excluir uma Empresa através do ID passado")
    public ResponseEntity excluirEmpresa(@PathVariable Long id) {
        try {
            empresaService.delete(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de Empresas")
    public List<Empresa> getEmpresaList() {
        return empresaService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retorna através do ID uma única Empresa")
    public ResponseEntity<Empresa> getEmpresaId(@PathVariable("id") Long id) throws Exception{
        return ResponseEntity
                .ok(empresaService
                        .findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Empresa não encontrada!")));
    }
}
