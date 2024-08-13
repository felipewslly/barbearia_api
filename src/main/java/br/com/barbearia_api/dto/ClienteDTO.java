package br.com.barbearia_api.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ClienteDTO {
    private Long id;
    private String nome;
    private LocalDate dataDeNascimento;
    private String telefone;
    private Integer idade;
    private String genero;

}
