package upe.br.consultas.infra.entities;

import jakarta.persistence.*;
import lombok.*;
import upe.br.consultas.infra.enums.CategoriaProdutoEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate data;
    private String descricao;

    private Double valor;

    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "recepcionista_id", nullable = false)
    private Recepcionista recepcionista;

    @ElementCollection
    @CollectionTable(name = "consulta_materiais", joinColumns = @JoinColumn(name = "consulta_id"))
    @Column(name = "material")
    @Builder.Default // Importante para o Lombok não anular a lista na construção
    private List<String> materiaisRequisitados = new ArrayList<>();
    // Tipo do Produto

    @Enumerated(EnumType.STRING)
    private CategoriaProdutoEnum categoria;
    private Integer quantidadeMaterial;

    public Consulta(Integer id, LocalDate data, Double valor, String descricao, Paciente paciente, Medico medico, List<String> materiaisRequisitados, Recepcionista recepcionista, CategoriaProdutoEnum categoria, Integer quantidadeMaterial) {
        this.id = id;
        this.data = data;
        this.valor = valor;
        this.descricao = descricao;
        this.paciente = paciente;
        this.medico = medico;
        this.materiaisRequisitados = materiaisRequisitados;
        this.recepcionista = recepcionista;
        this.categoria = categoria;
        this.quantidadeMaterial = quantidadeMaterial;
    }
}
