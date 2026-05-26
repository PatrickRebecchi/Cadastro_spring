package br.com.estudos.teste.Cadastro.validation;

import br.com.estudos.teste.Cadastro.dto.request.UsuarioRequestDTO;
import br.com.estudos.teste.Cadastro.exception.CadastroException;
import br.com.estudos.teste.Cadastro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoUsuarioCriarCpf implements ValidacaoCriarCpfUsuario{

    @Autowired
    private UsuarioRepository repository;

    @Override
    public void validar(UsuarioRequestDTO dto){
        if (repository.existsByCpf(dto.cpf())){
            throw new CadastroException("CPF já cadastrado.");
        }
    }

}
