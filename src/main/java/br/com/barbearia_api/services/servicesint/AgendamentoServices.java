package br.com.barbearia_api.services.servicesint;

import br.com.barbearia_api.model.entity.Agendamento;
import br.com.barbearia_api.model.entity.Clientes;
import br.com.barbearia_api.model.entity.Servico;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AgendamentoServices {

    Agendamento criarAgendamento (Agendamento agendamento);

    Agendamento atualizarAgendamento(Long agendamentoId, Agendamento agendamentoAtt);

    List<Agendamento> removerAgendamentoPorId(Long agendamentoId);

    List<Agendamento> todosAgendamentos();

    List<Agendamento> agendamentoPorData(LocalDate data);

    List<Agendamento> agendamentoPorCliente(Long clienteId);

    List<Agendamento> agendamentoPorServicos(Long servicoId);

}
