package br.com.barbearia_api.services.servicesint;

import br.com.barbearia_api.dto.AgendamentoDTO;
import br.com.barbearia_api.model.entity.Agendamento;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface AgendamentoServices {

    @Transactional
    AgendamentoDTO criarAgendamento(AgendamentoDTO request);

    Agendamento atualizarAgendamento(Long agendamentoId, Agendamento agendamentoAtt);

    List<Agendamento> removerAgendamentoPorId(Long agendamentoId);

    List<AgendamentoDTO> todosAgendamentos();

    List<Agendamento> agendamentoPorData(LocalDate data);

    List<Agendamento> agendamentoPorCliente(Long clienteId);

    List<Agendamento> agendamentoPorServicos(Long servicoId);

}
