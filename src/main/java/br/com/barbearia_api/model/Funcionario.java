package br.com.barbearia_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "email")
    private String email;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "data_de_contratacao")
    private LocalDate dataDeContratacao;

    @ManyToMany(mappedBy = "funcionarios", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Agendamento> agendamentos = new ArrayList<>();
}
