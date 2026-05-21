package br.com.estudos.teste.Cadastro.dto.response;


import br.com.estudos.teste.Cadastro.entity.enums.Role;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        Role role
) {

}
