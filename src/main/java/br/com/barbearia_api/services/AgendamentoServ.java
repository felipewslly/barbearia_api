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
       List<Clientes> clientes = new ArrayList<>();
        for (Long id : request.getCliente().stream().map(Clientes::getId).toList()) {
            Optional<Clientes> cliente = clienteRepo.findById(id);
            cliente.ifPresent(clientes::add);
        }
        List<Funcionario> funcionarios = new ArrayList<>();
            for (Long id : request.getFuncionarios().stream().map(Funcionario::getId).toList()) {
                Optional<Funcionario> funcionario = funcionarioRepo.findById(id);
                funcionario.ifPresent(funcionarios::add);
            }
            List<Servico> servicos = new ArrayList<>();
            for (Long id : request.getServicos().stream().map(Servico::getId).toList()) {
                Optional<Servico> servico = serviceRepo.findById(id);
                servico.ifPresent(servicos::add);
            }
            Agendamento agendamento = new Agendamento();
            agendamento.setData(LocalDate.from(request.getData()));
            agendamento.setHora(LocalTime.from(request.getHora()));
            agendamento.setServicos(servicos);
            agendamento.setFuncionarios(funcionarios);
            agendamento.setCliente(clientes);
            return agendamentoRepo.save(agendamento);
            }


    @Override
    public Agendamento atualizarAgendamento(Long agendamentoId, Agendamento agendamentoAtt) {
        Agendamento agendamento = agendamentoRepo.findById(agendamentoId).orElseThrow(
                ()-> new IllegalArgumentException("ID DO AGENDAMENTO NÃO EXISTE")

        );

        agendamento.setData(agendamentoAtt.getData());
        agendamento.setHora(agendamentoAtt.getHora());

        List<Clientes> clientesAtt = new ArrayList<>();
            for (Long id : agendamentoAtt.getCliente().stream().map(Clientes::getId).toList()) {
                Optional<Clientes> cliente = clienteRepo.findById(id);
                cliente.ifPresent(clientesAtt::add);
            }
        agendamento.setCliente(clientesAtt);

        List<Funcionario> funcionariosAtt = new ArrayList<>();
            for (Long id : agendamentoAtt.getFuncionarios().stream().map(Funcionario::getId).toList()) {
                Optional<Funcionario> funcionario = funcionarioRepo.findById(id);
                funcionario.ifPresent(funcionariosAtt::add);
            }
        agendamento.setFuncionarios(funcionariosAtt);

        List<Servico> servicosAtt = new ArrayList<>();
            for (Long id : agendamentoAtt.getServicos().stream().map(Servico::getId).toList()) {
                Optional<Servico> servico = serviceRepo.findById(id);
                servico.ifPresent(servicosAtt::add);
            }
        agendamento.setServicos(servicosAtt);

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
