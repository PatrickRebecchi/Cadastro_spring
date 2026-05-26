package br.com.estudos.teste.Cadastro.validation;

import br.com.estudos.teste.Cadastro.dto.request.UsuarioRequestDTO;
import br.com.estudos.teste.Cadastro.exception.CadastroException;
import br.com.estudos.teste.Cadastro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoUsuarioCriar implements ValidacaoCriarUsuario{

    @Autowired
    private UsuarioRepository repository;

    @Override
    public void validar(UsuarioRequestDTO dto){
        if (repository.existsByEmail(dto.email())){
            throw new CadastroException("Email já cadastrado (Validacao)");
        }
    }

}
