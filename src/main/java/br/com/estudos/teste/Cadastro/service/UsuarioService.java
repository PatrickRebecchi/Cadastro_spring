package br.com.estudos.teste.Cadastro.service;

import br.com.estudos.teste.Cadastro.dto.request.LoginRequestDTO;
import br.com.estudos.teste.Cadastro.dto.request.UsuarioRequestDTO;
import br.com.estudos.teste.Cadastro.dto.request.UsuarioRequestSemSenhaDTO;
import br.com.estudos.teste.Cadastro.dto.response.*;
import br.com.estudos.teste.Cadastro.entity.Usuario;
import br.com.estudos.teste.Cadastro.exception.CadastroException;
import br.com.estudos.teste.Cadastro.repository.UsuarioRepository;
import br.com.estudos.teste.Cadastro.validation.ValidacaoUsuarioCriar;
import br.com.estudos.teste.Cadastro.validation.ValidacaoUsuarioCriarCpf;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private List<ValidacaoUsuarioCriar> validacao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private List<ValidacaoUsuarioCriarCpf> validacaoCPF;

    public String formatarCpf(String cpf) {

        cpf = cpf.replaceAll("\\D", "");

        return cpf.replaceFirst(
                "(\\d{3})(\\d{3})(\\d{3})(\\d{2})",
                "$1.$2.$3-$4"
        );
    }

    @Transactional
    public List<UsuarioResponseCompletoDTO> buscarCompleto() {
        return converteDadosCompelto(repository.findAll());
    }

    @Transactional
    public List<UsuarioResponseSenhaDTO> buscarCompletoSenha() {
        return converteDadosComSenha(repository.findAll());
    }

    @Transactional
    public List<UsuarioResponseSenhaDTO> converteDadosComSenha(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(u -> new UsuarioResponseSenhaDTO(
                        u.getId(),
                        u.getNome(),
                        u.getEmail(),
                        u.getCpf(),
                        u.getSenha()))
                .collect(Collectors.toList());
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
                        u.getEmail()))
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
    public UsuarioResponseCompletoDTO cadastrarUsuario(UsuarioRequestDTO dto) {


        validacao.forEach(u -> u.validar(dto));
        validacaoCPF.forEach(u -> u.validar(dto));

        Usuario usuario = new Usuario(dto);

        usuario.setSenha(
                passwordEncoder.encode(dto.senha())
        );

        usuario.setCpf(formatarCpf(usuario.getCpf()));

        repository.save(usuario);
        return new UsuarioResponseCompletoDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getCpf(),
                usuario.getRole()
        );
    }

    @Transactional
    public LoginResponseMensagemDTO login(LoginRequestDTO dto){

        Usuario usuario = repository.findByEmail(dto.email())
                .orElseThrow(() ->
                        new CadastroException("Usuário não encontrado")
                );

        boolean senhaValida = passwordEncoder.matches(
                dto.senha(),
                usuario.getSenha()
        );

        if(!senhaValida){
            throw new CadastroException("Senha inválida");
        }

        return new LoginResponseMensagemDTO("Login realizado");
    }
}
