package br.com.barbearia_api.services.servicesint;

import br.com.barbearia_api.dto.FuncionarioDTO;
import br.com.barbearia_api.model.Funcionario;

import java.util.List;

public interface FuncionarioServices {

    Funcionario createEmployee(Funcionario funcionario);

    Funcionario employeeById(Long funcionarioId);

    List<Funcionario> allEmployees();

    Funcionario deleteEmployeeById(Long funcionarioId);

    Funcionario updateEmployeeById(Long funcionarioId, Funcionario funcionarioAtt);
}
