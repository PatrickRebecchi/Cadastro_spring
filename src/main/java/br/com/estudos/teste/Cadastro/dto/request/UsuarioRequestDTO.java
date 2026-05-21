package br.com.estudos.teste.Cadastro.dto.request;

import br.com.estudos.teste.Cadastro.entity.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

public record UsuarioRequestDTO(
        @NotBlank(message = "Nome obrigatorio")
        String nome,
        @Pattern(
                regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$",
                message = "Telefone inválido"
        )
        String telefone,
        @NotBlank(message = "Email obrigatório")
        @Email(message = "Email inválido")
        String email,
        @NotBlank(message = "CPF obrigatório")
        @CPF(message = "CPF inválido")
        String cpf
) {
}
