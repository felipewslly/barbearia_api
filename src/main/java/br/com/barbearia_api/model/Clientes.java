package br.com.barbearia_api.model;


import com.fasterxml.jackson.annotation.*;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
    @JsonBackReference
    private List<Agendamento> agendamentos = new ArrayList<>();

    public Clientes(Long id, String nome, String cpf, LocalDate dataDeNascimento, String genero, Integer idade, String telefone, String endereco, String email, LocalDate dataDeCadastro) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
        this.genero = genero;
        this.idade = idade;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.dataDeCadastro = dataDeCadastro;
    }
}