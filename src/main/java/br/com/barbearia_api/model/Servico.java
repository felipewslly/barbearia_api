package br.com.barbearia_api.model;
import com.fasterxml.jackson.annotation.*;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "corte", nullable = false)
    private String corte;

    @Column(name = "preco", nullable = false)
    private String preco;

    @Column(name = "descricao")
    private String descricao;

    @ManyToMany(mappedBy = "servicos", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Agendamento> agendamentos = new ArrayList<>();

    public Servico(Long id, String corte, String preco, String descricao) {
        this.id = id;
        this.corte = corte;
        this.preco = preco;
        this.descricao = descricao;
    }
}