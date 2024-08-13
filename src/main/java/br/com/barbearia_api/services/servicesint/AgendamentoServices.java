package br.com.barbearia_api.services.servicesint;

import br.com.barbearia_api.dto.AgendamentoDTO;
import br.com.barbearia_api.model.Agendamento;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface AgendamentoServices {

    @Transactional
    AgendamentoDTO criarAgendamento(AgendamentoDTO request);

    Agendamento atualizarAgendamento(Long agendamentoId, Agendamento agendamentoAtt);

    AgendamentoDTO agendamentoPorId(Long id);

    void removerAgendamentoPorId(Long agendamentoId);

    List<AgendamentoDTO> todosAgendamentos();

    List<AgendamentoDTO> agendamentoPorData(LocalDate data);

    List<AgendamentoDTO> agendamentoPorCliente(Long clienteId);

    List<AgendamentoDTO> agendamentoPorServicos(Long servicoId);

}
