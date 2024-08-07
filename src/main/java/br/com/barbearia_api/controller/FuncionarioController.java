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


    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Funcionario> createEmployee(@RequestBody Funcionario funcionario) {
        Funcionario funcionario1 = funcionarioServices.criarFuncionario(funcionario);
        return new ResponseEntity<>(funcionario1, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Funcionario>> allEmployees() {
        List<Funcionario> funcionarios = funcionarioServices.todosFuncionarios();
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("{id}")
    public ResponseEntity<Funcionario> findEmployeeById(@PathVariable Long id) {
        Funcionario funcionario = funcionarioServices.funcionarioPorId(id);
        return ResponseEntity.ok(funcionario);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Funcionario> deleteEmployeeById(@PathVariable Long id) {
        Funcionario funcionario = funcionarioServices.deletarFuncionarioId(id);
        return ResponseEntity.ok(funcionario);
    }

    @PutMapping("{id}")
    public ResponseEntity<Funcionario> updateEmployeeById(@PathVariable Long id, @RequestBody Funcionario UpdatedEmployee) {
        Funcionario funcionario = funcionarioServices.atualizarPorId(id, UpdatedEmployee);
        return ResponseEntity.ok(funcionario);
    }
}