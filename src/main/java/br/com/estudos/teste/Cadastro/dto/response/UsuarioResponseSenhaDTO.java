package br.com.estudos.teste.Cadastro.dto.response;


import br.com.estudos.teste.Cadastro.entity.enums.Role;

public record UsuarioResponseSenhaDTO(
        Long id,
        String nome,
        String email,
        String cpf,
        String senha
) {

}
