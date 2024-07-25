package br.com.barbearia_api.services.servicesint;

import br.com.barbearia_api.model.entity.Agendamento;
import br.com.barbearia_api.model.entity.Servico;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface AgendamentoServices {

    Agendamento criarAgendamento (LocalDate data, LocalTime hora, Long clienteId, Long serviceId);

    Optional<Agendamento> atualizarAgendamento(Long agendamentoId, LocalDate novaData, LocalTime novaHora, Servico novoServico);

    List<Agendamento> removerAgendamentoPorId(Long agendamentoId);

    List<Agendamento> todosAgendamentos();

    List<Agendamento> agendamentoPorData(LocalDate data);

    List<Agendamento> agendamentoPorCliente(Long clienteId);

    List<Agendamento> agendamentoPorServicos(Long servicoId);

}
