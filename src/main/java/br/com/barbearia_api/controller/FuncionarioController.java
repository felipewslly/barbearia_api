package br.com.barbearia_api.controller;
import br.com.barbearia_api.model.Funcionario;
import br.com.barbearia_api.services.servicesint.FuncionarioServices;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("barbearia/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioServices funcionarioServices;

    @PostMapping()
    public ResponseEntity<Funcionario> createEmployee(@RequestBody Funcionario employee) {
        Funcionario createEmployees = funcionarioServices.createEmployee(employee);
        return new ResponseEntity<>(createEmployees, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Funcionario>> allEmployees() {
        List<Funcionario> allEmployee = funcionarioServices.allEmployees();
        return ResponseEntity.ok(allEmployee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> findEmployeeById(@PathVariable Long id) {
        Funcionario employeeById = funcionarioServices.employeeById(id);
        return ResponseEntity.ok(employeeById);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Funcionario> deleteEmployeeById(@PathVariable Long id) {
        Funcionario employeeDelete = funcionarioServices.deleteEmployeeById(id);
        return ResponseEntity.ok(employeeDelete);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> updateEmployeeById(@PathVariable Long id, @RequestBody Funcionario updatedEmployee) {
        Funcionario newEmployee = funcionarioServices.updateEmployeeById(id, updatedEmployee);
        return ResponseEntity.ok(newEmployee);
    }
}