package br.com.estudos.teste.Cadastro.dto.response;


import br.com.estudos.teste.Cadastro.entity.enums.Role;

public record UsuarioResponseCompletoDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        Role role
) {

}
