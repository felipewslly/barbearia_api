package br.com.barbearia_api.controller;

import br.com.barbearia_api.model.entity.Cliente;
import br.com.barbearia_api.services.ClientesServ;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/barbearia")
public class ClienteController extends ClientesServ {


        private ClientesServ clientesServ;


        @PostMapping(consumes = "application/json", produces = "application/json")
        public ResponseEntity<Cliente> createCliente(@RequestBody Cliente clienteiD){
            Cliente cliente = clientesServ.criarCliente(clienteiD);
            return new ResponseEntity<>(cliente, HttpStatus.CREATED);

        }

        @GetMapping("/{id}")
        public ResponseEntity<Cliente> findClienteById(Long clienteId){
            Cliente cliente = clientesServ.clientePorId(clienteId);
            return ResponseEntity.ok(cliente);
        }
        @GetMapping
        public ResponseEntity<List<Cliente>> allClientes(){
            List<Cliente> cliente = clientesServ.todosClientes();
                return ResponseEntity.ok(cliente);
        }
        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteClienteById(Long clienteId){
            clientesServ.deletarClienteId(clienteId);

            return ResponseEntity.ok("Cliente deletado com sucesso");


        }

        @PutMapping("/{id}")
        public ResponseEntity<List<Cliente>> updateClienteById(Long clienteId, @RequestBody Cliente clienteAtt){
           List<Cliente> clientes = clientesServ.atualizarPorId(clienteId, clienteAtt);

           return ResponseEntity.ok("Cliente atualizado com sucesos! " + clienteAtt);
        }


}
