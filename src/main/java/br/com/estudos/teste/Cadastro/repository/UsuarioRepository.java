package br.com.estudos.teste.Cadastro.repository;


import br.com.estudos.teste.Cadastro.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
