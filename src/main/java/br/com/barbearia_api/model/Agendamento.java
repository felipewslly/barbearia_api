package br.com.barbearia_api.model;


import br.com.barbearia_api.dto.ClienteDTO;
import br.com.barbearia_api.dto.FuncionarioDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "agendamento_cliente",
            joinColumns = @JoinColumn(name = "agendamento_id"),
            inverseJoinColumns = @JoinColumn(name = "cliente_id")
    )

    @JsonBackReference
    private List<Clientes> clientes = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "agendamento_servico",
            joinColumns = @JoinColumn(name = "agendamento_id"),
            inverseJoinColumns = @JoinColumn(name = "servico_id")
    )

    @JsonBackReference
    private List<Servico> servicos = new ArrayList<>();



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "funcionario_agendamento",
            joinColumns = @JoinColumn(name = "agendamento_id"),
            inverseJoinColumns = @JoinColumn(name = "funcionario_id")
    )

        @JsonBackReference
        private List<Funcionario> funcionarios = new ArrayList<>();

}
