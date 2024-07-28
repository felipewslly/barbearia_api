package br.com.barbearia_api.controller;


import br.com.barbearia_api.model.entity.Clientes;
import br.com.barbearia_api.services.ClientesServ;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("barbearia/clientes")
public class ClienteController extends ClientesServ {

    @Autowired
    private ClientesServ clientesServ;


    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Clientes> createCliente(@RequestBody Clientes clienteId){
        Clientes cliente = clientesServ.criarCliente(clienteId);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clientes> findClienteById(@PathVariable Long id){
        Clientes cliente = clientesServ.clientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping()
    public ResponseEntity<List<Clientes>> allClientes(){
        List<Clientes> cliente = clientesServ.todosClientes();
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Clientes>> deleteClienteById(@PathVariable Long id){
        List<Clientes> clientesAtt =  clientesServ.deletarClienteId(id);
        List<Clientes> clientesTotais = clientesServ.todosClientes();
        return ResponseEntity.ok(clientesTotais);
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<Clientes>> updateClienteById(@PathVariable Long id, @RequestBody Clientes clienteAtt){
        List<Clientes> clientes = clientesServ.atualizarPorId(id, clienteAtt);
        return ResponseEntity.ok(clientes);
    }


}