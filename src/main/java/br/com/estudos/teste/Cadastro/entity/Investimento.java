package br.com.estudos.teste.Cadastro.entity;
import br.com.estudos.teste.Cadastro.entity.enums.TipoInvestimento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "investimentos")

public class Investimento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    @NotBlank(message = "Nome do investimento obrigatória")
    private String nome;
    @NotBlank(message = "Descrição do investimento obrigatória")
    private String descricao;
    @Column(nullable = false)
    @NotNull(message = "Valor obrigatória")
    private BigDecimal valor;
    private LocalDate dataCriacao;
    @Enumerated(EnumType.STRING)
    private TipoInvestimento tipo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
