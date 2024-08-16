package br.com.barbearia_api.dto;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dataDeNascimento;
    private String genero;
    private Integer idade;
    private String telefone;
    private String endereco;
    private String email;
    private LocalDate dataDeCadastro;


}
