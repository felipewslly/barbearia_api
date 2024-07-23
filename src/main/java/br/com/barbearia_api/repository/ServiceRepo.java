package br.com.barbearia_api.repository;

import br.com.barbearia_api.model.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.security.Provider;

public interface ServiceRepo extends JpaRepository<Servico, Long> {
}
