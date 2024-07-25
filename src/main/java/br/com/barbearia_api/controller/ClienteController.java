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
@RequestMapping("/api/barbearia/")
public class ClienteController extends ClientesServ {

        @Autowired
        private ClientesServ clientesServ;


        @PostMapping(consumes = "application/json", produces = "application/json")
        public ResponseEntity<Clientes> createCliente(@RequestBody Clientes clienteId){
            Clientes cliente = clientesServ.criarCliente(clienteId);
            return new ResponseEntity<>(cliente, HttpStatus.CREATED);
        }

        @GetMapping("/clientes/{id}")
        public ResponseEntity<Clientes> findClienteById(Long clienteId){
            Clientes cliente = clientesServ.clientePorId(clienteId);
            return ResponseEntity.ok(cliente);
        }

        @GetMapping("/clientes")
        public ResponseEntity<List<Clientes>> allClientes(){
            List<Clientes> cliente = clientesServ.todosClientes();
                return ResponseEntity.ok(cliente);
        }

        @DeleteMapping("/clientes/{id}")
        public ResponseEntity<String> deleteClienteById(Long clienteId){
            clientesServ.deletarClienteId(clienteId);
            return ResponseEntity.ok("Cliente deletado com sucesso");
        }

        @PutMapping("/clientes/{id}")
        public ResponseEntity<List<Clientes>> updateClienteById(Long clienteId, @RequestBody Clientes clienteAtt){
           List<Clientes> clientes = clientesServ.atualizarPorId(clienteId, clienteAtt);
           return ResponseEntity.ok(clientes);
        }


}
