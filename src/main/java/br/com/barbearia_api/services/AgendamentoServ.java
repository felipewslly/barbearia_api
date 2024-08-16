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
    public Agendamento criarAgendamento(Agendamento request) {
        Agendamento agendamento = new Agendamento();
        agendamento.setData(request.getData());
        agendamento.setHora(request.getHora());

                List<Clientes> clientes = request.getClientes().stream()
                .map(cliente -> clienteRepo.findById(cliente.getId()))
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        agendamento.setClientes(clientes);


        List<Servico> servicos = request.getServicos().stream()
                .map(servico -> serviceRepo.findById(servico.getId()))
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        agendamento.setServicos(servicos);


        List<Funcionario> funcionarios = request.getFuncionarios().stream()
                .map(funcionario -> funcionarioRepo.findById(funcionario.getId()))
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        agendamento.setFuncionarios(funcionarios);


        return agendamentoRepo.save(agendamento);
    }






    @Override
    @Transactional
    public Agendamento atualizarAgendamento(Long agendamentoId, Agendamento agendamentoAtt) {

        Optional<Agendamento> optionalAgendamento = agendamentoRepo.findById(agendamentoId);

        if (optionalAgendamento.isPresent()) {
            Agendamento agendamentoExistente = optionalAgendamento.get();
            agendamentoExistente.setData(agendamentoAtt.getData());
            agendamentoExistente.setHora(agendamentoAtt.getHora());

            List<Clientes> clientes = agendamentoAtt.getClientes().stream()
                    .map(cliente -> clienteRepo.findById(cliente.getId()))
                    .flatMap(Optional::stream)
                    .collect(Collectors.toList());
            agendamentoExistente.setClientes(clientes);

            List<Servico> servicos = agendamentoAtt.getServicos().stream()
                    .map(servico -> serviceRepo.findById(servico.getId()))
                    .flatMap(Optional::stream)
                    .collect(Collectors.toList());
            agendamentoExistente.setServicos(servicos);

            List<Funcionario> funcionarios = agendamentoAtt.getFuncionarios().stream()
                    .map(funcionario -> funcionarioRepo.findById(funcionario.getId()))
                    .flatMap(Optional::stream)
                    .collect(Collectors.toList());
            agendamentoExistente.setFuncionarios(funcionarios);


            System.out.println("Clientes: " + clientes);
            System.out.println("Servicos: " + servicos);
            System.out.println("Funcionarios: " + funcionarios);

            return agendamentoRepo.save(agendamentoExistente);
        }
        throw new ApiException("FALHA NA ATUALIZAÇÃO");
    }


    @Override
    public Agendamento agendamentoPorId(Long id) {
        return agendamentoRepo.findById(id).orElseThrow(
                () -> new ApiException("Agendamento não existe"));

    }

    @Override
    @Transactional
    public void removerAgendamentoPorId(Long agendamentoId) {
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

    }

    @Override
    public List<Agendamento> todosAgendamentos() {
        return agendamentoRepo.findAll();
    }
}
