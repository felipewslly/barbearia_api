package br.com.barbearia_api.services;


import br.com.barbearia_api.exception.ApiException;
import br.com.barbearia_api.model.Clientes;
import br.com.barbearia_api.repository.ClienteRepo;
import br.com.barbearia_api.services.servicesint.ClienteServices;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class ClientesServ implements ClienteServices {

    @Autowired
    private ClienteRepo clienteRepo;

    @Override
    @Transactional
    public Clientes createClient(Clientes cliente) {

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


    public Clientes clientByCpf(String cpf) {
        return clienteRepo.findClientByCpf(cpf);
    }

    @Override
    public Clientes clientById(Long clienteId) {
        return clienteRepo.findById(clienteId).orElseThrow(
                () -> new IllegalArgumentException("ID DO CLIENTE NÃO EXISTE"));
    }

    @Override
    public List<Clientes> allClients() {
        return clienteRepo.findAll();

    }

    @Override
    @Transactional
    public void deleteClientById(Long id) {
        if (!clienteRepo.existsById(id)) {
            throw new ApiException("ID NÃO EXISTENTE");
        }
        clienteRepo.deleteById(id);
    }

    @Override
    @Transactional
    public Clientes updateClientById(Long clienteId, Clientes clienteAtt) {
        Clientes clienteExistente = clienteRepo.findById(clienteId)
                .orElseThrow(() -> new ApiException("ID NÃO EXISTENTE"));

        clienteExistente.setNome(clienteAtt.getNome());
        clienteExistente.setIdade(clienteAtt.getIdade());
        clienteExistente.setTelefone(clienteAtt.getTelefone());
        clienteExistente.setDataDeNascimento(clienteAtt.getDataDeNascimento());
        clienteExistente.setDataDeCadastro(clienteAtt.getDataDeCadastro());
        clienteExistente.setEndereco(clienteAtt.getEndereco());
        clienteExistente.setGenero(clienteAtt.getGenero());
        clienteExistente.setEmail(clienteAtt.getEmail());


        return clienteRepo.save(clienteExistente);
    }

}
