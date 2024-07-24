package br.com.barbearia_api.services;

import br.com.barbearia_api.model.entity.Cliente;

import java.util.List;
import java.util.Optional;


public interface ClienteServices {

    Cliente criarCliente(Cliente cliente);

    Cliente clientePorId(Long clienteId);

    List<Cliente> todosClientes();

    Cliente deletarClienteId(Long clienteId);

   List<Cliente> atualizarPorId(Long clienteId, Cliente clienteAtt);


}
