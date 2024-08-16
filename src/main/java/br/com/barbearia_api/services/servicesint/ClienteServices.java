package br.com.barbearia_api.services.servicesint;


import br.com.barbearia_api.model.Clientes;

import java.util.List;


public interface ClienteServices {

    Clientes createClient(Clientes cliente);

    Clientes clientById(Long clienteId);

    List<Clientes> allClients();

    void deleteClientById(Long clienteId);

   Clientes updateClientById(Long clienteId, Clientes clienteAtt);


}
