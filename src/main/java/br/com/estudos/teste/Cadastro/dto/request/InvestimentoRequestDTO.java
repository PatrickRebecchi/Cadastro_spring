package br.com.estudos.teste.Cadastro.dto.request;

import br.com.estudos.teste.Cadastro.entity.enums.TipoInvestimento;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record InvestimentoRequestDTO(
        @NotBlank(message = "Nome do investimento obrigatório")
        String nome,
        @NotBlank(message = "Descrição obrigatória")
        String descricao,
        @NotNull(message = "Valor obrigatório")
        @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero")
        BigDecimal valor,
        @NotNull(message = "Data do investimento obrigatória")
        LocalDate dataInvestimento,
        @NotNull(message = "Tipo do investimento obrigatório")
        TipoInvestimento tipo,
        @NotNull(message = "Usuário obrigatório")
        Long usuarioId
        ) {
}
