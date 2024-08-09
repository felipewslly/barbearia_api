package br.com.barbearia_api.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FuncionarioDTO {
    private Long id;
    private String nome;
    private String telefone;

}
