package br.com.barbearia_api.services.servicesint;


import br.com.barbearia_api.model.entity.Clientes;

import java.util.List;


public interface ClienteServices {

    Clientes criarCliente(Clientes cliente);

    Clientes clientePorId(Long clienteId);

    List<Clientes> todosClientes();

    List<Clientes> deletarClienteId(Long clienteId);

   List<Clientes> atualizarPorId(Long clienteId, Clientes clienteAtt);


}
