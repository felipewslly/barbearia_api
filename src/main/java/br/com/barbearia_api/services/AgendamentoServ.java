package br.com.barbearia_api.services;

import br.com.barbearia_api.model.entity.Agendamento;
import br.com.barbearia_api.model.entity.Clientes;
import br.com.barbearia_api.model.entity.Funcionario;
import br.com.barbearia_api.model.entity.Servico;
import br.com.barbearia_api.repository.AgendamentoRepo;
import br.com.barbearia_api.repository.ClienteRepo;
import br.com.barbearia_api.repository.FuncionarioRepo;
import br.com.barbearia_api.repository.ServicoRepo;
import br.com.barbearia_api.services.servicesint.AgendamentoServices;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


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

    @Override
    public Agendamento criarAgendamento(Agendamento request) {
        Clientes cliente = clienteRepo.findById(request.getCliente().getId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        Funcionario funcionario = funcionarioRepo.findById(request.getFuncionarios().getId())
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado"));

        Servico servico = serviceRepo.findById(request.getServicos().get())
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado"));

        // Criar um novo agendamento
        Agendamento agendamento = new Agendamento();
        agendamento.setData(request.getData());
        agendamento.setHora(request.getHora());
        agendamento.setCliente(cliente);
        agendamento.setFuncionarios(funcionario);
        agendamento.setServicos(servico);

        return agendamentoRepo.save(agendamento);
    }

    @Override
    public Agendamento atualizarAgendamento(Long agendamentoId, Agendamento agendamentoAtt) {
        Agendamento agendamento = agendamentoRepo.findById(agendamentoId).orElseThrow(
                () -> new IllegalArgumentException("Agendamento não existe"));
        agendamento.setData(LocalDate.from(agendamento.getData()));
        agendamento.setHora(LocalTime.from(agendamento.getHora()));
        agendamento.setServicos(agendamento.getServicos());
        agendamento.setFuncionarios(agendamento.getFuncionarios());

        return agendamentoRepo.save(agendamento);


    }

    @Override
    public List<Agendamento> removerAgendamentoPorId(Long agendamentoId) {
                    agendamentoRepo.findAll();
       try{
           if (!agendamentoRepo.existsById(agendamentoId)){
               throw new IllegalArgumentException("ID do agendamento não encontrado");
           }
            agendamentoRepo.deleteById(agendamentoId);
       }catch(IllegalArgumentException e){
           throw e;
       }catch (Exception e){
           throw new RuntimeException("Erro ao excluir agendamento", e);
       }

       return agendamentoRepo.findAll();


    }

    @Override
    public List<Agendamento> todosAgendamentos() {
        List<Agendamento> todosAgendamentos = agendamentoRepo.findAll();
            if (todosAgendamentos.isEmpty()){
                throw new RuntimeException("LISTA DE AGENDAMENTO VAZIA");
            }
                return todosAgendamentos;
    }

    @Override
    public List<Agendamento> agendamentoPorData(LocalDate data) {
        List<Agendamento> agendamentos = agendamentoRepo.findByData(data);
            if (agendamentos.isEmpty()){
                throw new IllegalArgumentException("DATA NÃO REGISTRADA");
            }
            return agendamentos;
    }

    @Override
    public List<Agendamento> agendamentoPorCliente(Long cliente) {
        List<Agendamento> clienteAgendado = agendamentoRepo.findByCliente_Id(cliente);
            if (clienteAgendado.isEmpty()){
                throw new RuntimeException("ID DO CLIENTE NÃO ENCONTRADO");
            }
            return clienteAgendado;
    }

    @Override
    public List<Agendamento> agendamentoPorServicos(Long servicoId) {
        List<Agendamento> agendamentoPorServico = agendamentoRepo.findByServicosId(servicoId);

            if (agendamentoPorServico.isEmpty()){
                throw new RuntimeException("ID DO SERVIÇO NÃO ENCONTRADO");
            }
            return agendamentoPorServico;
    }

}
