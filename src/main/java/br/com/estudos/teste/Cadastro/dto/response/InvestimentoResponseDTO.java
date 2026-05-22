package br.com.estudos.teste.Cadastro.dto.response;

import br.com.estudos.teste.Cadastro.entity.enums.TipoInvestimento;

import java.math.BigDecimal;
import java.time.LocalDate;

public record InvestimentoResponseDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal valor,
        LocalDate dataInvestimento,
        TipoInvestimento tipo,
        String nomeUsuario
) {
}
