package br.com.barbearia_api.converter;

import br.com.barbearia_api.dto.AgendamentoDTO;
import br.com.barbearia_api.model.Agendamento;
import br.com.barbearia_api.model.Clientes;
import br.com.barbearia_api.model.Funcionario;
import br.com.barbearia_api.model.Servico;
import br.com.barbearia_api.repository.FuncionarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.stream.Collectors;

@Component
public class AgendamentoConverter {

    @Autowired
    private ClienteConverter clienteConverter;
    @Autowired
    private FuncionarioConverter funcionarioConverter;

    public static AgendamentoDTO paraDTO(Agendamento agendamento) {
        AgendamentoDTO dto = new AgendamentoDTO();
        dto.setId(agendamento.getId());
        dto.setData(agendamento.getData());
        dto.setHora(agendamento.getHora());
        dto.setClientes(agendamento.getClientes().stream()
                .map(Clientes::getId)
                .collect(Collectors.toList()));

        dto.setFuncionarios(agendamento.getFuncionarios().stream()
                .map(Funcionario::getId)
                .collect(Collectors.toList()));

        dto.setServicos(agendamento.getServicos().stream()
                .map(Servico::getId)
                .collect(Collectors.toList()));

        return dto;
    }
}
