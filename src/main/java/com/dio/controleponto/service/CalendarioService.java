package com.dio.controleponto.service;

import com.dio.controleponto.model.Calendario;
import com.dio.controleponto.repository.CalendarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalendarioService {

    @Autowired
    private CalendarioRepository calendarioRepository;

    public Calendario save(Calendario calendario) {
        return calendarioRepository.save(calendario);
    }

    public Calendario update(Long id, Calendario calendario) {

        Optional<Calendario> cal = calendarioRepository.findById(id);
        if (cal.isPresent()) {
            Calendario calendarioAtualizado = new Calendario();
            calendarioAtualizado.setId(id);
            calendarioAtualizado.setTipoData(calendario.getTipoData());
            calendarioAtualizado.setDescricao(calendario.getDescricao());
            calendarioAtualizado.setDataEspecial(calendario.getDataEspecial());
            return calendarioRepository.save(calendarioAtualizado);
        }

        return null;
    }

    public void delete(Long id) {

        calendarioRepository.deleteById(id);
    }

    public List<Calendario> findAll() {
        return calendarioRepository.findAll();
    }

    public Optional<Calendario> findById(Long id) {

        return calendarioRepository.findById(id);
    }
}
