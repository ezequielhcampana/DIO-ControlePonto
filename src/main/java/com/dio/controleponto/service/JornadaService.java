package com.dio.controleponto.service;

import com.dio.controleponto.model.JornadaTrabalho;
import com.dio.controleponto.repository.JornadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JornadaService {

    @Autowired
    private JornadaRepository jornadaRepository;

    public JornadaTrabalho save(JornadaTrabalho jornadaTrabalho) {
        return jornadaRepository.save(jornadaTrabalho);
    }

    public JornadaTrabalho update(Long id, JornadaTrabalho jornadaTrabalho) {

        Optional<JornadaTrabalho> jt = jornadaRepository.findById(id);
        if (jt.isPresent()) {
            JornadaTrabalho jtAtualizado = new JornadaTrabalho();
            jtAtualizado.setId(id);
            jtAtualizado.setDescricao(jornadaTrabalho.getDescricao());
            return jornadaRepository.save(jtAtualizado);
        }

        return null;
    }

    public void delete(Long id) {

        jornadaRepository.deleteById(id);
    }

    public List<JornadaTrabalho> findAll() {
        return jornadaRepository.findAll();
    }

    public Optional<JornadaTrabalho> getById(Long id) {

        return jornadaRepository.findById(id);
    }
}
