package com.dio.controleponto.service;

import com.dio.controleponto.model.Empresa;
import com.dio.controleponto.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public Empresa save(Empresa empresa) {

        return empresaRepository.save(empresa);
    }

    public Empresa update(Long id, Empresa empresa) {

        Optional<Empresa> emp = empresaRepository.findById(id);
        if (emp.isPresent()) {
            Empresa empAtualizada = new Empresa();
            empAtualizada.setId(id);
            empAtualizada.setDescricao(empresa.getDescricao());
            empAtualizada.setCnpj(empresa.getCnpj());
            empAtualizada.setEndereco(empresa.getEndereco());
            empAtualizada.setBairro(empresa.getBairro());
            empAtualizada.setCidade(empresa.getCidade());
            empAtualizada.setEstado(empresa.getEstado());
            empAtualizada.setTelefone(empresa.getTelefone());

            return empresaRepository.save(empAtualizada);
        }

        return null;
    }

    public void delete(Long id) {

        empresaRepository.deleteById(id);
    }

    public List<Empresa> findAll() {

        return empresaRepository.findAll();
    }

    public Optional<Empresa> findById(Long id) {

        return empresaRepository.findById(id);
    }
}
