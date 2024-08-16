package br.com.barbearia_api.converter;

import br.com.barbearia_api.dto.AgendamentoDTO;
import br.com.barbearia_api.dto.ClienteDTO;
import br.com.barbearia_api.dto.FuncionarioDTO;
import br.com.barbearia_api.dto.ServicoDTO;
import br.com.barbearia_api.model.Agendamento;
import br.com.barbearia_api.model.Clientes;
import br.com.barbearia_api.model.Funcionario;
import br.com.barbearia_api.model.Servico;
import br.com.barbearia_api.repository.FuncionarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;

@Component
public class AgendamentoConverter {

    public static AgendamentoDTO paraDTO(Agendamento agendamento) {
        AgendamentoDTO dto = new AgendamentoDTO();
        dto.setId(agendamento.getId());
        dto.setData(agendamento.getData());
        dto.setHora(agendamento.getHora());
        dto.setClientes(agendamento.getClientes());
        dto.setFuncionarios(agendamento.getFuncionarios());
        dto.setServicos(agendamento.getServicos());

        return dto;
    }

    public static Agendamento paraEntidade(AgendamentoDTO dto) {
        Agendamento agendamento = new Agendamento();
        agendamento.setId(dto.getId());
        agendamento.setData(dto.getData());
        agendamento.setHora(dto.getHora());
        agendamento.setClientes(dto.getClientes());
        agendamento.setServicos(dto.getServicos());
        agendamento.setFuncionarios(dto.getFuncionarios());
        return agendamento;
    }
}




