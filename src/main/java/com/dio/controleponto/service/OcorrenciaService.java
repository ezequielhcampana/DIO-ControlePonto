package com.dio.controleponto.service;

import com.dio.controleponto.model.Ocorrencia;
import com.dio.controleponto.repository.OcorrenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OcorrenciaService {

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    public Ocorrencia save(Ocorrencia ocorrencia) {
        return ocorrenciaRepository.save(ocorrencia);
    }

    public Ocorrencia update(Long id, Ocorrencia ocorrencia) {

        Optional<Ocorrencia> ocorr = ocorrenciaRepository.findById(id);

        if (ocorr.isPresent()) {
            Ocorrencia ocorrAtualizada = new Ocorrencia();
            ocorrAtualizada.setId(id);
            ocorrAtualizada.setNome(ocorrencia.getNome());
            ocorrAtualizada.setDescricao(ocorrencia.getDescricao());
            return ocorrenciaRepository.save(ocorrAtualizada);
        }

        return null;
    }

    public void delete(Long id) {
        ocorrenciaRepository.deleteById(id);
    }

    public List<Ocorrencia> findAll() {
        return ocorrenciaRepository.findAll();
    }

    public Optional<Ocorrencia> findById(Long id) {
        return ocorrenciaRepository.findById(id);
    }
}
