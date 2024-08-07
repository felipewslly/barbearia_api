package br.com.barbearia_api.repository;

import br.com.barbearia_api.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AgendamentoRepo extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findByData(LocalDate data);
    List<Agendamento> findByClientes_Id(Long cliente);
    List<Agendamento> findByServicosId(Long servicoId);

}
