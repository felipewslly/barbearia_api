package br.com.barbearia_api.repository;

import br.com.barbearia_api.model.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AgendamentoRepo extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findByDate(LocalDate data);
    List<Agendamento> findByClientId(Long clienteId);
    List<Agendamento> findByServiceId(Long servicoId);

}
