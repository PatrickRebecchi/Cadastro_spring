package br.com.estudos.teste.Cadastro.controller;


import br.com.estudos.teste.Cadastro.dto.request.LoginRequestDTO;
import br.com.estudos.teste.Cadastro.dto.request.UsuarioRequestDTO;
import br.com.estudos.teste.Cadastro.dto.response.*;
import br.com.estudos.teste.Cadastro.entity.Usuario;
import br.com.estudos.teste.Cadastro.service.AuthService;
import br.com.estudos.teste.Cadastro.service.UsuarioService;
import br.com.estudos.teste.Cadastro.util.DateUtil;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.PanelUI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private AuthService authService;

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    private UsuarioService service;

    @GetMapping("/completo")
    public List<UsuarioResponseCompletoDTO> completoDTO(){
        return service.buscarCompleto();
    }

    @GetMapping("/completo/senha")
    public List<UsuarioResponseSenhaDTO> completoComSenha(){
        return service.buscarCompletoSenha();
    }

    @GetMapping
    public List<UsuarioResponseDTO> obterTodosUsuarios(){
        log.info(dateUtil.formatLocalDateTimeToDatabase(LocalDateTime.now()));
        return service.obterUsuario();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseCompletoDTO> obterUsuarioPorId(@PathVariable long id){
        return ResponseEntity.ok(service.obterPorId(id));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseMensagemDTO> login(
            @RequestBody LoginRequestDTO dto
    ){
        return ResponseEntity.ok(
                service.login(dto)
        );
    }

    @PostMapping("/adc")
    public ResponseEntity<UsuarioResponseCompletoDTO > cadastrarUsuario(@RequestBody @Valid UsuarioRequestDTO dto){
        log.info(dateUtil.formatLocalDateTimeToDatabase(LocalDateTime.now()));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.cadastrarUsuario(dto));
    }

    @PostMapping("/loginComSenha")
    public ResponseEntity<?> loginTest(@RequestBody LoginRequestDTO request) {

        Optional<Usuario> usuario = authService.authenticate(
                request.email(),
                request.senha()
        );
        if (usuario.isPresent()) {
            Usuario u = usuario.get();
            return ResponseEntity.ok(
                    new LoginResponseDTO(
                            u.getId(),
                            u.getNome(),
                            u.getRole(),
                            "Login bem-sucedido"
                    )
            );
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new LoginResponseMensagemDTO(
                        "Email ou senha inválidos"
                ));
    }
}
