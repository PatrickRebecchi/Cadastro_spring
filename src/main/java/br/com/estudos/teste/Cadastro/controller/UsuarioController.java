package br.com.estudos.teste.Cadastro.controller;


import br.com.estudos.teste.Cadastro.dto.request.UsuarioRequestDTO;
import br.com.estudos.teste.Cadastro.dto.response.UsuarioResponseCompletoDTO;
import br.com.estudos.teste.Cadastro.dto.response.UsuarioResponseDTO;
import br.com.estudos.teste.Cadastro.entity.Usuario;
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

@Log4j2
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    private UsuarioService service;

    @GetMapping("/completo")
    public List<UsuarioResponseCompletoDTO> completoDTO(){
        return service.buscarCompleto();
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

    @PostMapping("/adc")
    public ResponseEntity<UsuarioRequestDTO> cadastrarUsuario(@RequestBody @Valid UsuarioRequestDTO dto){
        log.info(dateUtil.formatLocalDateTimeToDatabase(LocalDateTime.now()));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.cadastrarUsuario(dto));
    }
}
