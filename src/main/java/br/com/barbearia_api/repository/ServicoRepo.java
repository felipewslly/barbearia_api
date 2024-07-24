package br.com.barbearia_api.repository;

import br.com.barbearia_api.model.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.security.Provider;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ServicoRepo extends JpaRepository<Servico, Long> {

}
