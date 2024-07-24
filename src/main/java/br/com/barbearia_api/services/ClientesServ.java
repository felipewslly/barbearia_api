package br.com.barbearia_api.services;

import br.com.barbearia_api.model.entity.Cliente;
import br.com.barbearia_api.repository.ClienteRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class ClientesServ implements ClienteServices{

    @Autowired
    ClienteRepo clienteRepo;

    @Override
    public Cliente criarCliente(Cliente cliente) {
        Cliente cliente1 = new Cliente();
        cliente1.setNome(cliente.getNome());
        cliente1.setEndereco(cliente.getEndereco());
        cliente1.setTelefone(cliente.getTelefone());

           return clienteRepo.save(cliente1);
    }

    @Override
    public Cliente clientePorId(Long clienteId) {
        Cliente clientes = clienteRepo.findById(clienteId).orElseThrow(
                ()-> new IllegalArgumentException("ID DO CLIENTE NÃO EXISTE"));
        return clientes;
    }

    @Override
    public List<Cliente> todosClientes() {
        List<Cliente> clientes = clienteRepo.findAll();
            if (clientes.isEmpty()){
                throw new RuntimeException("LISTA VAZIA");
            }

        return clientes;
    }

    @Override
    public Cliente deletarClienteId(Long clienteId) {
        try{
            if (!clienteRepo.existsById(clienteId)){
                throw new IllegalArgumentException("ID do agendamento não encontrado");
            }
            clienteRepo.deleteById(clienteId);
        }catch(IllegalArgumentException e){
            throw e;
        }catch (Exception e){
            throw new RuntimeException("Erro ao excluir agendamento", e);
        }

        return (Cliente) clienteRepo.findAll();


    }

    @Override
    public Optional<Cliente> atualizarPorId(Long clienteId, Cliente clienteAtt) {
        Optional<Cliente> clienteOp = clienteRepo.findById(clienteId);

            if (clienteOp.isPresent()){
                Cliente cliente = clienteOp.get();
                cliente.setNome(cliente.getNome());
                cliente.setTelefone(cliente.getTelefone());
                cliente.setEndereco(cliente.getEndereco());
                clienteRepo.save(cliente);
                    return Optional.of(cliente);
            }
            return clienteOp;
    }
}
