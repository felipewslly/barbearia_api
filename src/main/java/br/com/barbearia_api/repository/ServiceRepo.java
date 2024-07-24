package br.com.barbearia_api.repository;

import br.com.barbearia_api.model.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.security.Provider;
@Repository
public interface ServiceRepo extends JpaRepository<Servico, Long> {
}
