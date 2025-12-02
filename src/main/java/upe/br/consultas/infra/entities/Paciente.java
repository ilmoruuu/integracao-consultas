package upe.br.consultas.infra.entities;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.*;
import upe.br.consultas.infra.enums.SexoEnum;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private int idade;
    private String cpf;
    private String email;
    private String telefone;
    @ElementCollection
    @CollectionTable(name = "paciente_historico", joinColumns = @JoinColumn(name = "paciente_id"))
    @Builder.Default
    private List<Byte> historico = new ArrayList<>(); //Histórico será vários documentos médicos
    private SexoEnum sexo;
    @ElementCollection
    @CollectionTable(name = "paciente_restricoes", joinColumns = @JoinColumn(name = "paciente_id"))
    @Builder.Default
    private List<String> restricoes = new ArrayList<>(); //Alergias, comprometimentos

}
