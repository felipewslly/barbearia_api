package br.com.barbearia_api.repository;

import br.com.barbearia_api.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepo extends JpaRepository<Servico, Long> {

}
