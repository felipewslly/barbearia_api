package br.com.barbearia_api.services;

import br.com.barbearia_api.model.entity.Agendamento;
import br.com.barbearia_api.model.entity.Cliente;
import br.com.barbearia_api.model.entity.Servico;
import br.com.barbearia_api.repository.AgendamentoRepo;
import br.com.barbearia_api.repository.ClienteRepo;
import br.com.barbearia_api.repository.ServiceRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoServ implements AgendamentoServices{

    private ServiceRepo serviceRepo;
    private AgendamentoRepo agendamentoRepo;
    private ClienteRepo clienteRepo;
    @Override
    public Agendamento criarAgendamento(LocalDate data, LocalTime hora, Long clienteId, Long serviceId) {
        if (data == null || hora == null || clienteId == null || serviceId == null){
            throw new IllegalArgumentException("Parâmetros não podem estar vazios");
        }

        Cliente cliente = clienteRepo.findById(clienteId).orElseThrow(
                () -> new IllegalArgumentException("Cliente não encontrado"));

        Servico service = serviceRepo.findById(serviceId).orElseThrow(
                () -> new IllegalArgumentException("Serviço não encontrado"));

        Agendamento agendamento = new Agendamento();
        agendamento.setData(data);
        agendamento.setHora(hora);
        agendamento.setCliente(cliente);
        List<Servico> servicos = new ArrayList<>();
        servicos.add(service);
        agendamento.setServicos(servicos);


        return agendamentoRepo.save(agendamento);


    }
    @Override
    public Optional<Agendamento> atualizarAgendamento(Long agendamentoId, LocalDate novaData, LocalTime novaHora, Servico novoServico) {
        Agendamento agendamento = agendamentoRepo.findById(agendamentoId).orElseThrow(
                () -> new IllegalArgumentException("Agendamento não existe"));
        agendamento.setData(novaData);
        agendamento.setHora(novaHora);

        List<Servico> servicos = new ArrayList<>();
        servicos.add(novoServico);
        agendamento.setServicos(servicos);

        Agendamento agendamentoAtualizado = agendamentoRepo.save(agendamento);

            return Optional.of(agendamentoRepo.save(agendamentoAtualizado));


    }

    @Override
    public List<Agendamento> removerAgendamentoPorId(Long agendamentoId) {
       Agendamento agendamento = agendamentoRepo.findById(agendamentoId).orElseThrow(
               () -> new IllegalArgumentException("ID não existente"));

        agendamentoRepo.deleteById(agendamentoId);


        return agendamentoRepo.findAll();


    }

    @Override
    public Optional<Agendamento> todosAgendamentos(Long agendamentoId) {
        return Optional.empty();
    }

    @Override
    public List<Agendamento> agendamentoPorData(LocalDate data) {
        return List.of();
    }

    @Override
    public List<Agendamento> agendamentoPorCliente(Long clienteId) {
        return List.of();
    }

    @Override
    public List<Agendamento> agendamentoPorServicos(Long servicoId) {
        return List.of();
    }
}
