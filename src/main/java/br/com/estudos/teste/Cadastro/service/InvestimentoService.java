package br.com.estudos.teste.Cadastro.service;

import br.com.estudos.teste.Cadastro.dto.request.UsuarioRequestDTO;
import br.com.estudos.teste.Cadastro.dto.response.UsuarioResponseCompletoDTO;
import br.com.estudos.teste.Cadastro.dto.response.UsuarioResponseDTO;
import br.com.estudos.teste.Cadastro.entity.Usuario;
import br.com.estudos.teste.Cadastro.exception.CadastroException;
import br.com.estudos.teste.Cadastro.repository.InvestimentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvestimentoService {

    @Autowired
    private InvestimentoRepository repository;

}
