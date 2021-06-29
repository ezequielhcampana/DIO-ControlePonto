package com.dio.controleponto.service;

import com.dio.controleponto.model.CategoriaUsuario;
import com.dio.controleponto.repository.CategoriaUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaUsuarioService {

    @Autowired
    private CategoriaUsuarioRepository categoriaUsuarioRepository;

    public CategoriaUsuario save(CategoriaUsuario categoriaUsuario) {
        return categoriaUsuarioRepository.save(categoriaUsuario);
    }

    public CategoriaUsuario update(Long id, CategoriaUsuario categoriaUsuario) {

        Optional<CategoriaUsuario> categ = categoriaUsuarioRepository.findById(id);
        if (categ.isPresent()) {
            CategoriaUsuario categAtualizado = new CategoriaUsuario();
            categAtualizado.setId(id);
            categAtualizado.setDescricao(categAtualizado.getDescricao());
            return categoriaUsuarioRepository.save(categAtualizado);
        }

        return null;
    }

    public void delete(Long id) {

        categoriaUsuarioRepository.deleteById(id);
    }

    public List<CategoriaUsuario> findAll() {
        return categoriaUsuarioRepository.findAll();
    }

    public Optional<CategoriaUsuario> findById(Long id) {

        return categoriaUsuarioRepository.findById(id);
    }
}
