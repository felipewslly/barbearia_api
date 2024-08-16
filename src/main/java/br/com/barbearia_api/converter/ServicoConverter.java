package br.com.barbearia_api.converter;

import br.com.barbearia_api.dto.ClienteDTO;
import br.com.barbearia_api.dto.ServicoDTO;
import br.com.barbearia_api.model.Clientes;
import br.com.barbearia_api.model.Servico;
import org.springframework.stereotype.Component;


@Component
public class ServicoConverter {
    public static ServicoDTO mapToServicoDto(Servico servico){
        return new ServicoDTO(
                servico.getId(),
                servico.getCorte(),
                servico.getPreco(),
                servico.getDescricao()
        );
    }
    public static Servico mapToServico(ServicoDTO servicoDTO){
        return new Servico(
                servicoDTO.getId(),
                servicoDTO.getCorte(),
                servicoDTO.getPreco(),
                servicoDTO.getDescricao()
        );
    }
}
