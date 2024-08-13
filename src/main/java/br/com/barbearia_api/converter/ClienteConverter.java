package br.com.barbearia_api.converter;

import br.com.barbearia_api.dto.ClienteDTO;
import br.com.barbearia_api.dto.FuncionarioDTO;
import br.com.barbearia_api.model.Clientes;
import br.com.barbearia_api.model.Funcionario;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class ClienteConverter {

    public ClienteDTO clientToDTO(Clientes clientes){
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(clientes.getId());
        clienteDTO.setNome(clientes.getNome());
        clienteDTO.setTelefone(clientes.getTelefone());
        clienteDTO.setIdade(clientes.getIdade());
        clienteDTO.setGenero(clientes.getGenero());
        clienteDTO.setDataDeNascimento(clientes.getDataDeNascimento());
        return clienteDTO;
    }
    public List<ClienteDTO> clientListToDTOList(List<Clientes> clientesList){
        return clientesList.stream()
                .map(clientes -> {
                    ClienteDTO clienteDTO = new ClienteDTO();
                    clienteDTO.setId(clientes.getId());
                    clienteDTO.setNome(clientes.getNome());
                    clienteDTO.setGenero(clientes.getGenero());
                    clienteDTO.setTelefone(clientes.getTelefone());
                    clienteDTO.setDataDeNascimento(clientes.getDataDeNascimento());
                    clienteDTO.setIdade(clientes.getIdade());
                    return clienteDTO;
                })
                .collect(Collectors.toList());
    }

    public Clientes clientDTOToEntity(ClienteDTO clienteDTO){
        Clientes clientes = new Clientes();
        clientes.setId(clienteDTO.getId());
        clientes.setNome(clienteDTO.getNome());
        clientes.setGenero(clienteDTO.getGenero());
        clientes.setTelefone(clienteDTO.getTelefone());
        clientes.setDataDeNascimento(clienteDTO.getDataDeNascimento());
        clientes.setIdade(clienteDTO.getIdade());
        return clientes;
    }

    public List<Clientes> clientListDTOToEntity(List<ClienteDTO> clientesListDTO){
        return clientesListDTO.stream()
                .map(clientesDTO -> {
                    Clientes clientes = new Clientes();
                    clientes.setId(clientesDTO.getId());
                    clientes.setNome(clientesDTO.getNome());
                    clientes.setGenero(clientesDTO.getGenero());
                    clientes.setTelefone(clientesDTO.getTelefone());
                    clientes.setDataDeNascimento(clientesDTO.getDataDeNascimento());
                    clientes.setIdade(clientesDTO.getIdade());
                    return clientes;
                })
                .collect(Collectors.toList());
    }
}
