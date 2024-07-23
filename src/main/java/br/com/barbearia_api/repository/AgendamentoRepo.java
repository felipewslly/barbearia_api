package br.com.barbearia_api.repository;

import br.com.barbearia_api.model.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepo extends JpaRepository<Agendamento, Long> {
}
