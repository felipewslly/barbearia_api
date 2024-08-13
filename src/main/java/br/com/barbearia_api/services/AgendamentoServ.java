package br.com.barbearia_api.services;

import br.com.barbearia_api.exception.ApiException;
import br.com.barbearia_api.converter.AgendamentoConverter;
import br.com.barbearia_api.dto.AgendamentoDTO;
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

    @Autowired
    private AgendamentoConverter agendamentoConverter;





    @Transactional
    @Override
    public AgendamentoDTO criarAgendamento(AgendamentoDTO request) {
        Agendamento agendamento = new Agendamento();
        agendamento.setData(request.getData());
        agendamento.setHora(request.getHora());

        List<Clientes> clientes = request.getClientes().stream()
                .map(clienteRepo::findById)
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        agendamento.setClientes(clientes);

        List<Servico> servicos = request.getServicos().stream()
                .map(serviceRepo::findById)
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        agendamento.setServicos(servicos);

        List<Funcionario> funcionarios = request.getFuncionarios().stream()
                .map(funcionarioRepo::findById)
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        agendamento.setFuncionarios(funcionarios);

        Agendamento agendamentoAtualizado = agendamentoRepo.save(agendamento);
        return AgendamentoConverter.paraDTO(agendamentoAtualizado);
    }
    @Override
    @Transactional
    public Agendamento atualizarAgendamento(Long agendamentoId, Agendamento agendamentoAtt) {
        Agendamento agendamentoExistente = agendamentoRepo.findById(agendamentoId).orElseThrow(
                () -> new ApiException("ID NÃO ENCONTRADO"));

            agendamentoExistente.setData(agendamentoAtt.getData());
            agendamentoExistente.setHora(agendamentoAtt.getHora());

            List<Clientes> clientes = agendamentoAtt.getClientes().stream()
                  .map(cliente -> clienteRepo.findById(cliente.getId()).orElseThrow(() -> new RuntimeException("Cliente não encontrado")))
                  .collect(Collectors.toList());
                  agendamentoExistente.setClientes(clientes);

            List<Funcionario> funcionarios = agendamentoAtt.getFuncionarios().stream()
                  .map(funcionario -> funcionarioRepo.findById(funcionario.getId()).orElseThrow(() -> new RuntimeException("Funcionário não encontrado")))
                   .collect(Collectors.toList());
          agendamentoExistente.setFuncionarios(funcionarios);

             List<Servico> servicos = agendamentoAtt.getServicos().stream()
                   .map(servico -> serviceRepo.findById(servico.getId()).orElseThrow(() -> new RuntimeException("Serviço não encontrado")))
                  .collect(Collectors.toList());
            agendamentoExistente.setServicos(servicos);

            agendamentoExistente.setClientes(clientes);
            agendamentoExistente.setFuncionarios(funcionarios);
            agendamentoExistente.setServicos(servicos);

                return agendamentoRepo.save(agendamentoExistente);


       }

    @Override
    public AgendamentoDTO agendamentoPorId(Long id) {
        Agendamento findById = agendamentoRepo.findById(id).orElseThrow(
                () -> new ApiException("ID NÃO ENCONTRADO"));
        List<Long> clientesIds = findById.getClientes().stream()
                .map(Clientes::getId)
                .toList();

        List<Long> servicoIds = findById.getServicos().stream()
                .map(Servico::getId)
                .toList();
        List<Long> funcionarioIds = findById.getFuncionarios().stream()
                .map(Funcionario::getId)
                .toList();

        AgendamentoDTO dto = new AgendamentoDTO();
        dto.setId(findById.getId());
        dto.setData(findById.getData());
        dto.setHora(findById.getHora());
        dto.setClientes(clientesIds);
        dto.setServicos(servicoIds);
        dto.setFuncionarios(funcionarioIds);

        return dto;
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
    public List<AgendamentoDTO> agendamentoPorData(LocalDate data) {
        List<Agendamento> findByDate = agendamentoRepo.findByData(data);
        return findByDate.stream()
                .map(AgendamentoConverter::paraDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AgendamentoDTO> agendamentoPorCliente(Long cliente) {
        List<Agendamento> findByClientId = agendamentoRepo.findByClientes_Id(cliente);
        return findByClientId.stream()
                .map(AgendamentoConverter::paraDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AgendamentoDTO> agendamentoPorServicos(Long servicoId) {
        List<Agendamento> findByServiceId = agendamentoRepo.findByServicosId(servicoId);
        return findByServiceId.stream()
                .map(AgendamentoConverter::paraDTO)
                .collect(Collectors.toList());
    }
}
