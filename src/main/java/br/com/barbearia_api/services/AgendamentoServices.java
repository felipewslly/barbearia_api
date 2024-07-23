package br.com.barbearia_api.services;

import br.com.barbearia_api.model.entity.Agendamento;
import br.com.barbearia_api.model.entity.Servico;
import org.apache.catalina.LifecycleState;
import org.hibernate.graph.internal.AbstractGraphNode;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface AgendamentoServices {

    Agendamento criarAgendamento (LocalDate data, LocalTime hora, Long clienteId, Long serviceId);

    Optional<Agendamento> atualizarAgendamento(Long agendamentoId, LocalDate novaData, LocalTime novaHora, Servico novoServico);

    List<Agendamento> removerAgendamentoPorId(Long agendamentoId);

    Optional<Agendamento> todosAgendamentos(Long agendamentoId);

    List<Agendamento> agendamentoPorData(LocalDate data);

    List<Agendamento> agendamentoPorCliente(Long clienteId);

    List<Agendamento> agendamentoPorServicos(Long servicoId);

}
