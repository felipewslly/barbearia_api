package br.com.barbearia_api.model.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "servicos")
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "corte", nullable = false)
    private String corte;

    @Column(name = "preco", nullable = false)
    private String preco;

    @ManyToMany(mappedBy = "servicos", fetch = FetchType.LAZY, cascade = CascadeType.ALL)

    private List<Agendamento> agendamentos = new ArrayList<>();


}
