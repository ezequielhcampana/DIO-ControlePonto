package com.dio.controleponto.service;

import com.dio.controleponto.model.JornadaTrabalho;
import com.dio.controleponto.model.Localidade;
import com.dio.controleponto.repository.LocalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalidadeService {

    @Autowired
    private LocalidadeRepository localidadeRepository;

    public Localidade save(Localidade localidade) {

        return localidadeRepository.save(localidade);
    }

    public Localidade update(Long id, Localidade localidade) {

        Optional<Localidade> local = localidadeRepository.findById(id);
        if (local.isPresent()) {
            Localidade localidadeAtualizada = new Localidade();
            localidadeAtualizada.setId(id);
            localidadeAtualizada.setDescricao(localidade.getDescricao());
            return localidadeRepository.save(localidadeAtualizada);
        }

        return null;
    }

    public void delete(Long id) {

        localidadeRepository.deleteById(id);
    }

    public List<Localidade> findAll() {

        return localidadeRepository.findAll();
    }

    public Optional<Localidade> findById(Long id) {

        return localidadeRepository.findById(id);
    }
}
