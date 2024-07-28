package br.com.barbearia_api.repository;

import br.com.barbearia_api.model.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepo extends JpaRepository<Funcionario, Long> {
}
