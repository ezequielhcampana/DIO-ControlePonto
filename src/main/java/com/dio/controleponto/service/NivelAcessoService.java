package com.dio.controleponto.service;

import com.dio.controleponto.model.NivelAcesso;
import com.dio.controleponto.repository.NivelAcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NivelAcessoService {

    @Autowired
    private NivelAcessoRepository nivelAcessoRepository;

    public NivelAcesso save(NivelAcesso nivelAcesso) {
        return nivelAcessoRepository.save(nivelAcesso);
    }

    public NivelAcesso update(Long id, NivelAcesso nivelAcesso) {

        Optional<NivelAcesso> nivel = nivelAcessoRepository.findById(id);
        if (nivel.isPresent()) {
            NivelAcesso nivelAtualizado = new NivelAcesso();
            nivelAtualizado.setId(id);
            nivelAtualizado.setDescricao(nivelAcesso.getDescricao());
            return nivelAcessoRepository.save(nivelAtualizado);
        }

        return null;
    }

    public void delete(Long id) {

        nivelAcessoRepository.deleteById(id);
    }

    public List<NivelAcesso> findAll() {
        return nivelAcessoRepository.findAll();
    }

    public Optional<NivelAcesso> getById(Long id) {

        return nivelAcessoRepository.findById(id);
    }
}
