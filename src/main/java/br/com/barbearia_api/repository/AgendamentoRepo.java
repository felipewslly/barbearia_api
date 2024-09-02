package br.com.barbearia_api.repository;

import br.com.barbearia_api.model.Agendamento;
import br.com.barbearia_api.model.Clientes;
import br.com.barbearia_api.model.Funcionario;
import br.com.barbearia_api.model.Servico;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AgendamentoRepo extends JpaRepository<Agendamento, Long> {

    @Query("SELECT data FROM Agendamento WHERE data = :date")
    Agendamento findScheduleByDate(@Param("date") LocalDate date);

    @Query("SELECT a FROM Agendamento a JOIN a.clientes c WHERE c.cpf = :cpf")
    List<Agendamento> clientsWithOrWithoutAppointments(@Param("cpf") String cpf);

    @Query("SELECT a FROM Agendamento a JOIN a.clientes c WHERE c.id = :clienteId")
    List<Agendamento> findAppointmentsByClienteId(@Param("clienteId") Long clienteId);
}
