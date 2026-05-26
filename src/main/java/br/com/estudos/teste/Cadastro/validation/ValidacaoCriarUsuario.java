package br.com.estudos.teste.Cadastro.validation;


import br.com.estudos.teste.Cadastro.dto.request.UsuarioRequestDTO;

public interface ValidacaoCriarUsuario {
    void validar(UsuarioRequestDTO dto);
}
