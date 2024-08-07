package br.com.barbearia_api.converter;

import br.com.barbearia_api.dto.AgendamentoDTO;
import br.com.barbearia_api.model.Agendamento;
import br.com.barbearia_api.model.Clientes;
import br.com.barbearia_api.model.Funcionario;
import br.com.barbearia_api.model.Servico;


import java.util.stream.Collectors;

public class AgendamentoConverter {
    public static AgendamentoDTO paraDTO(Agendamento agendamento){
        AgendamentoDTO dto = new AgendamentoDTO();
        dto.setId(agendamento.getId());
        dto.setData(agendamento.getData());
        dto.setHora(agendamento.getHora());
        dto.setClienteIds(agendamento.getClientes().stream()
                .map(Clientes::getId)
                .collect(Collectors.toList()));

        dto.setFuncionarioIds(agendamento.getFuncionarios().stream()
                .map(Funcionario::getId)
                .collect(Collectors.toList()));

        dto.setServicoIds(agendamento.getServicos().stream()
                .map(Servico::getId)
                .collect(Collectors.toList()));

        return dto;
    }
}
