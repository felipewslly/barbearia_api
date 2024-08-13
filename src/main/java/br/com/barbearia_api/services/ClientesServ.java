package br.com.barbearia_api.services;


import br.com.barbearia_api.converter.ClienteConverter;
import br.com.barbearia_api.model.Clientes;
import br.com.barbearia_api.repository.ClienteRepo;
import br.com.barbearia_api.services.servicesint.ClienteServices;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class ClientesServ implements ClienteServices {

    @Autowired
    private ClienteRepo clienteRepo;

    @Override
    @Transactional
    public Clientes criarCliente(Clientes cliente) {

            Clientes cliente1 = new Clientes();
            cliente1.setNome(cliente.getNome());
            cliente1.setEndereco(cliente.getEndereco());
            cliente1.setTelefone(cliente.getTelefone());
            cliente1.setCpf(cliente.getCpf());
            cliente1.setGenero(cliente.getGenero());
            cliente1.setDataDeNascimento(cliente.getDataDeNascimento());
            cliente1.setIdade(cliente.getIdade());
            cliente1.setEmail(cliente.getEmail());
            cliente1.setDataDeCadastro(cliente.getDataDeCadastro());
            return clienteRepo.save(cliente1);
        }

    @Override
    public Clientes clientePorId(Long clienteId) {
        return clienteRepo.findById(clienteId).orElseThrow(
                ()-> new IllegalArgumentException("ID DO CLIENTE NÃO EXISTE"));
    }

    @Override
    public List<Clientes> todosClientes() {
        List<Clientes> clientes = clienteRepo.findAll();
            return clientes;

    }

    @Override
    @Transactional
    public List<Clientes> deletarClienteId(Long id) {
        try{
            if (!clienteRepo.existsById(id)){
                throw new IllegalArgumentException("ID do cliente não encontrado");
            }
                clienteRepo.deleteById(id);
        }catch(IllegalArgumentException e){
            throw e;
        }catch (Exception e){
            throw new RuntimeException("Erro ao excluir o usuario", e);
        }

        return clienteRepo.findAll();

    }

    @Override
    @Transactional
    public List<Clientes> atualizarPorId(Long clienteId, Clientes clienteAtt) {
        Optional<Clientes> clientesIds = clienteRepo.findById(clienteId);

        if (clientesIds.isPresent()) {
            Clientes clientes = clientesIds.get();
            clientes.setNome(clienteAtt.getNome());
            clientes.setCpf(clienteAtt.getCpf());
            clientes.setGenero(clienteAtt.getGenero());
            clientes.setTelefone(clienteAtt.getTelefone());
            clientes.setIdade(clienteAtt.getIdade());
            clientes.setEmail(clienteAtt.getEmail());
            clientes.setDataDeCadastro(clienteAtt.getDataDeCadastro());
            clientes.setDataDeNascimento(clienteAtt.getDataDeNascimento());
            clientes.setAgendamentos(clienteAtt.getAgendamentos());
            clienteRepo.save(clientes);
        } else {
            throw new RuntimeException("Cliente não encontrado com este ID: " + clientesIds);
        }

        return clienteRepo.findAll();
    }
}
