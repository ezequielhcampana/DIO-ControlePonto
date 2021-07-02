package com.dio.controleponto.service;

import com.dio.controleponto.model.Calendario;
import com.dio.controleponto.model.TipoData;
import com.dio.controleponto.repository.CalendarioRepository;
import com.dio.controleponto.repository.TipoDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CalendarioService {

    @Autowired
    private CalendarioRepository calendarioRepository;
    @Autowired
    private TipoDataRepository tipoDataRepository;

    public Calendario save(Calendario calendario) {

        TipoData tp = tipoDataRepository
                .findById(calendario.getTipoData().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo Data não encontrado"));

        calendario.setTipoData(tp);

        return calendarioRepository.save(calendario);
    }

    public Calendario update(Long id, Calendario calendario) {

        TipoData tp = tipoDataRepository
                .findById(calendario.getTipoData().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo Data não encontrado"));

        calendario.setTipoData(tp);

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
