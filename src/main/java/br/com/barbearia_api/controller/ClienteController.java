package br.com.barbearia_api.controller;
import br.com.barbearia_api.model.Clientes;
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
public class ClienteController{

    @Autowired
    private ClientesServ clientesServ;


    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Clientes> createCliente(@RequestBody Clientes cliente){
        Clientes newCliente = clientesServ.createClient(cliente);
        return new ResponseEntity<>(newCliente, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clientes> findClienteById(@PathVariable Long id){
        Clientes cliente = clientesServ.clientById(id);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping()
    public ResponseEntity<List<Clientes>> allClientes(){
        List<Clientes> cliente = clientesServ.allClients();
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClienteById(@PathVariable Long id){
        try{
            clientesServ.deleteClientById(id);
            return ResponseEntity.ok("Cliente removido com sucesso");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Clientes> updateClienteById(@PathVariable Long id, @RequestBody Clientes clienteAtt){
        Clientes clientes = clientesServ.updateClientById(id, clienteAtt);
        return ResponseEntity.ok(clientes);
    }


}