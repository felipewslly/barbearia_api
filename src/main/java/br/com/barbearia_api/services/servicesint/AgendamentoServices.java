package br.com.barbearia_api.services.servicesint;

import br.com.barbearia_api.dto.AgendamentoDTO;
import br.com.barbearia_api.model.Agendamento;
import br.com.barbearia_api.model.Clientes;
import br.com.barbearia_api.model.Funcionario;
import br.com.barbearia_api.model.Servico;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface AgendamentoServices {

    Agendamento criarAgendamento(Agendamento request);

    Agendamento atualizarAgendamento(Long agendamentoId, Agendamento agendamentoAtt);

    Agendamento agendamentoPorId(Long id);

    void removerAgendamentoPorId(Long agendamentoId);

    List<Agendamento> todosAgendamentos();


}
