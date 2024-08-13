package br.com.barbearia_api.converter;

import br.com.barbearia_api.dto.FuncionarioDTO;
import br.com.barbearia_api.model.Funcionario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FuncionarioConverter {


    public FuncionarioDTO employeeToDTO(Funcionario funcionario){
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        funcionarioDTO.setId(funcionario.getId());
        funcionarioDTO.setNome(funcionario.getNome());
        funcionarioDTO.setTelefone(funcionario.getTelefone());
        return funcionarioDTO;
    }

    public List<FuncionarioDTO> employeeListToDTOList(List<Funcionario> funcionarioList){
            return funcionarioList.stream()
                    .map(funcionario -> {
                        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
                        funcionarioDTO.setId(funcionario.getId());
                        funcionarioDTO.setNome(funcionario.getNome());
                        funcionarioDTO.setTelefone(funcionario.getTelefone());
                        return funcionarioDTO;
                    })
                    .collect(Collectors.toList());
    }

    public Funcionario employeeToEntity(FuncionarioDTO funcionarioDTO){
        Funcionario funcionario = new Funcionario();
        funcionario.setId(funcionarioDTO.getId());
        funcionario.setNome(funcionarioDTO.getNome());
        funcionario.setTelefone(funcionarioDTO.getTelefone());
        return funcionario;
    }

    public List<Funcionario> employeeListToEntityList(List<FuncionarioDTO> funcionarioDTOS){
        return funcionarioDTOS.stream()
                .map(funcionarioDTO -> {
                    Funcionario dto = new Funcionario();
                    dto.setId(funcionarioDTO.getId());
                    dto.setNome(funcionarioDTO.getNome());
                    dto.setTelefone(funcionarioDTO.getTelefone());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
