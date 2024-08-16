package br.com.barbearia_api.services;
import br.com.barbearia_api.exception.ApiException;
import br.com.barbearia_api.model.Agendamento;
import br.com.barbearia_api.model.Clientes;
import br.com.barbearia_api.model.Funcionario;
import br.com.barbearia_api.model.Servico;
import br.com.barbearia_api.repository.AgendamentoRepo;
import br.com.barbearia_api.repository.ClienteRepo;
import br.com.barbearia_api.repository.FuncionarioRepo;
import br.com.barbearia_api.repository.ServicoRepo;
import br.com.barbearia_api.services.servicesint.AgendamentoServices;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoServ implements AgendamentoServices {

    @Autowired
    private FuncionarioRepo funcionarioRepo;

    @Autowired
    private ServicoRepo serviceRepo;

    @Autowired
    private AgendamentoRepo agendamentoRepo;

    @Autowired
    private ClienteRepo clienteRepo;


    @Transactional
    @Override
    public Agendamento createSchedule(Agendamento newSchedule) {
        Agendamento agendamento = new Agendamento();
        agendamento.setData(newSchedule.getData());
        agendamento.setHora(newSchedule.getHora());

                List<Clientes> clientes = newSchedule.getClientes().stream()
                .map(cliente -> clienteRepo.findById(cliente.getId()))
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        agendamento.setClientes(clientes);

        List<Servico> servicos = newSchedule.getServicos().stream()
                .map(servico -> serviceRepo.findById(servico.getId()))
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        agendamento.setServicos(servicos);

        List<Funcionario> funcionarios = newSchedule.getFuncionarios().stream()
                .map(funcionario -> funcionarioRepo.findById(funcionario.getId()))
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        agendamento.setFuncionarios(funcionarios);

        return agendamentoRepo.save(agendamento);
    }

    @Override
    @Transactional
    public Agendamento updateSchedule(Long agendamentoId, Agendamento scheduleUpdated) {

        Optional<Agendamento> optionalAgendamento = agendamentoRepo.findById(agendamentoId);

        if (optionalAgendamento.isPresent()) {
            Agendamento agendamentoExistente = optionalAgendamento.get();
            agendamentoExistente.setData(scheduleUpdated.getData());
            agendamentoExistente.setHora(scheduleUpdated.getHora());

            List<Clientes> clientes = scheduleUpdated.getClientes().stream()
                    .map(cliente -> clienteRepo.findById(cliente.getId()))
                    .flatMap(Optional::stream)
                    .collect(Collectors.toList());
            agendamentoExistente.setClientes(clientes);

            List<Servico> servicos = scheduleUpdated.getServicos().stream()
                    .map(servico -> serviceRepo.findById(servico.getId()))
                    .flatMap(Optional::stream)
                    .collect(Collectors.toList());
            agendamentoExistente.setServicos(servicos);

            List<Funcionario> funcionarios = scheduleUpdated.getFuncionarios().stream()
                    .map(funcionario -> funcionarioRepo.findById(funcionario.getId()))
                    .flatMap(Optional::stream)
                    .collect(Collectors.toList());
            agendamentoExistente.setFuncionarios(funcionarios);

            return agendamentoRepo.save(agendamentoExistente);
        }
        throw new ApiException("FALHA NA ATUALIZAÇÃO");
    }


    @Override
    public Agendamento scheduleById(Long id) {
        return agendamentoRepo.findById(id).orElseThrow(
                () -> new ApiException("Agendamento não existe"));

    }

    @Override
    @Transactional
    public void deleteScheduleById(Long scheduleId) {
       try{
           if (!agendamentoRepo.existsById(scheduleId)){
               throw new IllegalArgumentException("ID do agendamento não encontrado");
           }
                agendamentoRepo.deleteById(scheduleId);
       }catch(IllegalArgumentException e){
           throw e;
       }catch (Exception e){
           throw new RuntimeException("Erro ao excluir agendamento", e);
       }

    }

    @Override
    public List<Agendamento> allSchedule() {
        return agendamentoRepo.findAll();
    }
}
