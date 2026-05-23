package br.com.estudos.teste.Cadastro.service;

import br.com.estudos.teste.Cadastro.dto.request.UsuarioRequestDTO;
import br.com.estudos.teste.Cadastro.dto.response.UsuarioResponseCompletoDTO;
import br.com.estudos.teste.Cadastro.dto.response.UsuarioResponseDTO;
import br.com.estudos.teste.Cadastro.entity.Usuario;
import br.com.estudos.teste.Cadastro.exception.CadastroException;
import br.com.estudos.teste.Cadastro.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Transactional
    public List<UsuarioResponseCompletoDTO> buscarCompleto() {
        return converteDadosCompelto(repository.findAll());
    }

    @Transactional
    public List<UsuarioResponseCompletoDTO> converteDadosCompelto(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(u -> new UsuarioResponseCompletoDTO(
                        u.getId(),
                        u.getNome(),
                        u.getEmail(),
                        u.getTelefone(),
                        u.getCpf(),
                        u.getRole()))
                .collect(Collectors.toList());
    }

    public List<UsuarioResponseDTO> obterUsuario() {
        return converteDados(repository.findAll());
    }
    @Transactional
    public List<UsuarioResponseDTO> converteDados(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(u -> new UsuarioResponseDTO(
                        u.getId(),
                        u.getNome(),
                        u.getRole()))
                .collect(Collectors.toList());
    }

    public UsuarioResponseCompletoDTO obterPorId(long id) {
            Usuario u = repository.findById(id)
                    .orElseThrow(()-> new CadastroException("Usuario não encostrado"));

        return new UsuarioResponseCompletoDTO(
                u.getId(),
                u.getNome(),
                u.getEmail(),
                u.getTelefone(),
                u.getCpf(),
                u.getRole()
        );
    }

    @Transactional
    public UsuarioRequestDTO cadastrarUsuario(UsuarioRequestDTO dto) {
        if (repository.existsByEmail(dto.email())){
            throw new CadastroException("Email já cadastrado!");
        }

        Usuario u = new Usuario(dto);
        return new UsuarioRequestDTO(u.getNome(),
                u.getTelefone(),
                u.getEmail(),
                u.getCpf());
    }
}
