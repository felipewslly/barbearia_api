package br.com.barbearia_api.converter;

import br.com.barbearia_api.dto.ClienteDTO;
import br.com.barbearia_api.dto.FuncionarioDTO;
import br.com.barbearia_api.model.Clientes;
import br.com.barbearia_api.model.Funcionario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FuncionarioConverter {
    public static FuncionarioDTO mapToFuncionarioDto(Funcionario funcionario){
        return new FuncionarioDTO(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCargo(),
                funcionario.getTelefone(),
                funcionario.getEmail(),
                funcionario.getEndereco(),
                funcionario.getDataDeContratacao()
        );
    }
    public static Funcionario mapToFuncionario(FuncionarioDTO funcionarioDTO){
        return new Funcionario(
                funcionarioDTO.getId(),
                funcionarioDTO.getNome(),
                funcionarioDTO.getCargo(),
                funcionarioDTO.getTelefone(),
                funcionarioDTO.getEmail(),
                funcionarioDTO.getEndereco(),
                funcionarioDTO.getDataDeContratacao()
        );
    }

    }