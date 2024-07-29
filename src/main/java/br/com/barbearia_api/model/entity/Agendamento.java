package br.com.barbearia_api.model.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "agendamento")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Column(name = "hora", nullable = false)
    private LocalTime hora;

    @ManyToMany
    @JoinTable(
            name = "agendamento_cliente",
            joinColumns = @JoinColumn(name = "agendamento_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "cliente_id",referencedColumnName = "id")
    )
    @JsonManagedReference
    private List<Clientes> clientes;

    @ManyToMany
    @JoinTable(
            name = "agendamento_servico",
            joinColumns = @JoinColumn(name = "agendamento_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "servico_id", referencedColumnName = "id")
    )
    @JsonManagedReference
    private List<Servico> servicos;



    @ManyToMany
    @JoinTable(
            name = "funcionario_agendamento",
            joinColumns = @JoinColumn(name = "agendamento_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "funcionario_id",referencedColumnName = "id")
    )
    @JsonManagedReference
        private List<Funcionario> funcionarios;

}
