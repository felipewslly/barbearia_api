package br.com.barbearia_api.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name= "clientes")
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @Column(name = "data-de-nascimento")
    private LocalDate dataDeNascimento;

    @Column(name = "genero")
    private String genero;

    @Column(name = "idade")
    private Integer idade;

    @Column(name = "telefone", unique = true)
    private String telefone;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "data-de-cadastro")
    private LocalDate dataDeCadastro;


    @ManyToMany(mappedBy = "clientes", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Agendamento> agendamentos = new ArrayList<>();


}
