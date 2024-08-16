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

    public static ClienteDTO mapToClientDto(Clientes clientes){
        return new ClienteDTO(
                clientes.getId(),
                clientes.getNome(),
                clientes.getCpf(),
                clientes.getDataDeNascimento(),
                clientes.getGenero(),
                clientes.getIdade(),
                clientes.getTelefone(),
                clientes.getEndereco(),
                clientes.getEmail(),
                clientes.getDataDeCadastro()
        );
    }
    public static Clientes mapToCliente(ClienteDTO clienteDTO){
        return new Clientes(
                clienteDTO.getId(),
                clienteDTO.getNome(),
                clienteDTO.getCpf(),
                clienteDTO.getDataDeNascimento(),
                clienteDTO.getGenero(),
                clienteDTO.getIdade(),
                clienteDTO.getTelefone(),
                clienteDTO.getEmail(),
                clienteDTO.getEndereco(),
                clienteDTO.getDataDeCadastro()
        );
    }

}
