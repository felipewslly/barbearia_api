package br.com.barbearia_api.services;

import br.com.barbearia_api.ApiException;
import br.com.barbearia_api.converter.AgendamentoConverter;
import br.com.barbearia_api.dto.AgendamentoDTO;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    public AgendamentoDTO criarAgendamento(AgendamentoDTO request) {
        Agendamento agendamento = new Agendamento();
        agendamento.setData(request.getData());
        agendamento.setHora(request.getHora());

        List<Clientes> clientes = request.getClienteIds().stream()
                .map(clienteRepo::findById)
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
            if(request.getClienteIds() == null){
                 throw new ApiException("ID NÃO EXISTENTE");
            }
        agendamento.setClientes(clientes);

        List<Servico> servicos = request.getServicoIds().stream()
                .map(serviceRepo::findById)
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        if(request.getServicoIds() == null){
            throw new ApiException("ID NÃO EXISTENTE");
        }
            agendamento.setServicos(servicos);

        List<Funcionario> funcionarios = request.getFuncionarioIds().stream()
                .map(funcionarioRepo::findById)
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        if(request.getFuncionarioIds() == null){
            throw new ApiException("ID NÃO EXISTENTE");
        }
            agendamento.setFuncionarios(funcionarios);

        Agendamento agendamentoSave = agendamentoRepo.save(agendamento);
        return AgendamentoConverter.paraDTO(agendamentoSave);
    }




    @Override
    @Transactional
    public Agendamento atualizarAgendamento(Long agendamentoId, Agendamento agendamentoAtt) {
        Optional<Agendamento> agendamentoExistente = agendamentoRepo.findById(agendamentoId);

        if (agendamentoExistente.isPresent()) {
            Agendamento agendamento = agendamentoExistente.get();
            List<Clientes> clientesNew = agendamento.getClientes().stream()
                    .map(Clientes::getId)
                    .map(clienteRepo::findById)
                    .flatMap(Optional::stream)
                    .collect(Collectors.toList());

            List<Funcionario> funcionarioNew = agendamento.getFuncionarios().stream()
                    .map(Funcionario::getId)
                    .map(funcionarioRepo::findById)
                    .flatMap(Optional::stream)
                    .collect(Collectors.toList());


            List<Servico> servicosNew = agendamento.getServicos().stream()
                    .map(Servico::getId)
                    .map(serviceRepo::findById)
                    .flatMap(Optional::stream)
                    .collect(Collectors.toList());

            agendamento.setData(agendamentoAtt.getData());
            agendamento.setHora(agendamentoAtt.getHora());
            agendamento.setClientes(clientesNew);
            agendamento.setFuncionarios(funcionarioNew);
            agendamento.setServicos(servicosNew);
            return agendamentoRepo.save(agendamento);
        }else{
            throw new ApiException("AGENDAMENTO NÃO ENCONTRADO");
        }
    }

    @Override
    @Transactional
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
    public List<AgendamentoDTO> todosAgendamentos() {
        List<Agendamento> todosAgendamentos = agendamentoRepo.findAll();
            if (todosAgendamentos.isEmpty()){
                throw new RuntimeException("LISTA DE AGENDAMENTO VAZIA");
            }
                return todosAgendamentos.stream()
                        .map(AgendamentoConverter::paraDTO)
                        .collect(Collectors.toList());
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
        List<Agendamento> clienteAgendado = agendamentoRepo.findByClientes_Id(cliente);
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
