package br.com.estudos.teste.Cadastro.entity;

import br.com.estudos.teste.Cadastro.dto.request.UsuarioRequestDTO;
import br.com.estudos.teste.Cadastro.entity.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String telefone;
    private String cpf;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String email;
    private String senha;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Investimento> investimentoList;

    public Usuario(UsuarioRequestDTO dto){
        this.nome = dto.nome();
        this.telefone = dto.telefone();
        this.email = dto.email();
        this.cpf = dto.cpf();
    }
}

