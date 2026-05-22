package br.com.estudos.teste.Cadastro.controller;


import br.com.estudos.teste.Cadastro.dto.response.UsuarioResponseCompletoDTO;
import br.com.estudos.teste.Cadastro.dto.response.UsuarioResponseDTO;
import br.com.estudos.teste.Cadastro.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.PanelUI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/completo")
    public List<UsuarioResponseCompletoDTO> completoDTO(){
        return service.buscarCompleto();
    }

    @GetMapping
    public List<UsuarioResponseDTO> obterTodosUsuarios(){
        return service.obterUsuario();
    }
}
