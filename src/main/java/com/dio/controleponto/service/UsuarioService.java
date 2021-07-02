package com.dio.controleponto.service;

import com.dio.controleponto.model.*;
import com.dio.controleponto.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private CategoriaUsuarioRepository categoriaUsuarioRepository;
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private NivelAcessoRepository nivelAcessoRepository;
    @Autowired
    private JornadaRepository jornadaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario save(Usuario usuario) {

        CategoriaUsuario categUser = categoriaUsuarioRepository
                .findById(usuario.getCategoriaUsuario().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria de Usuário não encontrado!"));

        Empresa emp = empresaRepository
                .findById(usuario.getEmpresa().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empresa não encontrada!"));

        NivelAcesso na = nivelAcessoRepository
                .findById(usuario.getNivelAcesso().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nivel de Acesso não encontrado!"));

        JornadaTrabalho jt = jornadaRepository
                .findById(usuario.getJornadaTrabalho().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Jornada de Trabalho não encontrado!"));

        usuario.setCategoriaUsuario(categUser);
        usuario.setEmpresa(emp);
        usuario.setNivelAcesso(na);
        usuario.setJornadaTrabalho(jt);

        return usuarioRepository.save(usuario);
    }

    public Usuario update(Long id, Usuario usuario) {

        Optional<Usuario> optUsuario = usuarioRepository.findById(id);
        if(optUsuario.isPresent()) {
            CategoriaUsuario categUser = categoriaUsuarioRepository
                    .findById(usuario.getCategoriaUsuario().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria de Usuário não encontrado!"));

            Empresa emp = empresaRepository
                    .findById(usuario.getEmpresa().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empresa não encontrada!"));

            NivelAcesso na = nivelAcessoRepository
                    .findById(usuario.getNivelAcesso().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nivel de Acesso não encontrado!"));

            JornadaTrabalho jt = jornadaRepository
                    .findById(usuario.getJornadaTrabalho().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Jornada de Trabalho não encontrado!"));

            Usuario usuarioAtualizado = new Usuario();
            usuarioAtualizado.setCategoriaUsuario(categUser);
            usuarioAtualizado.setEmpresa(emp);
            usuarioAtualizado.setNivelAcesso(na);
            usuarioAtualizado.setJornadaTrabalho(jt);
            usuarioAtualizado.setNome(usuario.getNome());
            usuarioAtualizado.setInicioJornada(usuario.getInicioJornada());
            usuarioAtualizado.setFinalJornada(usuario.getFinalJornada());
            usuarioAtualizado.setTolerancia(usuario.getTolerancia());

            usuarioRepository.save(usuarioAtualizado);
        }

        return null;
    }

    public void delete(Long id) {

        usuarioRepository.deleteById(id);
    }

    public List<Usuario> findAll() {

        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id) {

        return usuarioRepository.findById(id);
    }
}
