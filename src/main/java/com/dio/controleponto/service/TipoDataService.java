package com.dio.controleponto.service;

import com.dio.controleponto.model.TipoData;
import com.dio.controleponto.repository.TipoDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoDataService {

    @Autowired
    private TipoDataRepository tipoDataRepository;

    public TipoData save(TipoData tipoData) {
        return tipoDataRepository.save(tipoData);
    }

    public TipoData update(Long id, TipoData tipoData) {
        Optional<TipoData> td = tipoDataRepository.findById(id);
        if (td.isPresent()) {
            TipoData tdAtualizado = new TipoData();
            tdAtualizado.setId(id);
            tdAtualizado.setDescricao(tipoData.getDescricao());
            return tipoDataRepository.save(tdAtualizado);
        }

        return null;
    }

    public void delete(Long id) {
        tipoDataRepository.deleteById(id);
    }

    public List<TipoData> findAll() {
        return tipoDataRepository.findAll();
    }

    public Optional<TipoData> findById(Long id) {
        return tipoDataRepository.findById(id);
    }
}
