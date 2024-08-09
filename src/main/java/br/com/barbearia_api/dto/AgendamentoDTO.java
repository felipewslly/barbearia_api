package br.com.barbearia_api.dto;

import br.com.barbearia_api.model.Clientes;
import br.com.barbearia_api.model.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoDTO {
    private Long id;
    private LocalDate data;
    private LocalTime hora;
    private List<Long> clienteIds = new ArrayList<>();
    private List<Long> funcionarioIds = new ArrayList<>();
    private List<Long> servicoIds = new ArrayList<>();


}
