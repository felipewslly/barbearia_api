package br.com.barbearia_api.services.servicesint;


import br.com.barbearia_api.model.Clientes;

import java.util.List;


public interface ClienteServices {

    Clientes criarCliente(Clientes cliente);

    Clientes clientePorId(Long clienteId);

    List<Clientes> todosClientes();

    void deletarClienteId(Long clienteId);

   Clientes atualizarPorId(Long clienteId, Clientes clienteAtt);


}
