package br.com.barbearia_api.services;
import br.com.barbearia_api.exception.ApiException;
import br.com.barbearia_api.model.Funcionario;
import br.com.barbearia_api.repository.FuncionarioRepo;
import br.com.barbearia_api.services.servicesint.FuncionarioServices;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioServ implements FuncionarioServices {

    @Autowired
    private FuncionarioRepo funcionarioRepo;


    @Override
    @Transactional
    public Funcionario createEmployee(Funcionario employee) {

            Funcionario employees = new Funcionario();
        employees.setNome(employee.getNome());
        employees.setEndereco(employee.getEndereco());
        employees.setTelefone(employee.getTelefone());
        employees.setCargo(employee.getCargo());
        employees.setEmail(employee.getEmail());
        employees.setDataDeContratacao(employee.getDataDeContratacao());

            return funcionarioRepo.save(employees);
    }

    @Override
    public Funcionario employeeById(Long employeeId) {
        return funcionarioRepo.findById(employeeId).orElseThrow(
                () -> new ApiException("Funcionário não existe/encontrado"));
    }

    @Override
    public List<Funcionario> allEmployees() {
         return funcionarioRepo.findAll();
    }

    @Override
    @Transactional
    public Funcionario deleteEmployeeById(Long employeeId) {
        try{
            Funcionario deleteFuncionarioById = funcionarioRepo.findById(employeeId).orElseThrow(
                    () -> new IllegalArgumentException("Funcionário não encontrado"));
            funcionarioRepo.deleteById(employeeId);
            return deleteFuncionarioById;

        }catch (ApiException e){
            throw new ApiException("Funcionário não encontrado" + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Funcionario updateEmployeeById(Long funcionarioId, Funcionario funcionarioAtt) {

            Funcionario funcionario = funcionarioRepo.findById(funcionarioId).orElseThrow(
                    () -> new IllegalArgumentException("Funcionário não encontrado"));

            funcionario.setNome(funcionarioAtt.getNome());
            funcionario.setCargo(funcionarioAtt.getCargo());
            funcionario.setDataDeContratacao(funcionarioAtt.getDataDeContratacao());
            funcionario.setEmail(funcionarioAtt.getEmail());
            funcionario.setEndereco(funcionarioAtt.getEndereco());
            funcionario.setTelefone(funcionarioAtt.getTelefone());
            return funcionarioRepo.save(funcionario);

    }
}
