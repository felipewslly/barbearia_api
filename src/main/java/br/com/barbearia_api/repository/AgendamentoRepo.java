package br.com.barbearia_api.repository;

import br.com.barbearia_api.model.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface AgendamentoRepo extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findByData(LocalDate data);
    List<Agendamento> findByClientes_Id(Long cliente);
    List<Agendamento> findByServicosId(Long servicoId);

}
