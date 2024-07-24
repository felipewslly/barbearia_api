package br.com.barbearia_api.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "servico")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "corte", nullable = false)
    private String corte;

    @Column(name = "preco", nullable = false)
    private String preco;

    @ManyToMany
    private List<Agendamento> agendamentos = new ArrayList<>();


}
